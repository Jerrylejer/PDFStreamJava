package com.jeromerichard.pdfstream.Repository;

import com.jeromerichard.pdfstream.Entity.Profil;
import com.jeromerichard.pdfstream.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByProfil(Profil profil);
    // Par rapport à la sécurité
//    List<User> findByUser(String username);
    Boolean existsByUsername(String username);
}
