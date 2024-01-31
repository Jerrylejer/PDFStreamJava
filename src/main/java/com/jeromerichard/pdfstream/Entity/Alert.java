package com.jeromerichard.pdfstream.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="alert")
public class Alert {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="title")
    private String title;
    @Column(name="description")
    private String description;
    @Column(name="state")
    private String state;
    @Column(name="created_at")
    private Date createdAt;
    @Column(name="updated_at")
    private Date updatedAt;
    @ManyToOne // user lié à l'alert
    @JoinColumn(name="alert_user_id")
    private User alertLauncher;
    @ManyToOne // article lié à l'alert
    @JoinColumn(name="alert_article_id")
    private Article charteArticle;
    @ManyToOne // pdf lié à l'alert
    @JsonIgnore
    @JoinColumn(name="alert_pdf_id")
    private Pdf pdf;

    public Alert() {
    }

    public Alert(String title, String description, String state, Date createdAt, Date updatedAt, User alertLauncher, Article charteArticle, Pdf pdf) {
        this.title = title;
        this.description = description;
        this.state = state;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.alertLauncher = alertLauncher;
        this.charteArticle = charteArticle;
        this.pdf = pdf;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public User getAlertLauncher() {
        return alertLauncher;
    }

    public void setAlertLauncher(User alertLauncher) {
        this.alertLauncher = alertLauncher;
    }

    public Article getCharteArticle() {
        return charteArticle;
    }

    public void setCharteArticle(Article charteArticle) {
        this.charteArticle = charteArticle;
    }

    public Pdf getPdf() {
        return pdf;
    }

    public void setPdf(Pdf pdf) {
        this.pdf = pdf;
    }
}
