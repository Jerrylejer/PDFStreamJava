package com.jeromerichard.pdfstream.Controller;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.PdfDTOWayIN;
import com.jeromerichard.pdfstream.Dto.EntityToDto.PdfDTO;
import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Entity.User;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;
import com.jeromerichard.pdfstream.Service.Implementations.PdfService;
import com.jeromerichard.pdfstream.Service.Implementations.UserService;
import com.jeromerichard.pdfstream.Utils.DefaultMultipartFile;
import com.jeromerichard.pdfstream.Utils.ResizedMultipartFile;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
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

    private MultipartFile resizeImage(MultipartFile image) throws IOException {
        // Lecture des datas du fichier original
        InputStream inputStream = image.getInputStream();

        // Dimensions maximales acceptées pour les cards (category & pdf)
        int maxWidth = 240;
        int maxHeight = 291;

        // Fichier byte[] temporaire pour stocker l'image resizée
        // ajuster selon la nécessité
        byte[] resizedImageData = new byte[1024 * 1024];

        // 2 variables qui vont stocker les bytes et la taille du fichier
        int bytesRead, totalSize = 0;

        // Temporarily store resized image data
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (inputStream) {
            // Resize on the fly (adjust filtering as needed)
            while ((bytesRead = inputStream.read(resizedImageData)) != -1) {
                BufferedImage resizedBufferedImage = ImageIO.read(new ByteArrayInputStream(resizedImageData, 0, bytesRead));
                BufferedImage resizedImage = new BufferedImage(maxWidth, maxHeight, BufferedImage.TYPE_INT_RGB);
                Graphics2D g = resizedImage.createGraphics();
                g.drawImage(resizedBufferedImage.getScaledInstance(maxWidth, maxHeight, Image.SCALE_SMOOTH), 0, 0, null);
                g.dispose();
                ImageIO.write(resizedImage, "jpg", baos); // Assuming JPEG format, adjust as needed
                totalSize += bytesRead;
            }
        }

        // Create a new ResizedMultipartFile object for the resized image
        MultipartFile resizedMultipartFile = new ResizedMultipartFile(
                "resized_image.jpg",
                "resized_image.jpg",
                image.getContentType(),
                baos.toByteArray()
        );

        return resizedMultipartFile;
    }

/*    @PostMapping("/upload")
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
                    log.info("passé par image par défaut !");

                    return new ResponseEntity<>(pdfDto, HttpStatus.CREATED);

                } catch (IOException e) {
                    // Handle the exception, e.g., log the error
                    e.printStackTrace();
                }
        } else {
            // Enregistrer le PDF avec l'image fournie par l'utilisateur
            Pdf pdf = service.savePdf(clientDatas, pdfFile, image);
            PdfDTO pdfDto = modelMapper.map(pdf, PdfDTO.class);
            System.out.println("passé par image de l'utilisateur ?");
            return new ResponseEntity<>(pdfDto, HttpStatus.CREATED);
        }
        return null;
    }*/

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
                log.info("passé par image par défaut !");

                return new ResponseEntity<>(pdfDto, HttpStatus.CREATED);

            } catch (IOException e) {
                // Handle the exception, e.g., log the error
                e.printStackTrace();
            }
        } else {
            // Resize the user-uploaded image (without converting to BufferedImage)
            MultipartFile resizedImage = resizeImage(image);

            // Use the resized MultipartFile in service method
            Pdf pdf = service.savePdf(clientDatas, pdfFile, resizedImage);
            PdfDTO pdfDto = modelMapper.map(pdf, PdfDTO.class);
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
