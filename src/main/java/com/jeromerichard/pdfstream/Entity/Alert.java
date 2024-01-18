package com.jeromerichard.pdfstream.Entity;

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
    private Date updateAt;
    @ManyToOne
    @JoinColumn(name="alert_user_id")
    private User alertUserId;
    @ManyToOne
    @JoinColumn(name="alert_article_id")
    private Alert alertArticleId;
    @ManyToOne
    @JoinColumn(name="alert_pdf_id")
    private Pdf alertPdfId;

    public Alert() {
    }

    public Alert(String title, String description, String state, Date createdAt, Date updateAt, User alertUserId, Alert alertArticleId, Pdf alertPdfId) {
        this.title = title;
        this.description = description;
        this.state = state;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.alertUserId = alertUserId;
        this.alertArticleId = alertArticleId;
        this.alertPdfId = alertPdfId;
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

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public User getAlertUserId() {
        return alertUserId;
    }

    public void setAlertUserId(User alertUserId) {
        this.alertUserId = alertUserId;
    }

    public Alert getAlertArticleId() {
        return alertArticleId;
    }

    public void setAlertArticleId(Alert alertArticleId) {
        this.alertArticleId = alertArticleId;
    }

    public Pdf getAlertPdfId() {
        return alertPdfId;
    }

    public void setAlertPdfId(Pdf alertPdfId) {
        this.alertPdfId = alertPdfId;
    }
}
