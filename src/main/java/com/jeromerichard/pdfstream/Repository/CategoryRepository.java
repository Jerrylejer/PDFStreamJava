package com.jeromerichard.pdfstream.Repository;

import com.jeromerichard.pdfstream.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
