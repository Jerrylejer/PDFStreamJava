package com.jeromerichard.pdfstream.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name="created_at")
    private Date createdAt;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="donation_beneficiary_id") // user recevant la donation
    private User beneficiary;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="donation_donor_id") // user faisant la donation
    private User donor;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="donation_pdf_id") // pdf lié à la donation
    private Pdf pdf;

    public Donation() {
    }

    public Donation(Integer amount, String message, Date createdAt, User beneficiary, User donor, Pdf pdf) {
        this.amount = amount;
        this.message = message;
        this.createdAt = createdAt;
        this.beneficiary = beneficiary;
        this.donor = donor;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(User beneficiary) {
        this.beneficiary = beneficiary;
    }

    public User getDonor() {
        return donor;
    }

    public void setDonor(User donor) {
        this.donor = donor;
    }

    public Pdf getPdf() {
        return pdf;
    }

    public void setPdf(Pdf pdf) {
        this.pdf = pdf;
    }
}
