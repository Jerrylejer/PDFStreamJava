package com.jeromerichard.pdfstream.Service.Interfaces;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.SearchDTODown;
import com.jeromerichard.pdfstream.Entity.Search;
import com.jeromerichard.pdfstream.Entity.User;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;

import java.util.List;
import java.util.Set;

public interface SearchServiceInt {
    // CRUD JPA
    public Search saveSearch(SearchDTODown search);
    public List<Search> getAllSearchs() throws EmptyListException;
    public Search getSearchById(Integer id) throws NotFoundException;
    public void deleteSearch(Integer id) throws NotFoundException;
    // FOREIGN KEY
    public List<Search> findByUser(User user) throws EmptyListException;
}
