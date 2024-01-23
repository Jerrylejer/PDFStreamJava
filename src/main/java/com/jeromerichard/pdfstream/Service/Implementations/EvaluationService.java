package com.jeromerichard.pdfstream.Service.Implementations;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.EvaluationDTOWayIN;
import com.jeromerichard.pdfstream.Entity.Evaluation;
import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Entity.User;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;
import com.jeromerichard.pdfstream.Repository.EvaluationRepository;
import com.jeromerichard.pdfstream.Service.Interfaces.EvaluationServiceInt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@Transactional
public class EvaluationService implements EvaluationServiceInt {
    @Autowired
    private EvaluationRepository repository;

    @Override
    public Evaluation saveEvaluation(EvaluationDTOWayIN evaluation) {
        Evaluation evaluationToSave = new Evaluation();
        evaluationToSave.setTitle(evaluation.getTitle());
        evaluationToSave.setComment(evaluation.getComment());
        evaluationToSave.setStar(evaluation.getStar());
        evaluationToSave.setCreatedAt(new Date());
        evaluationToSave.setPdf(evaluation.getPdfId());
        evaluationToSave.setUser(evaluation.getUserId());
        log.info("Nouvelle évaluation du PDF " + evaluationToSave.getPdf() + " ajoutée");
        repository.save(evaluationToSave);
        return evaluationToSave;
    }

    @Override
    public List<Evaluation> getAllEvaluations() throws EmptyListException {
        List<Evaluation> evaluationsList = repository.findAll();
        if(evaluationsList == null) {
            throw new EmptyListException("Aucune liste ne correspond à votre demande");
        }
        return evaluationsList;
    }

    @Override
    public Evaluation getEvaluationById(Integer id) throws NotFoundException {
        Evaluation evaluation = repository.findById(id).orElseThrow(
                ()-> new NotFoundException("Cette évaluation n'existe pas, reformulez votre demande")
        );
        log.info("l'évaluation de " + evaluation.getUser().getUsername() + " est affiché");
        return evaluation;
    }

    @Override
    public Evaluation updateEvaluation(Integer id, EvaluationDTOWayIN evaluation) throws NotFoundException {
                Evaluation evaluationToUpdate = repository.findById(id).orElseThrow(
                ()-> new NotFoundException("Cette évaluation n'existe pas, reformulez votre demande")
        );
        evaluationToUpdate.setTitle(evaluation.getTitle());
        evaluationToUpdate.setComment(evaluation.getComment());
        evaluationToUpdate.setStar(evaluation.getStar());
        evaluationToUpdate.setUpdatedAt(new Date());
        evaluationToUpdate.setPdf(evaluation.getPdfId());
        evaluationToUpdate.setUser(evaluation.getUserId());
        log.info("L'évaluation " + evaluationToUpdate.getId() + "a correctement été modifiée");
        repository.save(evaluationToUpdate);
        return evaluationToUpdate;
    }

    @Override
    public void deleteEvaluation(Integer id) throws NotFoundException {
        Evaluation evaluationToDelete = repository.findById(id).orElseThrow(
                ()-> new NotFoundException("Cette évaluation n'existe pas, reformulez votre demande")
        );
        log.info("L'évaluation " + evaluationToDelete.getId() + "a correctement été supprimée");
        repository.deleteById(id);
    }

    @Override
    public List<Evaluation> findByUser(User user) throws EmptyListException {
        List<Evaluation> evaluationsList = repository.findByUser(user);
        if(evaluationsList == null) {
            throw new EmptyListException("Aucune liste ne correspond à votre demande");
        }
        return evaluationsList;
    }

    @Override
    public List<Evaluation> findByPdf(Pdf pdf) throws EmptyListException {
        List<Evaluation> evaluationsList = repository.findByPdf(pdf);
        if(evaluationsList == null) {
            throw new EmptyListException("Aucune liste ne correspond à votre demande");
        }
        return evaluationsList;
    }
}
