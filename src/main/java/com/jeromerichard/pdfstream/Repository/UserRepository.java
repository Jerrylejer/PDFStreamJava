package com.jeromerichard.pdfstream.Repository;

import com.jeromerichard.pdfstream.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username); // SECURITE
    Boolean existsByUsername(String username); // SECURITE
    Boolean existsByEmail(String email); // SECURITE

}
