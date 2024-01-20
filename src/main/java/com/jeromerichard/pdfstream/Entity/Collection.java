package com.jeromerichard.pdfstream.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="collection")
public class Collection {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="created_at")
    private Date createdAt;
    @Column(name="updated_at")
    private Date updateAt;
    @OneToOne
    @JoinColumn(name="collection_user_id")
    private User userId;
    public Collection() {
    }
    public Collection(Date createdAt, Date updateAt, User userId) {
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.userId = userId;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
