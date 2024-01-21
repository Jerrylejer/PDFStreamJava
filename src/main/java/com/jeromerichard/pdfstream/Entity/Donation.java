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
    private User userId;
    @ManyToOne
    @JoinColumn(name="donation_pdf_id") // pdf lié à la donation
    private Pdf pdfId;

    public Donation() {
    }
    public Donation(Integer amount, String message, String beneficiary, Date createdAt, User userId, Pdf pdfId) {
        this.amount = amount;
        this.message = message;
        this.beneficiary = beneficiary;
        this.createdAt = createdAt;
        this.userId = userId;
        this.pdfId = pdfId;
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

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Pdf getPdfId() {
        return pdfId;
    }

    public void setPdfId(Pdf pdfId) {
        this.pdfId = pdfId;
    }
}
