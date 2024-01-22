package com.jeromerichard.pdfstream.Service.Interfaces;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.AlertDTODown;
import com.jeromerichard.pdfstream.Entity.Alert;
import com.jeromerichard.pdfstream.Entity.Article;
import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Entity.User;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;
import org.hibernate.validator.constraints.ru.INN;

import java.util.List;
import java.util.Set;

public interface AlertServiceInt {
    // CRUD JPA
    public Alert saveAlert(AlertDTODown alert);
    public List<Alert> getAllAlerts() throws EmptyListException;
    public Alert getAlertById(Integer id) throws NotFoundException;
    public Alert updateAlert(Integer id, AlertDTODown alert) throws NotFoundException;
    public void deleteAlert(Integer id) throws NotFoundException;
    // FOREIGN KEY
    public List<Alert> findByUser(User userId) throws EmptyListException;
    public List<Alert> findByArticle(Article articleId) throws EmptyListException;
    public List<Alert> findByPdf(Pdf pdfId) throws EmptyListException;
}
