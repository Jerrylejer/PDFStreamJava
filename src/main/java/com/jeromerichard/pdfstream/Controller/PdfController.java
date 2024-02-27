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
import com.jeromerichard.pdfstream.Utils.DefaultMultipartFile;
import com.jeromerichard.pdfstream.message.ResponseMessage;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin("*")
@Slf4j
@RestController
@RequestMapping("/pdf")
public class PdfController {
    @Autowired
    private PdfService service;
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/upload")
    public ResponseEntity<PdfDTO> savePdf(@ModelAttribute PdfDTOWayIN clientDatas,
                                                   @RequestPart(value = "pdfFile") MultipartFile pdfFile,
                                          @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
        if(image == null || image.isEmpty()) {
            // Convertir l'image par défaut en MultipartFile
            DefaultMultipartFile defaultImageMultipartFile;
            InputStream inputStream = getClass().getResourceAsStream("/defaultImage.jpg");
                assert inputStream != null;
                log.info("### inputStream: ###" + inputStream);
                try {
                    byte[] defaultImageBytes = inputStream.readAllBytes();
                    log.info("### defaultImageBytes: ###" + defaultImageBytes);
                    defaultImageMultipartFile = new DefaultMultipartFile(
                            "defaultImage.jpg",
                            "defaultImage.jpg",
                            "image/jpg",
                            defaultImageBytes
                    );
                    // defaultImageBytes est un tableau de byte[] remplie = ok
                    log.info("### defaultImageBytes.length in controller ### : " + defaultImageBytes.length);
                    Pdf pdf = service.savePdfWithDefaultImage(clientDatas, pdfFile, defaultImageMultipartFile);
                    PdfDTO pdfDto = modelMapper.map(pdf, PdfDTO.class);
                    log.info("passé par ici !");

                    return new ResponseEntity<>(pdfDto, HttpStatus.CREATED);

                } catch (IOException e) {
                    // Handle the exception, e.g., log the error
                    e.printStackTrace();
                }
        } else {
            // Enregistrer le PDF avec l'image fournie ou l'image par défaut (ternaire) => dans les 2 cas je transmets un type MultipartFile
            Pdf pdf = service.savePdf(clientDatas, pdfFile, image);
            PdfDTO pdfDto = modelMapper.map(pdf, PdfDTO.class);
            System.out.println("passé par là ?");
            return new ResponseEntity<>(pdfDto, HttpStatus.CREATED);
        }
        return null;
    }


    @PutMapping("/update/{id}")
    @ResponseBody
    //@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<PdfDTO> updatePdf(@PathVariable Integer id,
                                            @ModelAttribute PdfDTOWayIN clientDatas,
                                            @RequestParam(value = "pdfFile", required = false)MultipartFile pdfFile, @RequestPart(value = "image", required = false) MultipartFile image) throws NotFoundException, IOException {
        // Conversion des datas front en DTOWayIN
        PdfDTOWayIN pdfDTOWayIN = modelMapper.map(clientDatas, PdfDTOWayIN.class);

        // Si je ne précise pas que chacune des MultipartFile peut ne pas présente = erreur back "null" à la réception de la requête front
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
