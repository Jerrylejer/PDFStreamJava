package com.jeromerichard.pdfstream.Service.Implementations;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.AlertDTODown;
import com.jeromerichard.pdfstream.Entity.Alert;
import com.jeromerichard.pdfstream.Entity.Article;
import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Entity.User;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;
import com.jeromerichard.pdfstream.Repository.AlertRepository;
import com.jeromerichard.pdfstream.Service.Interfaces.AlertServiceInt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@Transactional
public class AlertService implements AlertServiceInt {
    @Autowired
    private AlertRepository repository;

    @Override
    public Alert saveAlert(AlertDTODown alert) {
        Alert alertToSave = new Alert();
        alertToSave.setTitle(alert.getTitle());
        alertToSave.setDescription(alert.getDescription());
        alertToSave.setState(alert.getState());
        alertToSave.setCreatedAt(new Date());
        alertToSave.setUser(alert.getUserId());
        alertToSave.setArticle(alert.getArticleId());
        alertToSave.setPdf(alert.getPdfId());
        log.info("Nouvelle alerte ajoutée");
        repository.save(alertToSave);
        return alertToSave;
    }

    @Override
    public List<Alert> getAllAlerts() throws EmptyListException {
        List<Alert> alertsList = repository.findAll();
        if(alertsList ==null) {
            throw new EmptyListException("Aucune liste ne correspond à votre demande");
        }
        return alertsList;
    }

    @Override
    public Alert getAlertById(Integer id) throws NotFoundException {
        Alert Alert = repository.findById(id).orElseThrow(
                ()-> new NotFoundException("Cet article n'existe pas, reformulez votre demande")
        );
        log.info("l'alerte id " + id + " est affiché.");
        return Alert;
    }

    @Override
    public Alert updateAlert(Integer id, AlertDTODown alert) throws NotFoundException {
        Alert alertToUpdate = repository.findById(id).orElseThrow(
                ()-> new NotFoundException("Cette alerte n'existe pas, reformulez votre demande.")
        );
        alertToUpdate.setTitle(alert.getTitle());
        alertToUpdate.setDescription(alert.getDescription());
        alertToUpdate.setState(alert.getState());
        alertToUpdate.setUpdatedAt(new Date());
        alertToUpdate.setUser(alert.getUserId());
        alertToUpdate.setArticle(alert.getArticleId());
        alertToUpdate.setPdf(alert.getPdfId());
        log.info("L'alerte id" + id + "à correctement été modifiée");
        repository.save(alertToUpdate);
        return alertToUpdate;
    }

    @Override
    public void deleteAlert(Integer id) throws NotFoundException {
        Alert alertToDelete = repository.findById(id).orElseThrow(
                ()-> new NotFoundException("Cette alerte n'existe pas, reformulez votre demande")
        );
        log.info("L'alerte " + alertToDelete.getTitle() + "à correctement été supprimée");
        repository.deleteById(id);
    }

    @Override
    public List<Alert> findByUser(User user) throws EmptyListException {
        List<Alert> alertsList = repository.findByUser(user);
        if(alertsList ==null) {
            throw new EmptyListException("Aucune liste ne correspond à votre demande");
        }
        return alertsList;
    }

    @Override
    public List<Alert> findByArticle(Article article) throws EmptyListException {
        List<Alert> alertsList = repository.findByArticle(article);
        if(alertsList ==null) {
            throw new EmptyListException("Aucune liste ne correspond à votre demande");
        }
        return alertsList;
    }

    @Override
    public List<Alert> findByPdf(Pdf pdf) throws EmptyListException {
        List<Alert> alertsList = repository.findByPdf(pdf);
        if(alertsList ==null) {
            throw new EmptyListException("Aucune liste ne correspond à votre demande");
        }
        return alertsList;
    }
}
