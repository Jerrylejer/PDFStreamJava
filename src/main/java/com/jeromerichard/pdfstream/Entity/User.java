package com.jeromerichard.pdfstream.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class User {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Lob
    @Column(name="avatar", length = 65555)
    private byte[] avatar;
    @Column(name="email")
    private String email;
    @Column(name="bio")
    private String bio;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name="updated_at")
    private Date updatedAt;
    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(name="user_role",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> roles = new HashSet<>();
    @OneToMany(mappedBy = "donor", cascade = CascadeType.ALL)
    //@JsonIgnore
    // liste des donations par donateurs
    private Set<Donation> donationsByDonor = new HashSet<>();
    @OneToMany(mappedBy = "beneficiary", cascade = CascadeType.ALL)
    //@JsonIgnore
    // liste des donations par bénéficiaire
    private Set<Donation> donationsByBeneficiary = new HashSet<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    // liste des evaluations par user
    private Set<Evaluation> evaluations = new HashSet<>();
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @JsonIgnore
    // Liste des pdfs par user
    private Set<Pdf> pdfs = new HashSet<>();

    @OneToMany(mappedBy = "alertLauncher", cascade = CascadeType.ALL)
    @JsonIgnore
    // liste alerts par user
    private Set<Alert> alertList = new HashSet<>();

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String password, byte[] avatar, String email, String bio, Date createdAt, Date updatedAt, Set<Role> roles, Set<Donation> donationsByBeneficiary, Set<Donation> donationsByDonor, Set<Evaluation> evaluations, Set<Pdf> pdfs, Set<Alert> alertList) {
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.email = email;
        this.bio = bio;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.roles = roles;
        this.donationsByBeneficiary = donationsByBeneficiary;
        this.donationsByDonor = donationsByDonor;
        this.evaluations = evaluations;
        this.pdfs = pdfs;
        this.alertList = alertList;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Donation> getDonationsByDonor() {
        return donationsByDonor;
    }

    public void setDonationsByDonor(Set<Donation> donationsByDonor) {
        this.donationsByDonor = donationsByDonor;
    }

    public Set<Donation> getDonationsByBeneficiary() {
        return donationsByBeneficiary;
    }

    public void setDonationsByBeneficiary(Set<Donation> donationsByBeneficiary) {
        this.donationsByBeneficiary = donationsByBeneficiary;
    }

    public Set<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(Set<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }

    public Set<Pdf> getPdfs() {
        return pdfs;
    }

    public void setPdfs(Set<Pdf> pdfs) {
        this.pdfs = pdfs;
    }

    public Set<Alert> getAlertList() {
        return alertList;
    }

    public void setAlertList(Set<Alert> alertList) {
        this.alertList = alertList;
    }
}
