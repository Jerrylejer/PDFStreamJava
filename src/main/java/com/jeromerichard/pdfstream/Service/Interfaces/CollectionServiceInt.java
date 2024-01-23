package com.jeromerichard.pdfstream.Service.Interfaces;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.CollectionDTOWayIN;
import com.jeromerichard.pdfstream.Entity.Collection;
import com.jeromerichard.pdfstream.Entity.User;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;

import java.util.List;

public interface CollectionServiceInt {
    // CRUD JPA
    public Collection saveCollection(CollectionDTOWayIN collection);
    public List<Collection> getAllCollections() throws EmptyListException;
    public Collection getCollectionById(Integer id) throws NotFoundException;
    public Collection updateCollection(Integer id, CollectionDTOWayIN collection) throws NotFoundException;
    public void deleteCollection(Integer id) throws NotFoundException;
    // FOREIGN KEY
    public List<Collection> findByUser(User user) throws EmptyListException;
}
