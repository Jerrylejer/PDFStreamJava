package com.jeromerichard.pdfstream.Entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="collection")
public class Collection {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="created_at")
    private Date createdAt;
    @Column(name="updated_at")
    private Date updateAt;
    @OneToOne
    @JoinColumn(name="collection_user_id") // user lié à la collection
    private User userId;
    @ManyToMany
    @JoinTable(name="collection_pdf",
            joinColumns = @JoinColumn(name="collection_id"),
            inverseJoinColumns = @JoinColumn(name="pdf_id"))
    private Set<Pdf> pdfList = new HashSet<>();
    public Collection() {
    }
    public Collection(Date createdAt, Date updateAt, User userId) {
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.userId = userId;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
