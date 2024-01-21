package com.jeromerichard.pdfstream.Repository;

import com.jeromerichard.pdfstream.Entity.Pdf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PdfRepository extends JpaRepository<Pdf, Integer> {

}
