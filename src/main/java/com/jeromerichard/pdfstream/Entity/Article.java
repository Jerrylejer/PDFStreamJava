package com.jeromerichard.pdfstream.Entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="article")
public class Article {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="title")
    private String title;
    @Column(name="description")
    private String description;
    @Column(name="ordering")
    private Integer ordering;
    @Column(name="created_at")
    private Date createdAt;
    @Column(name="updated_at")
    private Date updateAt;
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    // Liste des alerts pour un article authentifié => update ou delete, modifie ou supprime la liste
    private Set<Alert> alertList = new HashSet<>();

    public Article() {
    }

    public Article(String title, String description, Integer ordering, Date createdAt, Date updateAt) {
        this.title = title;
        this.description = description;
        this.ordering = ordering;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrder() {
        return ordering;
    }

    public void setOrder(Integer order) {
        this.ordering = order;
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
}
