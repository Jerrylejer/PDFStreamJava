package com.jeromerichard.pdfstream.Repository;

import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PdfRepository extends JpaRepository<Pdf, Integer> {
    // List<Pdf> findByAuthorId(Integer id);
    List<Pdf> findByAuthor(User id);
}
