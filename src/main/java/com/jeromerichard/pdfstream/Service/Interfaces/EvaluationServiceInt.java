package com.jeromerichard.pdfstream.Service.Interfaces;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.EvaluationDTODown;
import com.jeromerichard.pdfstream.Entity.Evaluation;
import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Entity.User;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;

import java.util.Set;

public interface EvaluationServiceInt {
    // CRUD JPA
    public Evaluation saveEvaluation(EvaluationDTODown evaluation);
    public Set<Evaluation> getAllEvaluations() throws EmptyListException;
    public Evaluation getEvaluationById(Integer id) throws NotFoundException;
    public Evaluation updateEvaluation(Integer id, EvaluationDTODown evaluation) throws NotFoundException;
    public void deleteEvaluation(Integer id) throws NotFoundException;
    // FOREIGN KEY
    public Set<Evaluation> findByUser(User userId) throws EmptyListException;
    public Set<Evaluation> findByPdf(Pdf pdfId) throws EmptyListException;
}
