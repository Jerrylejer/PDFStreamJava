package com.jeromerichard.pdfstream.Repository;

import com.jeromerichard.pdfstream.Entity.Alert;
import com.jeromerichard.pdfstream.Entity.Article;
import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Integer> {
    List<Alert> findByUser(User user);
    List<Alert> findByArticle(Article article);
    List<Alert> findByPdf(Pdf pdf);
}
