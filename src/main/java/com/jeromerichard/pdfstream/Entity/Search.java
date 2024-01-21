package com.jeromerichard.pdfstream.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="search")
public class Search {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="result")
    private String result;
    @Column(name="create_at")
    private Date createdAt;
    @ManyToOne
    @JoinColumn(name="search_user_id") // user lié à la recherche
    private User userId;

    public Search() {
    }

    public Search(String result, Date createdAt, User userId) {
        this.result = result;
        this.createdAt = createdAt;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
