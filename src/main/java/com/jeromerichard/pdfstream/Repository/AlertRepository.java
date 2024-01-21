package com.jeromerichard.pdfstream.Repository;

import com.jeromerichard.pdfstream.Entity.Alert;
import com.jeromerichard.pdfstream.Entity.Article;
import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
    Set<Alert> findByUser(User userId);
    Set<Alert> findByArticle(Article articleId);
    Set<Alert> findByPdf(Pdf pdfId);
}
