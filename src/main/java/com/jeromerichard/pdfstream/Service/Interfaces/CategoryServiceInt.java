package com.jeromerichard.pdfstream.Service.Interfaces;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.CategoryDTODown;
import com.jeromerichard.pdfstream.Entity.Category;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;

import java.util.List;
import java.util.Set;

public interface CategoryServiceInt {
    // CRUD JPA
    public Category saveCategory(CategoryDTODown category);
    public List<Category> getAllCategories() throws EmptyListException;
    public Category getCategoryById(Integer id) throws NotFoundException;
    public Category updateCategory(Integer id, CategoryDTODown category) throws NotFoundException;
    public void deleteCategory(Integer id) throws NotFoundException;
}
