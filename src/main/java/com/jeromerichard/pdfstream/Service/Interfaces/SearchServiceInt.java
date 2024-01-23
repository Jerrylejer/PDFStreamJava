package com.jeromerichard.pdfstream.Service.Interfaces;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.SearchDTOWayIN;
import com.jeromerichard.pdfstream.Entity.Search;
import com.jeromerichard.pdfstream.Entity.User;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;

import java.util.List;

public interface SearchServiceInt {
    // CRUD JPA
    public Search saveSearch(SearchDTOWayIN search);
    public List<Search> getAllSearchs() throws EmptyListException;
    public Search getSearchById(Integer id) throws NotFoundException;
    public void deleteSearch(Integer id) throws NotFoundException;
    // FOREIGN KEY
    public List<Search> findByUser(User user) throws EmptyListException;
}
