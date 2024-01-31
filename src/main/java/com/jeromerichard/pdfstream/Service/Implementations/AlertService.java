package com.jeromerichard.pdfstream.Service.Implementations;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.AlertDTOWayIN;
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
    public Alert saveAlert(AlertDTOWayIN alert) {
        Alert alertToSave = new Alert();
        alertToSave.setTitle(alert.getTitle());
        alertToSave.setDescription(alert.getDescription());
        alertToSave.setState(alert.getState());
        alertToSave.setCreatedAt(new Date());
        alertToSave.setAlertLauncher(alert.getAlertLauncher());
        alertToSave.setCharteArticle(alert.getCharteArticle());
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
    public Alert updateAlert(Integer id, AlertDTOWayIN alert) throws NotFoundException {
        Alert alertToUpdate = repository.findById(id).orElseThrow(
                ()-> new NotFoundException("Cette alerte n'existe pas, reformulez votre demande.")
        );
        if (alert.getTitle() != null) // On ne modifie que les propriétés nécessaires
            alertToUpdate.setTitle(alert.getTitle());
        if (alert.getDescription() != null)
            alertToUpdate.setDescription(alert.getDescription());
        if (alert.getState() != null)
            alertToUpdate.setState(alert.getState());
            alertToUpdate.setUpdatedAt(new Date());
        if (alert.getAlertLauncher() != null)
            alertToUpdate.setAlertLauncher(alert.getAlertLauncher());
        if (alert.getCharteArticle() != null)
            alertToUpdate.setCharteArticle(alert.getCharteArticle());
        if (alert.getPdfId() != null)
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
    public List<Alert> findByAlertLauncher(User alertLauncher) throws EmptyListException {
        List<Alert> alertsList = repository.findByAlertLauncher(alertLauncher);
        if(alertsList ==null) {
            throw new EmptyListException("Aucune liste ne correspond à votre demande");
        }
        return alertsList;
    }

    @Override
    public List<Alert> findByCharteArticle(Article charteArticle) throws EmptyListException {
        List<Alert> alertsList = repository.findByCharteArticle(charteArticle);
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
