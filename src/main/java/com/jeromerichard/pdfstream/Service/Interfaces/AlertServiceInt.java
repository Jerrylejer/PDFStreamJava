package com.jeromerichard.pdfstream.Service.Interfaces;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.AlertDTOWayIN;
import com.jeromerichard.pdfstream.Entity.Alert;
import com.jeromerichard.pdfstream.Entity.Article;
import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Entity.User;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;

import java.util.List;

public interface AlertServiceInt {
    // CRUD JPA
    public Alert saveAlert(AlertDTOWayIN alert);
    public List<Alert> getAllAlerts() throws EmptyListException;
    public Alert getAlertById(Integer id) throws NotFoundException;
    public Alert updateAlert(Integer id, AlertDTOWayIN alert) throws NotFoundException;
    public void deleteAlert(Integer id) throws NotFoundException;
    // FOREIGN KEY
    public List<Alert> findByUser(User user) throws EmptyListException;
    public List<Alert> findByArticle(Article articleId) throws EmptyListException;
    public List<Alert> findByPdf(Pdf pdfId) throws EmptyListException;
}
