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
    @ManyToOne // user lié à l'alert
    @JoinColumn(name="alert_user_id")
    private User userId;
    @ManyToOne // article lié à l'alert
    @JoinColumn(name="alert_article_id")
    private Alert articleId;
    @ManyToOne // pdf lié à l'alert
    @JoinColumn(name="alert_pdf_id")
    private Pdf pdfId;

    public Alert() {
    }

    public Alert(String title, String description, String state, Date createdAt, Date updateAt, User userId, Alert articleId, Pdf pdfId) {
        this.title = title;
        this.description = description;
        this.state = state;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.userId = userId;
        this.articleId = articleId;
        this.pdfId = pdfId;
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

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Alert getArticleId() {
        return articleId;
    }

    public void setArticleId(Alert articleId) {
        this.articleId = articleId;
    }

    public Pdf getPdfId() {
        return pdfId;
    }

    public void setPdfId(Pdf pdfId) {
        this.pdfId = pdfId;
    }
}
