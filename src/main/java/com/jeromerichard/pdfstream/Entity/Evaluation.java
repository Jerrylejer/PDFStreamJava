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
    private Date updateAt;
    @JoinColumn(name="eval_user_id")
    private User evalUserId;
    @ManyToOne
    @JoinColumn(name="eval_pdf_id")
    private Pdf evalPdfId;

    public Evaluation() {
    }

    public Evaluation(String title, String comment, Byte star, Date createdAt, Date updateAt, User evalUserId, Pdf evalPdfId) {
        this.title = title;
        this.comment = comment;
        this.star = star;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.evalUserId = evalUserId;
        this.evalPdfId = evalPdfId;
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

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public User getEvalUserId() {
        return evalUserId;
    }

    public void setEvalUserId(User evalUserId) {
        this.evalUserId = evalUserId;
    }

    public Pdf getEvalPdfId() {
        return evalPdfId;
    }

    public void setEvalPdfId(Pdf evalPdfId) {
        this.evalPdfId = evalPdfId;
    }
}
