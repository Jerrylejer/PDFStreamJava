package com.jeromerichard.pdfstream.Repository;

import com.jeromerichard.pdfstream.Entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
