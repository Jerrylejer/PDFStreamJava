package com.jeromerichard.pdfstream.Service.Interfaces;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.CategoryDTOWayIN;
import com.jeromerichard.pdfstream.Entity.Category;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;

import java.util.List;

public interface CategoryServiceInt {
    // CRUD JPA
    public Category saveCategory(CategoryDTOWayIN category);
    public List<Category> getAllCategories() throws EmptyListException;
    public Category getCategoryById(Integer id) throws NotFoundException;
    public Category updateCategory(Integer id, CategoryDTOWayIN category) throws NotFoundException;
    public void deleteCategory(Integer id) throws NotFoundException;
}
