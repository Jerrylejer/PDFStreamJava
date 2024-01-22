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
    private Integer id;
    @Column(name="created_at")
    private Date createdAt;
    @Column(name="updated_at")
    private Date updatedAt;
    @OneToOne
    @JoinColumn(name="collection_user_id") // user lié à la collection
    private User user;
    @ManyToMany
    @JoinTable(name="collection_pdf",
            joinColumns = @JoinColumn(name="collectionPDF_collection_id"),
            inverseJoinColumns = @JoinColumn(name="collectionPDF_pdf_id"))
    private Set<Pdf> pdfList = new HashSet<>();
    public Collection() {
    }
    public Collection(Date createdAt, Date updatedAt, User user) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
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

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
