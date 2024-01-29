package com.jeromerichard.pdfstream.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.*;

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
    @JoinColumn(name = "parent_id") // We use @OneToOne annotation to refer a category to its parent
    @JsonIgnore
    private Category parentId;
    @OneToMany(mappedBy = "parentId", cascade = CascadeType.ALL)
    private List<Category> children = new ArrayList<>(); // We use @OneToMany to refer a category to its children
    @Column(name="created_at")
    private Date createdAt;
    @Column(name="updated_at")
    private Date updateAt;
    @ManyToMany(mappedBy = "categoriesList")
    private Set<Pdf> pdfList = new HashSet<>();
    public Category() {
    }

    public Category(String title, Category parentId, List<Category> children, Date createdAt, Date updateAt, Set<Pdf> pdfList) {
        this.title = title;
        this.parentId = parentId;
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

    public Category getParentId() {
        return parentId;
    }

    public void setParentId(Category parentId) {
        this.parentId = parentId;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
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
