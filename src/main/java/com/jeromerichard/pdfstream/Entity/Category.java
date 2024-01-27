package com.jeromerichard.pdfstream.Entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="category", uniqueConstraints = {
        @UniqueConstraint(columnNames = "title")
})
public class Category {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="title")
    private String title;
    @OneToOne
    @JoinColumn(name = "parent_id")
    private Category parent;
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private Set<Category> children = new HashSet<>();
    @Column(name="created_at")
    private Date createdAt;
    @Column(name="updated_at")
    private Date updateAt;
    @ManyToMany(mappedBy = "categoriesList")
    private Set<Pdf> pdfList = new HashSet<>();
    public Category() {
    }

    public Category(String title, Category parent, Set<Category> children, Date createdAt, Date updateAt, Set<Pdf> pdfList) {
        this.title = title;
        this.parent = parent;
        this.children = children;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.pdfList = pdfList;
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

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public Set<Category> getChildren() {
        return children;
    }

    public void setChildren(Set<Category> children) {
        this.children = children;
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

    public Set<Pdf> getPdfList() {
        return pdfList;
    }

    public void setPdfList(Set<Pdf> pdfList) {
        this.pdfList = pdfList;
    }
}
