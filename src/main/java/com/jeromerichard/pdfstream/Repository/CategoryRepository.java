package com.jeromerichard.pdfstream.Repository;

import com.jeromerichard.pdfstream.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findCategoriesByParentId(Category parentId);
}
