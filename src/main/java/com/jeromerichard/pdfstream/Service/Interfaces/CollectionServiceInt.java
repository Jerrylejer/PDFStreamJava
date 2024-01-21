package com.jeromerichard.pdfstream.Service.Interfaces;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.CollectionDTODown;
import com.jeromerichard.pdfstream.Entity.Collection;
import com.jeromerichard.pdfstream.Entity.User;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;

import java.util.Set;

public interface CollectionServiceInt {
    // CRUD JPA
    public Collection saveCollection(CollectionDTODown collection);
    public Set<Collection> getAllCollections() throws EmptyListException;
    public Collection getCollectionById(Long id) throws NotFoundException;
    public Collection updateCollection(Long id, CollectionDTODown collection) throws NotFoundException;
    public void deleteCollection(Long id) throws NotFoundException;
    // FOREIGN KEY
    public Set<Collection> findByUser(User userId) throws EmptyListException;
}
