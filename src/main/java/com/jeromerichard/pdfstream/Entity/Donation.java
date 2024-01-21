package com.jeromerichard.pdfstream.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="donation")
public class Donation {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="amount")
    private Integer amount;
    @Column(name="message")
    private String message;
    @Column(name="beneficiary")
    private String beneficiary;
    @Column(name="created_at")
    private Date createdAt;
    @ManyToOne
    @JoinColumn(name="donation_user_id") // user lié à la donation
    private User user;
    @ManyToOne
    @JoinColumn(name="donation_pdf_id") // pdf lié à la donation
    private Pdf pdf;

    public Donation() {
    }
    public Donation(Integer amount, String message, String beneficiary, Date createdAt, User user, Pdf pdf) {
        this.amount = amount;
        this.message = message;
        this.beneficiary = beneficiary;
        this.createdAt = createdAt;
        this.user = user;
        this.pdf = pdf;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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
