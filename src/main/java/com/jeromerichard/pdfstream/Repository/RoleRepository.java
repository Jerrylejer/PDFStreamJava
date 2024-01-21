package com.jeromerichard.pdfstream.Repository;

import com.jeromerichard.pdfstream.Entity.ERole;
import com.jeromerichard.pdfstream.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // Par rapport à la sécurité
    Optional<Role> findByName(ERole name);
}
