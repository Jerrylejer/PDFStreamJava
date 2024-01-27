package com.jeromerichard.pdfstream.Controller;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.SearchDTOWayIN;
import com.jeromerichard.pdfstream.Dto.EntityToDto.SearchDTO;
import com.jeromerichard.pdfstream.Entity.*;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;
import com.jeromerichard.pdfstream.Service.Implementations.SearchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private SearchService service;
    @Autowired
    private ModelMapper modelMapper;
    @PostMapping("/new")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN'), hasRole('USER')")
    // les datas seront placées dans le corps de la réponse HTTP sans être interprétées comme une vue HTML.
    public ResponseEntity<SearchDTO> saveSearch(@RequestBody SearchDTOWayIN clientDatas) {
        // Conversion des datas front en DTOWayIN
        SearchDTOWayIN searchDTOWayIN = modelMapper.map(clientDatas, SearchDTOWayIN.class);
        // Conversion sens DTOWayIN à Entité
        Search search = service.saveSearch(searchDTOWayIN);
        // Conversion sens Entité à DTO
        SearchDTO searchDTO = modelMapper.map(search, SearchDTO.class);
        return new ResponseEntity<SearchDTO>(searchDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN'), hasRole('USER')")
    public ResponseEntity<?> deleteSearch(@PathVariable Integer id) throws NotFoundException {
        service.deleteSearch(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<SearchDTO>> getSearchList() throws EmptyListException {
        // Le flux de datas retourné par getAllAlerts() est traité pour que chaque data soit convertie en class AlertDTO et retournée sous forme de List
        List<SearchDTO> searchDTOList = service.getAllSearchs().stream().map(search -> modelMapper.map(search, SearchDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(searchDTOList, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SearchDTO> getSearch(@PathVariable Integer id) throws NotFoundException {
        Search search = service.getSearchById(id);
        // Conversion sens Entité à DTO
        SearchDTO searchDTO = modelMapper.map(search, SearchDTO.class);
        return new ResponseEntity<SearchDTO>(searchDTO, HttpStatus.OK);
    }
}
