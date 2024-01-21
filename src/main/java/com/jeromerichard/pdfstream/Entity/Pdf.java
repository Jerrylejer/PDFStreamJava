package com.jeromerichard.pdfstream.Entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="pdf")
public class Pdf {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="title")
    private String title;
    @Column(name="small_description")
    private String smallDescription;
    @Column(name="description")
    private String description;
    @Column(name="image")
    private String image;
    @Column(name="author")
    private String author;
    @Column(name="size")
    private Integer size;
    @Column(name="counter")
    private Integer counter;
    @Column(name="created_at")
    private Date createdAt;
    @Column(name="updated_at")
    private Date updateAt;
    @ManyToMany
    @JoinTable(name="pdf_category",
            joinColumns = @JoinColumn(name="pdf_id"),
            inverseJoinColumns = @JoinColumn(name="category_id"))
    private Set<Category> CategoriesList = new HashSet<>();
    @ManyToMany(mappedBy = "pdfList")
    private Set<Collection> collectionList = new HashSet<>();
    @ManyToOne
    @JoinColumn(name="pdf_user_id") // user lié au pdf
    private User userId;
    @OneToMany(mappedBy = "pdfId", cascade = CascadeType.ALL) // liste des evaluations par pdf authentifié => update ou delete, modifie ou supprime la liste
    private Set<Evaluation> evaluationList;
    @OneToMany(mappedBy = "pdfId", cascade = CascadeType.ALL) // liste des alerts par pdf authentifié => update ou delete, modifie ou supprime la liste
    private Set<Alert> alertList;

    public Pdf() {
    }

    public Pdf(String title, String smallDescription, String description, String image, String author, Integer size, Integer counter, Date createdAt, Date updateAt) {
        this.title = title;
        this.smallDescription = smallDescription;
        this.description = description;
        this.image = image;
        this.author = author;
        this.size = size;
        this.counter = counter;
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

    public String getSmallDescription() {
        return smallDescription;
    }

    public void setSmallDescription(String smallDescription) {
        this.smallDescription = smallDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
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
