package com.jeromerichard.pdfstream.Repository;

import com.jeromerichard.pdfstream.Entity.Search;
import com.jeromerichard.pdfstream.Entity.User;
import org.hibernate.boot.archive.internal.JarProtocolArchiveDescriptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SearchRepository extends JpaRepository<Search, Long> {
    Set<Search> findByUser(User userId);
}
