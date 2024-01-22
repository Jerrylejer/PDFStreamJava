package com.jeromerichard.pdfstream.Repository;

import com.jeromerichard.pdfstream.Entity.Donation;
import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Integer> {
    List<Donation> findByUser(User user);
    List<Donation> findByPdf(Pdf pdf);
}
