package com.jeromerichard.pdfstream.Repository;

import com.jeromerichard.pdfstream.Entity.Search;
import com.jeromerichard.pdfstream.Entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SearchRepository extends JpaRepository<Search, Integer> {
    List<Search> findByUser(User user);
}
