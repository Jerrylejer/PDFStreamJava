package com.jeromerichard.pdfstream.Repository;

import com.jeromerichard.pdfstream.Entity.Evaluation;
import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    Set<Evaluation> findByUser(User userId);
    Set<Evaluation> findByPdf(Pdf pdfId);
}
