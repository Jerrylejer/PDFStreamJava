package com.jeromerichard.pdfstream.Repository;

import com.jeromerichard.pdfstream.Entity.Alert;
import com.jeromerichard.pdfstream.Entity.Article;
import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Integer> {
    Set<Alert> findByUser(User user);
    Set<Alert> findByArticle(Article article);
    Set<Alert> findByPdf(Pdf pdf);
}
