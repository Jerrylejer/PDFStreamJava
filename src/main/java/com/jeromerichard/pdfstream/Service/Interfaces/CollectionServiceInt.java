package com.jeromerichard.pdfstream.Service.Interfaces;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.CollectionDTODown;
import com.jeromerichard.pdfstream.Entity.Collection;
import com.jeromerichard.pdfstream.Entity.User;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;

import java.util.List;
import java.util.Set;

public interface CollectionServiceInt {
    // CRUD JPA
    public Collection saveCollection(CollectionDTODown collection);
    public List<Collection> getAllCollections() throws EmptyListException;
    public Collection getCollectionById(Integer id) throws NotFoundException;
    public Collection updateCollection(Integer id, CollectionDTODown collection) throws NotFoundException;
    public void deleteCollection(Integer id) throws NotFoundException;
    // FOREIGN KEY
    public List<Collection> findByUsername(User username) throws EmptyListException;
}
