package com.jeromerichard.pdfstream.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="evaluation")
public class Evaluation {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="title")
    private String title;
    @Column(name="comment")
    private String comment;
    @Column(name="star")
    private Byte star;
    @Column(name="created_at")
    private Date createdAt;
    @Column(name="updated_at")
    private Date updatedAt;
    @ManyToOne
    @JoinColumn(name="evaluation_pdf_id") // pdf lié à l'évaluation
    private Pdf pdf;
    @ManyToOne
    @JoinColumn(name="evaluation_user_id") // user lié à l'évaluation
    private User user;

    public Evaluation() {
    }

    public Evaluation(String title, String comment, Byte star, Date createdAt, Date updateAt, User user, Pdf pdf) {
        this.title = title;
        this.comment = comment;
        this.star = star;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Byte getStar() {
        return star;
    }

    public void setStar(Byte star) {
        this.star = star;
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

    public Pdf getPdf() {
        return pdf;
    }

    public void setPdf(Pdf pdf) {
        this.pdf = pdf;
    }
}
