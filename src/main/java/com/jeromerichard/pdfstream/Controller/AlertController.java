package com.jeromerichard.pdfstream.Controller;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.AlertDTOWayIN;
import com.jeromerichard.pdfstream.Dto.EntityToDto.AlertDTO;
import com.jeromerichard.pdfstream.Entity.Alert;
import com.jeromerichard.pdfstream.Entity.Article;
import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Entity.User;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;
import com.jeromerichard.pdfstream.Service.Implementations.AlertService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600) // Temps max de la mise en cache
@RestController
@RequestMapping("/alert")
public class AlertController {
    @Autowired
    private AlertService service;
    @Autowired
    private ModelMapper modelMapper;
    @PostMapping("/new")
    @ResponseBody // les datas seront placées dans le corps de la réponse HTTP sans être interprétées comme une vue HTML.
    @PreAuthorize("hasRole('ADMIN'), hasRole('USER')")
    public ResponseEntity<AlertDTO> saveAlert(@RequestBody AlertDTOWayIN clientDatas) {
        // Conversion des datas front en DTOWayIN
        AlertDTOWayIN alertDTOWayIN = modelMapper.map(clientDatas, AlertDTOWayIN.class);
        // Conversion sens DTOWayIN à Entité
        Alert alert = service.saveAlert(alertDTOWayIN);
        // Conversion sens Entité à DTO
        AlertDTO alertDTO = modelMapper.map(alert, AlertDTO.class);
        return new ResponseEntity<AlertDTO>(alertDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN'), hasRole('USER')")
    public ResponseEntity<AlertDTO> updateAlert(@PathVariable Integer id, @RequestBody AlertDTOWayIN clientDatas) throws NotFoundException {
        // Conversion des datas front en DTOWayIN
        AlertDTOWayIN alertDTOWayIN = modelMapper.map(clientDatas, AlertDTOWayIN.class);
        // Conversion sens DTOWayIN à Entité
        Alert alert = service.updateAlert(id, alertDTOWayIN);
        // Conversion sens Entité à DTO
        AlertDTO alertDTO = modelMapper.map(alert, AlertDTO.class);
        return new ResponseEntity<AlertDTO>(alertDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN'), hasRole('USER')")
    public ResponseEntity<?> deleteAlert(@PathVariable Integer id) throws NotFoundException {
        service.deleteAlert(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN'), hasRole('USER')")
    public ResponseEntity<List<AlertDTO>> getAlertsList() throws EmptyListException {
        // Le flux de datas retourné par getAllAlerts() est traité pour que chaque data soit convertie en class AlertDTO et retournée sous forme de List
        List<AlertDTO> alertDTOList = service.getAllAlerts().stream().map(alert -> modelMapper.map(alert, AlertDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(alertDTOList, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    @PreAuthorize("hasRole('ADMIN'), hasRole('USER')")
    public ResponseEntity<AlertDTO> getAlert(@PathVariable Integer id) throws NotFoundException {
        Alert alert = service.getAlertById(id);
        // Conversion sens Entité à DTO
        AlertDTO alertDto = modelMapper.map(alert, AlertDTO.class);
        return new ResponseEntity<AlertDTO>(alertDto, HttpStatus.OK);
    }

    // ######### METHODES SUPPLEMENTAIRES #########

    @GetMapping("/user/{user}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AlertDTO>> getAlertsByUser(@PathVariable User user) throws NotFoundException, EmptyListException {
        List<AlertDTO> dtosUserList = service.findByUser(user).stream().map(alert -> modelMapper.map(alert, AlertDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<List<AlertDTO>>(dtosUserList, HttpStatus.OK);
    }
    @GetMapping("/article/{article}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AlertDTO>> getAlertsByArticle(@PathVariable Article article) throws NotFoundException, EmptyListException {
        List<AlertDTO> dtosArticleList = service.findByArticle(article).stream().map(alert -> modelMapper.map(alert, AlertDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<List<AlertDTO>>(dtosArticleList, HttpStatus.OK);
    }
    @GetMapping("/pdf/{pdf}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AlertDTO>> getAlertsByPdf(@PathVariable Pdf pdf) throws NotFoundException, EmptyListException {
        List<AlertDTO> dtosPdfList = service.findByPdf(pdf).stream().map(alert -> modelMapper.map(alert, AlertDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<List<AlertDTO>>(dtosPdfList, HttpStatus.OK);
    }
}
