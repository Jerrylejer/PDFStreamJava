package com.jeromerichard.pdfstream.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="search")
public class Search {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="term")
    private String term;
    @Column(name="create_at")
    private Date createdAt;
    @ManyToOne
    @JoinColumn(name="search_user_id")
    private User searchUserId;

    public Search() {
    }

    public Search(String term, Date createdAt, User searchUserId) {
        this.term = term;
        this.createdAt = createdAt;
        this.searchUserId = searchUserId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getSearchUserId() {
        return searchUserId;
    }

    public void setSearchUserId(User searchUserId) {
        this.searchUserId = searchUserId;
    }
}
