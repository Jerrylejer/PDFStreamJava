package com.jeromerichard.pdfstream.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="result")
public class result {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="link")
    private String link;
    @Column(name="created_at")
    private Date createdAt;
    @ManyToOne
    @JoinColumn(name="search_user_id")
    private User searchUserId;

    public result() {
    }

    public result(String link, Date createdAt, User searchUserId) {
        this.link = link;
        this.createdAt = createdAt;
        this.searchUserId = searchUserId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
