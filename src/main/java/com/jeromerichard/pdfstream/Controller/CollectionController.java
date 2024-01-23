package com.jeromerichard.pdfstream.Controller;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.CollectionDTOWayIN;
import com.jeromerichard.pdfstream.Dto.DtoToEntity.DonationDTOWayIN;
import com.jeromerichard.pdfstream.Dto.DtoToEntity.EvaluationDTOWayIN;
import com.jeromerichard.pdfstream.Dto.DtoToEntity.UserDTOWayIN;
import com.jeromerichard.pdfstream.Dto.EntityToDto.CollectionDTO;
import com.jeromerichard.pdfstream.Dto.EntityToDto.DonationDTO;
import com.jeromerichard.pdfstream.Dto.EntityToDto.EvaluationDTO;
import com.jeromerichard.pdfstream.Entity.Collection;
import com.jeromerichard.pdfstream.Entity.Donation;
import com.jeromerichard.pdfstream.Entity.Evaluation;
import com.jeromerichard.pdfstream.Entity.User;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;
import com.jeromerichard.pdfstream.Service.Implementations.CollectionService;
import com.jeromerichard.pdfstream.Service.Implementations.DonationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/collection")
public class CollectionController {
    @Autowired
    private CollectionService service;
    @Autowired
    private ModelMapper modelMapper;
    @PostMapping("/new")
    @ResponseBody
    // les datas seront placées dans le corps de la réponse HTTP sans être interprétées comme une vue HTML.
    public ResponseEntity<CollectionDTO> saveCollection(@RequestBody CollectionDTOWayIN clientDatas) {
        // Conversion des datas front en DTOWayIN
        CollectionDTOWayIN collectionDTOWayIN = modelMapper.map(clientDatas, CollectionDTOWayIN.class);
        // Conversion sens DTOWayIN à Entité
        Collection collection = service.saveCollection(collectionDTOWayIN);
        // Conversion sens Entité à DTO
        CollectionDTO collectionDTO = modelMapper.map(collection, CollectionDTO.class);
        return new ResponseEntity<CollectionDTO>(collectionDTO, HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    @ResponseBody
    public ResponseEntity<CollectionDTO> updateCollection(@PathVariable Integer id, @RequestBody CollectionDTOWayIN clientDatas) throws NotFoundException {
        // Conversion des datas front en DTOWayIN
        CollectionDTOWayIN collectionDTOWayIN = modelMapper.map(clientDatas, CollectionDTOWayIN.class);
        // Conversion sens DTOWayIN à Entité
        Collection collection = service.updateCollection(id, collectionDTOWayIN);
        // Conversion sens Entité à DTO
        CollectionDTO collectionDTO = modelMapper.map(collection, CollectionDTO.class);
        return new ResponseEntity<CollectionDTO>(collectionDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCollection(@PathVariable Integer id) throws NotFoundException {
        service.deleteCollection(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<CollectionDTO>> getCollectionsList() throws EmptyListException {
        // Le flux de datas retourné par getAllAlerts() est traité pour que chaque data soit convertie en class AlertDTO et retournée sous forme de List
        List<CollectionDTO> collectionDTOList = service.getAllCollections().stream().map(collection -> modelMapper.map(collection, CollectionDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(collectionDTOList, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CollectionDTO> getCollection(@PathVariable Integer id) throws NotFoundException {
        Collection collection = service.getCollectionById(id);
        // Conversion sens Entité à DTO
        CollectionDTO collectionDTO = modelMapper.map(collection, CollectionDTO.class);
        return new ResponseEntity<CollectionDTO>(collectionDTO, HttpStatus.OK);
    }

    @GetMapping("/user/{user}")
    public ResponseEntity<List<CollectionDTO>> getCollectionsByUser(@PathVariable User user) throws NotFoundException, EmptyListException {
        List<CollectionDTO> collectionDTOList = service.findByUser(user).stream().map(item -> modelMapper.map(item, CollectionDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(collectionDTOList, HttpStatus.OK);
    }
}
