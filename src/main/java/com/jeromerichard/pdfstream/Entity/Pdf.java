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

//    @Lob
//    @Column(name="file")
//    https://www.bezkoder.com/spring-boot-upload-file-database/
//    private byte[] file;
    @Column(name="size")
    private Integer size;
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
    @ManyToMany(mappedBy = "pdfList")
    @JsonIgnore
    private Set<Collection> collectionList = new HashSet<>();
    @ManyToOne
    @JoinColumn(name="pdf_user_id") // user lié au pdf
    @JsonIgnore
    private User user;
    @OneToMany(mappedBy = "pdf", cascade = CascadeType.ALL)
    @JsonIgnore
    // liste des evaluations par pdf
    private Set<Evaluation> evaluations = new HashSet<>();
    @OneToMany(mappedBy = "pdf", cascade = CascadeType.ALL)
    @JsonIgnore
    // liste des alerts par pdf
    private Set<Alert> alerts = new HashSet<>();
    @OneToMany(mappedBy = "pdf", cascade = CascadeType.ALL)
    @JsonIgnore
    // liste des alerts par pdf
    private Set<Donation> donations = new HashSet<>();

    public Pdf() {
    }
    public Pdf(String title, String smallDescription, String description, String image, Integer size, Integer counter, Date createdAt, Date updateAt, Set<Category> categories, Set<Collection> collectionList, User user, Set<Evaluation> evaluations, Set<Alert> alerts, Set<Donation> donations) {
        this.title = title;
        this.smallDescription = smallDescription;
        this.description = description;
        this.image = image;
        this.size = size;
        this.counter = counter;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.categories = categories;
        this.collectionList = collectionList;
        this.user = user;
        this.evaluations = evaluations;
        this.alerts = alerts;
        this.donations = donations;
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

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Collection> getCollectionList() {
        return collectionList;
    }

    public void setCollectionList(Set<Collection> collectionList) {
        this.collectionList = collectionList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
