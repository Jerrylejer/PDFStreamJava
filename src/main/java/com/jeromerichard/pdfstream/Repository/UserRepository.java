package com.jeromerichard.pdfstream.Repository;

import com.jeromerichard.pdfstream.Entity.Profil;
import com.jeromerichard.pdfstream.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Set<User> findByProfil(Profil profilId);
    // Par rapport à la sécurité
    User findByUsername(String username);
    Boolean existsByUsername(String username);
}
