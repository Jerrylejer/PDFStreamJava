package com.jeromerichard.pdfstream.Controller;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.PdfDTOWayIN;
import com.jeromerichard.pdfstream.Dto.EntityToDto.PdfDTO;
import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.FileUploadExceptionAdvice;
import com.jeromerichard.pdfstream.Exception.NotFoundException;
import com.jeromerichard.pdfstream.Service.Implementations.PdfService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/pdf")
public class PdfController {
    @Autowired
    private PdfService service;
    @Autowired
    private ModelMapper modelMapper;


/*    @PostMapping("/new")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    // les datas seront placées dans le corps de la réponse HTTP sans être interprétées comme une vue HTML.
    public ResponseEntity<PdfDTO> savePdf(@RequestBody PdfDTOWayIN clientDatas) {
        // Conversion des datas front en DTOWayIN
        PdfDTOWayIN pdfDTOWayIN = modelMapper.map(clientDatas, PdfDTOWayIN.class);
        // Conversion sens DTOWayIN à Entité
        Pdf pdf = service.savePdf(pdfDTOWayIN);
        // Conversion sens Entité à DTO
        PdfDTO pdfDTO = modelMapper.map(pdf, PdfDTO.class);
        return new ResponseEntity<PdfDTO>(pdfDTO, HttpStatus.CREATED);
    }*/

    @PostMapping("/new")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    // les datas seront placées dans le corps de la réponse HTTP sans être interprétées comme une vue HTML.
    public Pdf savePdf(@RequestParam("file")MultipartFile file) throws IOException, FileUploadExceptionAdvice {
        return service.store(file);
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<PdfDTO> updatePdf(@PathVariable Integer id, @RequestBody PdfDTOWayIN clientDatas) throws NotFoundException {
        // Conversion des datas front en DTOWayIN
        PdfDTOWayIN pdfDTOWayIN = modelMapper.map(clientDatas, PdfDTOWayIN.class);
        // Conversion sens DTOWayIN à Entité
        Pdf pdf = service.updatePdf(id, pdfDTOWayIN);
        // Conversion sens Entité à DTO
        PdfDTO pdfDTO = modelMapper.map(pdf, PdfDTO.class);
        return new ResponseEntity<PdfDTO>(pdfDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<?> deletePdf(@PathVariable Integer id) throws NotFoundException {
        service.deletePdf(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<List<PdfDTO>> getPdfsList() throws EmptyListException {
        // Le flux de datas retourné par getAllAlerts() est traité pour que chaque data soit convertie en class AlertDTO et retournée sous forme de List
        List<PdfDTO> pdfDTOList = service.getAllPdfs().stream().map(pdf -> modelMapper.map(pdf, PdfDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(pdfDTOList, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<PdfDTO> getPdf(@PathVariable Integer id) throws NotFoundException {
        Pdf pdf = service.getPdfById(id);
        // Conversion sens Entité à DTO
        PdfDTO pdfDTO = modelMapper.map(pdf, PdfDTO.class);
        return new ResponseEntity<PdfDTO>(pdfDTO, HttpStatus.OK);
    }
}
