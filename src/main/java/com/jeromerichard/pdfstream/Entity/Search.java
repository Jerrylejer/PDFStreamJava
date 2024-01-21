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
    @Column(name="result")
    private String result;
    @Column(name="create_at")
    private Date createdAt;
    @ManyToOne
    @JoinColumn(name="search_user_id") // user lié à la recherche
    private User user;

    public Search() {
    }

    public Search(String result, Date createdAt, User user) {
        this.result = result;
        this.createdAt = createdAt;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
