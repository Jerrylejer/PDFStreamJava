package com.jeromerichard.pdfstream.Service.Implementations;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.CollectionDTOWayIN;
import com.jeromerichard.pdfstream.Entity.Collection;
import com.jeromerichard.pdfstream.Entity.User;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;
import com.jeromerichard.pdfstream.Repository.CollectionRepository;
import com.jeromerichard.pdfstream.Service.Interfaces.CollectionServiceInt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@Transactional
public class CollectionService implements CollectionServiceInt {
    @Autowired
    private CollectionRepository repository;
    @Override
    public Collection saveCollection(CollectionDTOWayIN collection) {
        Collection collectionToSave = new Collection();
        collectionToSave.setUser(collection.getUser());
        collectionToSave.setCreatedAt(new Date());
        log.info("Nouvelle collection " + collectionToSave.getId() + " ajoutée");
        repository.save(collectionToSave);
        return collectionToSave;
    }

    @Override
    public List<Collection> getAllCollections() throws EmptyListException {
        List<Collection> collectionsList = repository.findAll();
        if(collectionsList == null) {
            throw new EmptyListException("Aucune liste ne correspond à votre demande");
        }
        return collectionsList;
    }

    @Override
    public Collection getCollectionById(Integer id) throws NotFoundException {
        Collection collection = repository.findById(id).orElseThrow(
                ()-> new NotFoundException("Cette collection n'existe pas, reformulez votre demande")
        );
        log.info("la collection de " + collection.getUser().getUsername() + " est affichée");
        return collection;
    }

    @Override
    public Collection updateCollection(Integer id, CollectionDTOWayIN collection) throws NotFoundException {
        Collection collectionToUpdate = repository.findById(id).orElseThrow(
                ()-> new NotFoundException("Cette collection n'existe pas, reformulez votre demande")
        );
        if (collection.getUser() != null)
        collectionToUpdate.setUser(collection.getUser());
        collectionToUpdate.setUpdatedAt(new Date());
        log.info("La collection " + collectionToUpdate.getUser().getUsername() + " a été modifiée");
        repository.save(collectionToUpdate);
        return collectionToUpdate;
    }

    @Override
    public void deleteCollection(Integer id) throws NotFoundException {
        Collection collectionToDelete = repository.findById(id).orElseThrow(
                ()-> new NotFoundException("Cette collection n'existe pas, reformulez votre demande")
        );
        log.info("La collection de " + collectionToDelete.getUser().getUsername() + "a correctement été supprimée");
        repository.deleteById(id);
    }

    @Override
    public List<Collection> findByUser(User user) throws EmptyListException {
        List<Collection> collection = repository.findByUser(user);
        if(collection == null) {
            throw new EmptyListException("Aucune liste ne correspond à votre demande");
        }
        log.info("la collection de " + user + " est affichée");
        return collection;
    }
}
