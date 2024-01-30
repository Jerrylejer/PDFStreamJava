package com.jeromerichard.pdfstream.Controller;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.EvaluationDTOWayIN;
import com.jeromerichard.pdfstream.Dto.DtoToEntity.UserDTOWayIN;
import com.jeromerichard.pdfstream.Dto.EntityToDto.EvaluationDTO;
import com.jeromerichard.pdfstream.Entity.Evaluation;
import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Entity.User;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;
import com.jeromerichard.pdfstream.Service.Implementations.EvaluationService;
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
@RequestMapping("/evaluation")
public class EvaluationController {
    @Autowired
    private EvaluationService service;
    @Autowired
    private ModelMapper modelMapper;
    @PostMapping("/new")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN'), hasRole('USER')")
    // les datas seront placées dans le corps de la réponse HTTP sans être interprétées comme une vue HTML.
    public ResponseEntity<EvaluationDTO> saveEvaluation(@RequestBody EvaluationDTOWayIN clientDatas) {
        // Conversion des datas front en DTOWayIN
        EvaluationDTOWayIN evaluationDTOWayIN = modelMapper.map(clientDatas, EvaluationDTOWayIN.class);
        // Conversion sens DTOWayIN à Entité
        Evaluation evaluation = service.saveEvaluation(evaluationDTOWayIN);
        // Conversion sens Entité à DTO
        EvaluationDTO evaluationDTO = modelMapper.map(evaluation, EvaluationDTO.class);
        return new ResponseEntity<EvaluationDTO>(evaluationDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN'), hasRole('USER')")
    public ResponseEntity<EvaluationDTO> updateEvaluation(@PathVariable Integer id, @RequestBody EvaluationDTOWayIN clientDatas) throws NotFoundException {
        // Conversion des datas front en DTOWayIN
        EvaluationDTOWayIN evaluationDTOWayIN = modelMapper.map(clientDatas, EvaluationDTOWayIN.class);
        // Conversion sens DTOWayIN à Entité
        Evaluation evaluation = service.updateEvaluation(id, evaluationDTOWayIN);
        // Conversion sens Entité à DTO
        EvaluationDTO evaluationDTO = modelMapper.map(evaluation, EvaluationDTO.class);
        return new ResponseEntity<EvaluationDTO>(evaluationDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteEvaluation(@PathVariable Integer id) throws NotFoundException {
        service.deleteEvaluation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<EvaluationDTO>> getEvaluationsList() throws EmptyListException {
        // Le flux de datas retourné par getAllAlerts() est traité pour que chaque data soit convertie en class AlertDTO et retournée sous forme de List
        List<EvaluationDTO> evaluationDTOList = service.getAllEvaluations().stream().map(evaluation -> modelMapper.map(evaluation, EvaluationDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(evaluationDTOList, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    @PreAuthorize("hasRole('ADMIN'), hasRole('USER')")
    public ResponseEntity<EvaluationDTO> getEvaluation(@PathVariable Integer id) throws NotFoundException {
        Evaluation evaluation = service.getEvaluationById(id);
        // Conversion sens Entité à DTO
        EvaluationDTO evaluationDTO = modelMapper.map(evaluation, EvaluationDTO.class);
        return new ResponseEntity<EvaluationDTO>(evaluationDTO, HttpStatus.OK);
    }

    @GetMapping("/user/{user}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<EvaluationDTO>> getEvaluationsByUser(@PathVariable User user) throws NotFoundException, EmptyListException {
        List<EvaluationDTO> evaluationDTOList = service.findByUser(user).stream().map(item -> modelMapper.map(item, EvaluationDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(evaluationDTOList, HttpStatus.OK);
    }
    @GetMapping("/pdf/{pdf}")
    @PreAuthorize("hasRole('ADMIN'), hasRole('USER')")
    public ResponseEntity<List<EvaluationDTO>> getEvaluationsByPdf(@PathVariable Pdf pdf) throws NotFoundException, EmptyListException {
        List<EvaluationDTO> evaluationDTOList = service.findByPdf(pdf).stream().map(item -> modelMapper.map(item, EvaluationDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(evaluationDTOList, HttpStatus.OK);
    }
}
