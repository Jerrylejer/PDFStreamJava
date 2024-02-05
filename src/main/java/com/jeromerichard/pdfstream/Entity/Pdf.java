package com.jeromerichard.pdfstream.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name="type")
    private String type;
    @Lob
    @Column(name="file", length = 65555)
    private byte[] file;
    @Column(name="size")
    private long size;
    @Column(name="counter")
    private Integer counter;
    @Column(name="created_at")
    private Date createdAt;
    @Column(name="updated_at")
    private Date updateAt;
    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(name="pdf_category",
            joinColumns = @JoinColumn(name="pdf_id"),
            inverseJoinColumns = @JoinColumn(name="category_id"))
    private Set<Category> categories = new HashSet<>();
    // Je récupère un tableau d'objets des catégories qui incluent ce pdf

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(name="pdf_collection",
            joinColumns = @JoinColumn(name="pdf_id"),
            inverseJoinColumns = @JoinColumn(name="collection_id"))
    private Set<Collection> collections = new HashSet<>();
    // Je "devrais" récupèrer un tableau d'objets des collections qui incluent ce pdf
    @ManyToOne
    @JoinColumn(name="user_id") // user lié au pdf
    @JsonIgnore
    private User author;
    @OneToMany(mappedBy = "pdf", cascade = CascadeType.ALL)
    @JsonIgnore
    // liste des evaluations par pdf
    private Set<Evaluation> evaluations = new HashSet<>();
    @OneToMany(mappedBy = "pdf", cascade = CascadeType.ALL)
    @JsonIgnore
    // liste des alerts par pdf ok
    private Set<Alert> alerts = new HashSet<>();
    @OneToMany(mappedBy = "pdf", cascade = CascadeType.ALL)
    @JsonIgnore
    // liste des alerts par pdf ok
    private Set<Donation> donations = new HashSet<>();

    public Pdf() {
    }
    public Pdf(String title, String smallDescription, String description, String image, String type, byte[] file, long size, Integer counter, Date createdAt, Date updateAt, Set<Category> categories, Set<Collection> collections, User author, Set<Evaluation> evaluations, Set<Alert> alerts, Set<Donation> donations) {
        this.title = title;
        this.smallDescription = smallDescription;
        this.description = description;
        this.image = image;
        this.type = type;
        this.file = file;
        this.size = size;
        this.counter = counter;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.categories = categories;
        this.collections = collections;
        this.author = author;
        this.evaluations = evaluations;
        this.alerts = alerts;
        this.donations = donations;
    }

    public Pdf(String title, String contentType, byte[] bytes) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
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

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Collection> getCollections() {
        return collections;
    }

    public void setCollections(Set<Collection> collections) {
        this.collections = collections;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(Set<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }

    public Set<Alert> getAlerts() {
        return alerts;
    }

    public void setAlerts(Set<Alert> alerts) {
        this.alerts = alerts;
    }

    public Set<Donation> getDonations() {
        return donations;
    }

    public void setDonations(Set<Donation> donations) {
        this.donations = donations;
    }
}
