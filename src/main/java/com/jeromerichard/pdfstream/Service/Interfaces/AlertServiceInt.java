package com.jeromerichard.pdfstream.Service.Interfaces;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.AlertDTODown;
import com.jeromerichard.pdfstream.Entity.Alert;
import com.jeromerichard.pdfstream.Entity.Article;
import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Entity.User;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;

import java.util.Set;

public interface AlertServiceInt {
    // CRUD JPA
    public Alert saveAlert(AlertDTODown alert);
    public Set<Alert> getAllAlerts() throws EmptyListException;
    public Alert getAlertById(Long id) throws NotFoundException;
    public Alert updateAlert(Long id, AlertDTODown alert) throws NotFoundException;
    public void deleteAlert(Long id) throws NotFoundException;
    // FOREIGN KEY
    public Set<Alert> findByUser(User userId) throws EmptyListException;
    public Set<Alert> findByArticle(Article articleId) throws EmptyListException;
    public Set<Alert> findByPdf(Pdf pdfId) throws EmptyListException;
}
