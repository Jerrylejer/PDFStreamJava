package com.jeromerichard.pdfstream.Controller;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.PdfDTOWayIN;
import com.jeromerichard.pdfstream.Dto.EntityToDto.PdfDTO;
import com.jeromerichard.pdfstream.Dto.EntityToDto.UserDTO;
import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Entity.User;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;
import com.jeromerichard.pdfstream.Service.Implementations.PdfService;
import com.jeromerichard.pdfstream.Service.Implementations.UserService;
import com.jeromerichard.pdfstream.message.ResponseMessage;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin("*")
@RestController
@RequestMapping("/pdf")
public class PdfController {
    @Autowired
    private PdfService service;
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

/*    @PostMapping("/upload")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    // les datas seront placées dans le corps de la réponse HTTP sans être interprétées comme une vue HTML.
    public ResponseEntity<PdfDTO> savePdf(@ModelAttribute PdfDTOWayIN clientDatas, @RequestParam("pdfFile")MultipartFile pdfFile, @RequestParam("image")MultipartFile image) throws IOException, FileUploadExceptionAdvice {
        // ... mais J'enregistre directement l'image dans la BDD
        // Conversion des datas front en DTOWayIN
        PdfDTOWayIN pdfDTOWayIN = modelMapper.map(clientDatas, PdfDTOWayIN.class);
        // Conversion sens DTOWayIN à Entité
        Pdf pdf = service.savePdf(pdfDTOWayIN, pdfFile, image);
        // Conversion sens Entité à DTO
        PdfDTO pdfDTO = modelMapper.map(pdf, PdfDTO.class);
        return new ResponseEntity<>(pdfDTO, HttpStatus.CREATED);
    }*/

    @PostMapping("/upload")
    public ResponseEntity<PdfDTO> savePdf(@ModelAttribute PdfDTOWayIN clientDatas,
                                                   @RequestPart(value = "pdfFile") MultipartFile pdfFile, @RequestPart(value = "image") MultipartFile image) throws IOException {
        // Conversion sens DTOWayIN à Entité
        Pdf pdf = service.savePdf(clientDatas, pdfFile, image);
        // Conversion sens Entité à DTO
        PdfDTO pdfDto = modelMapper.map(pdf, PdfDTO.class);

        return new ResponseEntity<>(pdfDto, HttpStatus.CREATED);
        //return ResponseEntity.ok().body(pdfDto);
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    //@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<PdfDTO> updatePdf(@PathVariable Integer id,
                                            @ModelAttribute PdfDTOWayIN clientDatas,
                                            @RequestParam(value = "pdfFile", required = false)MultipartFile pdfFile, @RequestPart(value = "image", required = false) MultipartFile image) throws NotFoundException, IOException {
        // Conversion des datas front en DTOWayIN
        PdfDTOWayIN pdfDTOWayIN = modelMapper.map(clientDatas, PdfDTOWayIN.class);

        Pdf pdf = null;
        if (pdfFile != null && image != null) {
            pdf = service.updatePdf(id, pdfDTOWayIN, pdfFile, image);
        }
        if (pdfFile == null && image != null) {
            pdf = service.updatePdfExceptPdfFile(id, pdfDTOWayIN, image);
        }
        if (pdfFile != null && image == null) {
            pdf = service.updatePdfExceptImage(id, pdfDTOWayIN, pdfFile);
        }
        if (pdfFile == null && image == null){
            pdf = service.updatePdflight(id, pdfDTOWayIN);
        }
        // Conversion sens DTOWayIN à Entité
        //Pdf pdf = service.updatePdf(id, pdfDTOWayIN, pdfFile, image);
        // Conversion sens Entité à DTO
        PdfDTO pdfDTO = modelMapper.map(pdf, PdfDTO.class);
        return new ResponseEntity<PdfDTO>(pdfDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    //@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<?> deletePdf(@PathVariable Integer id) throws NotFoundException {
        service.deletePdf(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("")
    //@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<List<PdfDTO>> getPdfsList() throws EmptyListException {
        // Le flux de datas retourné par getAllAlerts() est traité pour que chaque data soit convertie en class AlertDTO et retournée sous forme de List
        List<PdfDTO> pdfDTOList = service.getAllPdfs().stream().map(pdf -> modelMapper.map(pdf, PdfDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(pdfDTOList, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    //@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<PdfDTO> getPdf(@PathVariable Integer id) throws NotFoundException {
        Pdf pdf = service.getPdfById(id);
        // Conversion sens Entité à DTO
        PdfDTO pdfDTO = modelMapper.map(pdf, PdfDTO.class);
        return new ResponseEntity<PdfDTO>(pdfDTO, HttpStatus.OK);
    }

    @GetMapping("/author/{id}")
    //@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<List<PdfDTO>> getPdfsListByAuthor(@PathVariable User id) throws EmptyListException {
        List<Pdf> pdfList = service.findByAuthor(id);
        List<PdfDTO> pdfDTOList = pdfList.stream().map(pdf -> modelMapper.map(pdf, PdfDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(pdfDTOList, HttpStatus.OK);
    }
}
