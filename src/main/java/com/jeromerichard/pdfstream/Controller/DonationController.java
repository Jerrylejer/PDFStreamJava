package com.jeromerichard.pdfstream.Controller;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.DonationDTOWayIN;
import com.jeromerichard.pdfstream.Dto.EntityToDto.DonationDTO;
import com.jeromerichard.pdfstream.Entity.Donation;
import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Entity.User;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;
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
@RequestMapping("/donation")
public class DonationController {
    @Autowired
    private DonationService service;
    @Autowired
    private ModelMapper modelMapper;
    @PostMapping("/new")
    @ResponseBody
    // les datas seront placées dans le corps de la réponse HTTP sans être interprétées comme une vue HTML.
    public ResponseEntity<DonationDTO> saveDonation(@RequestBody DonationDTOWayIN clientDatas) {
        // Conversion des datas front en DTOWayIN
        DonationDTOWayIN donationDTOWayIN = modelMapper.map(clientDatas, DonationDTOWayIN.class);
        // Conversion sens DTOWayIN à Entité
        Donation donation = service.saveDonation(donationDTOWayIN);
        // Conversion sens Entité à DTO
        DonationDTO donationDTO = modelMapper.map(donation, DonationDTO.class);
        return new ResponseEntity<DonationDTO>(donationDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDonation(@PathVariable Integer id) throws NotFoundException {
        service.deleteDonation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<DonationDTO>> getDonationsList() throws EmptyListException {
        // Le flux de datas retourné par getAllAlerts() est traité pour que chaque data soit convertie en class AlertDTO et retournée sous forme de List
        List<DonationDTO> donationDTOList = service.getAllDonations().stream().map(donation -> modelMapper.map(donation, DonationDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(donationDTOList, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<DonationDTO> getDonation(@PathVariable Integer id) throws NotFoundException {
        Donation donation = service.getDonationById(id);
        // Conversion sens Entité à DTO
        DonationDTO donationDTO = modelMapper.map(donation, DonationDTO.class);
        return new ResponseEntity<DonationDTO>(donationDTO, HttpStatus.OK);
    }

    @GetMapping("/user/{user}")
    public ResponseEntity<List<DonationDTO>> getDonationByUser(@PathVariable User user) throws NotFoundException, EmptyListException {
        List<DonationDTO> donationDTOList = service.findByUser(user).stream().map(item -> modelMapper.map(item, DonationDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(donationDTOList, HttpStatus.OK);
    }
    @GetMapping("/pdf/{pdf}")
    public ResponseEntity<List<DonationDTO>> getDonationsByPdf(@PathVariable Pdf pdf) throws NotFoundException, EmptyListException {
        List<DonationDTO> donationDTOList = service.findByPdf(pdf).stream().map(item -> modelMapper.map(item, DonationDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(donationDTOList, HttpStatus.OK);
    }
}
