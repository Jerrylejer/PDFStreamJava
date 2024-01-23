package com.jeromerichard.pdfstream.Controller;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.ProfilDTOWayIN;
import com.jeromerichard.pdfstream.Dto.EntityToDto.ProfilDTO;
import com.jeromerichard.pdfstream.Entity.Profil;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;
import com.jeromerichard.pdfstream.Service.Implementations.ProfilService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/profil")
public class ProfilController {
    @Autowired
    private ProfilService service;
    @Autowired
    private ModelMapper modelMapper;
    @PostMapping("/new")
    @ResponseBody
    // les datas seront placées dans le corps de la réponse HTTP sans être interprétées comme une vue HTML.
    public ResponseEntity<ProfilDTO> saveProfil(@RequestBody ProfilDTOWayIN clientDatas) {
        // Conversion des datas front en DTOWayIN
        ProfilDTOWayIN profilDTOWayIN = modelMapper.map(clientDatas, ProfilDTOWayIN.class);
        // Conversion sens DTOWayIN à Entité
        Profil profil = service.saveProfil(profilDTOWayIN);
        // Conversion sens Entité à DTO
        ProfilDTO profilDTO = modelMapper.map(profil, ProfilDTO.class);
        return new ResponseEntity<ProfilDTO>(profilDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public ResponseEntity<ProfilDTO> updateProfil(@PathVariable Integer id, @RequestBody ProfilDTOWayIN clientDatas) throws NotFoundException {
        // Conversion des datas front en DTOWayIN
        ProfilDTOWayIN profilDTOWayIN = modelMapper.map(clientDatas, ProfilDTOWayIN.class);
        // Conversion sens DTOWayIN à Entité
        Profil profil = service.updateProfil(id, clientDatas);
        // Conversion sens Entité à DTO
        ProfilDTO profilDTO = modelMapper.map(profil, ProfilDTO.class);
        return new ResponseEntity<ProfilDTO>(profilDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProfil(@PathVariable Integer id) throws NotFoundException {
        service.deleteProfil(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<ProfilDTO>> getProfilsList() throws EmptyListException {
        // Le flux de datas retourné par getAllAlerts() est traité pour que chaque data soit convertie en class AlertDTO et retournée sous forme de List
        List<ProfilDTO> profilDTOList = service.getAllProfils().stream().map(profil -> modelMapper.map(profil, ProfilDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(profilDTOList, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ProfilDTO> getProfil(@PathVariable Integer id) throws NotFoundException {
        Profil profil = service.getProfilById(id);
        // Conversion sens Entité à DTO
        ProfilDTO profilDTO = modelMapper.map(profil, ProfilDTO.class);
        return new ResponseEntity<ProfilDTO>(profilDTO, HttpStatus.OK);
    }

}
