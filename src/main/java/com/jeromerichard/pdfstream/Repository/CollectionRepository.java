package com.jeromerichard.pdfstream.Repository;

import com.jeromerichard.pdfstream.Entity.Collection;
import com.jeromerichard.pdfstream.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {
    Set<Collection> findByUser(User userId);
}