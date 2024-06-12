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
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
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

    // méthode Java qui prend en entrée un fichier image MultipartFile et retourne une image redimenssionnée
    private MultipartFile resizeImage(MultipartFile image) throws IOException {
        // MultipartFile est une interface qui représente un fichier téléchargé dans un contrôleur
        // getInputStream() est une méthode de la Class MultipartFile qui permet d'obtenir
        // un flux pour lire les données du fichier image.
        InputStream inputStream = image.getInputStream();
        // Dimensions maximales acceptées pour mes cards (category & pdf)
        int maxWidth = 240;
        int maxHeight = 291;
        // Crée un flux dans lequel les données de la nouvelle image seront finalement stockées.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (inputStream; baos) {
            // Lire l'image d'origine depuis le flux inputStream
            // BufferedImage permet d'accéder à des méthodes de manipulation d'image en termes de pixels
            BufferedImage originalImage = ImageIO.read(inputStream);
            if (originalImage == null) {
                throw new IOException("L'image fournie n'est pas valide ou absente.");
            }
            // Crée une nouvelle instance de BufferedImage avec les dimensions maximales spécifiées.
            BufferedImage resizedImage = new BufferedImage(maxWidth, maxHeight, BufferedImage.TYPE_INT_RGB);
            // Crée un objet Graphics2D à partir de l'image redimensionnée, ce qui permet de dessiner sur cette image
            Graphics2D g = resizedImage.createGraphics();
            // Redimensionne l'image originale et la dessine sur l'image redimensionnée en utilisant un algorithme de mise à l'échelle en douceur
            g.drawImage(originalImage.getScaledInstance(maxWidth, maxHeight, Image.SCALE_SMOOTH), 0, 0, maxWidth, maxHeight, null);
            g.dispose();
            // Transcrit l'image redimensionnée dans le flux de sortie baos sous forme de fichier JPG
            ImageIO.write(resizedImage, "jpg", baos);

        } catch (IOException e) {
            // Si erreur, je la retourne
            e.printStackTrace();
        }
        // Nouvel objet MultipartFile contenant les données de l'image redimensionnée
        MultipartFile resizedMultipartFile = new ResizedMultipartFile(
                "resized_image.jpg",
                "resized_image.jpg",
                image.getContentType(),
                baos.toByteArray()
        );
        return resizedMultipartFile;
    }

    @PostMapping("/upload")
    public ResponseEntity<PdfDTO> savePdf(@ModelAttribute(value="clientDatas") PdfDTOWayIN clientDatas,
                                          @RequestPart(value = "pdfFile") MultipartFile pdfFile,
                                          @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {

        System.out.println(clientDatas);
        // 1 - Si pas d'image, j'associe une image par défaut au pdf
        if(image == null || image.isEmpty()) {
            // Convertir l'image par défaut en MultipartFile
            DefaultMultipartFile defaultImageMultipartFile;
            InputStream inputStream = getClass().getResourceAsStream("/defaultImage.jpg");
            /*assert inputStream != null;*/
            log.info("### inputStream: ###" + inputStream);
            try {
                assert inputStream != null;
                byte[] defaultImageBytes = inputStream.readAllBytes();
                log.info("### defaultImageBytes: ###" + defaultImageBytes);
                // Je créé un multipartFile et y enregistre les données de l'image par défaut
                defaultImageMultipartFile = new DefaultMultipartFile(
                        "defaultImage.jpg",
                        "defaultImage.jpg",
                        "image/jpg",
                        defaultImageBytes
                );
                // defaultImageBytes est un tableau de byte[] remplie = ok
                log.info("### defaultImageBytes.length in controller ### : " + defaultImageBytes.length);
                // J'utilise mon service savePdfWithDefaultImage pour l'image par défaut
                Pdf pdf = service.savePdfWithDefaultImage(clientDatas, pdfFile, defaultImageMultipartFile);
                PdfDTO pdfDto = modelMapper.map(pdf, PdfDTO.class);
                log.info("passé par image par défaut !");

                return new ResponseEntity<>(pdfDto, HttpStatus.CREATED);

            } catch (IOException e) {
                // Si erreur, je la retourne
                e.printStackTrace();
            }
            // 2 - Si image fournie par le user
        } else {
            // L'image utilisateur est redimenssionnée et est stockée dans une variable de type MultipartFile
            MultipartFile resizedImage = resizeImage(image);
            // J'utilise mon service savePdf de base
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
                                            @RequestParam(value = "pdfFile", required = false)MultipartFile pdfFile,
                                            @RequestPart(value = "image", required = false) MultipartFile image) throws NotFoundException, IOException {
        // Conversion des datas front en DTOWayIN
        PdfDTOWayIN pdfDTOWayIN = modelMapper.map(clientDatas, PdfDTOWayIN.class);

        // Si je ne précise pas que chacune des MultipartFile peut ne pas présente = erreur back "null" à la réception de la requête front
        Pdf pdf = null;
        if (pdfFile != null && image != null) {
            // L'image utilisateur est redimenssionnée et est stockée dans une variable de type MultipartFile
            MultipartFile resizedImage = resizeImage(image);
            // J'utilise mon service savePdf de base
            pdf = service.updatePdf(id, pdfDTOWayIN, pdfFile, resizedImage);
            // pdf = service.updatePdf(id, pdfDTOWayIN, pdfFile, image);
        }
        if (pdfFile == null && image != null) {
            MultipartFile resizedImage = resizeImage(image);
            // J'utilise mon service savePdf de base
            pdf = service.updatePdfExceptPdfFile(id, pdfDTOWayIN, resizedImage);
            // pdf = service.updatePdfExceptPdfFile(id, pdfDTOWayIN, image);
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

    @GetMapping("/preview/{id}")
    //@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<byte[]> getPdfPreview(@PathVariable Integer id) throws NotFoundException {
        try {
            byte[] pdfPreview = service.getPdfPreview(id);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            return new ResponseEntity<>(pdfPreview, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadPdf(@PathVariable Integer id) throws NotFoundException {
        Pdf pdf = service.getPdfById(id);
        if (pdf != null) {
            // Je créé un nouveau header pour y stocker mes datas
            HttpHeaders headers = new HttpHeaders();
            // Je renseigne le header de ma réponse avec le type + nom du fichier
            headers.setContentType(MediaType.parseMediaType(pdf.getType()));
            headers.setContentDisposition(ContentDisposition.inline().filename(pdf.getTitle()).build());
            System.out.println(pdf.getTitle());
            System.out.println(headers);
            // je créé un tableau de bytes avec les datas du pdf
            ByteArrayResource resource = new ByteArrayResource(pdf.getPdfFile());
            // Je retourne le header + le body contenant les datas du fichier
            return ResponseEntity.ok().headers(headers).body(resource);
        } else {
            // Si pdf n'existe pas, je renvoie une erreur notFound
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/author/{id}")
    //@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<List<PdfDTO>> getPdfsListByAuthor(@PathVariable User id) throws EmptyListException {
        List<Pdf> pdfList = service.findByAuthor(id);
        List<PdfDTO> pdfDTOList = pdfList.stream().map(pdf -> modelMapper.map(pdf, PdfDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(pdfDTOList, HttpStatus.OK);
    }
}
