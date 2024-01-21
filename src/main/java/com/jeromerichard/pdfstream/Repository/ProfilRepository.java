package com.jeromerichard.pdfstream.Repository;

import com.jeromerichard.pdfstream.Entity.Profil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilRepository extends JpaRepository<Profil, Long> {
}
