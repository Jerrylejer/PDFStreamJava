package com.jeromerichard.pdfstream.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="gift")
public class Gift {
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
    @JoinColumn(name="gift_user_id")
    private User giftUserId;
    @ManyToOne
    @JoinColumn(name="gift_pdf_id")
    private Pdf giftPdfId;

    public Gift() {
    }

    public Gift(Integer amount, String message, String beneficiary, Date createdAt, User giftUserId, Pdf giftPdfId) {
        this.amount = amount;
        this.message = message;
        this.beneficiary = beneficiary;
        this.createdAt = createdAt;
        this.giftUserId = giftUserId;
        this.giftPdfId = giftPdfId;
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

    public User getGiftUserId() {
        return giftUserId;
    }

    public void setGiftUserId(User giftUserId) {
        this.giftUserId = giftUserId;
    }

    public Pdf getGiftPdfId() {
        return giftPdfId;
    }

    public void setGiftPdfId(Pdf giftPdfId) {
        this.giftPdfId = giftPdfId;
    }
}
