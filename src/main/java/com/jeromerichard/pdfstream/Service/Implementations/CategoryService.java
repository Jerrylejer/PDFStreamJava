package com.jeromerichard.pdfstream.Service.Implementations;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.CategoryDTOWayIN;
import com.jeromerichard.pdfstream.Entity.Category;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;
import com.jeromerichard.pdfstream.Repository.CategoryRepository;
import com.jeromerichard.pdfstream.Service.Interfaces.CategoryServiceInt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@Transactional
public class CategoryService implements CategoryServiceInt {

    @Autowired
    private CategoryRepository repository;
    @Override
    public Category saveCategory(CategoryDTOWayIN category) {
        Category categoryToSave = new Category();
        categoryToSave.setTitle(category.getTitle());
        categoryToSave.setParentId(category.getParentId());
        categoryToSave.setCreatedAt(new Date());
        log.info("Nouvelle catégorie " + category.getTitle() + " ajoutée");
        repository.save(categoryToSave);
        return categoryToSave;
    }

    @Override
    public List<Category> getAllCategories() throws EmptyListException {
        List<Category> categoriesList = repository.findAll();
        if(categoriesList == null) {
            throw new EmptyListException("Aucune liste ne correspond à votre demande");
        }
        return categoriesList;
    }

    @Override
    public Category getCategoryById(Integer id) throws NotFoundException {
        Category category = repository.findById(id).orElseThrow(
                ()-> new NotFoundException("Cette catégorie n'existe pas, reformulez votre demande.")
        );
        log.info("la catégorie " + category.getTitle() + " trouvée.");
        return category;
    }

    @Override
    public Category updateCategory(Integer id, CategoryDTOWayIN category) throws NotFoundException {
        Category categoryToUpdate = repository.findById(id).orElseThrow(
                ()-> new NotFoundException("Cette catégorie n'existe pas, reformulez votre demande")
        );
        if (category.getTitle() != null) // On ne modifie que les propriétés nécessaires
        categoryToUpdate.setTitle(category.getTitle());
        if (category.getParentId() != null)
            categoryToUpdate.setParentId(category.getParentId());
        categoryToUpdate.setUpdateAt(new Date());
        log.info("Catégorie " + category.getTitle() + " modifiée");
        repository.save(categoryToUpdate);
        return categoryToUpdate;
    }

    @Override
    public void deleteCategory(Integer id) throws NotFoundException {
        Category categoryToDelete = repository.findById(id).orElseThrow(
                ()-> new NotFoundException("Cette catégorie n'existe pas, reformulez votre demande")
        );
        log.info("La catégorie " + categoryToDelete.getTitle() + "à correctement été supprimée");
        repository.deleteById(id);
    }
}
