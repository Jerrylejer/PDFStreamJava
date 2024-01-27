package com.jeromerichard.pdfstream.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name="avatar")
    private String avatar;
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
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    // liste des donations par user authentifié => update ou delete, modifie ou supprime la liste
    private Set<Donation> donationList = new HashSet<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    // liste des evaluations par user authentifié => update ou delete, modifie ou supprime la liste
    private Set<Evaluation> evaluationList = new HashSet<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    // Liste des pdfs par user authentifié => update ou delete, modifie ou supprime la liste
    private Set<Pdf> pdfList = new HashSet<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    // liste des recherches par user authentifié => update ou delete, modifie ou supprime la liste
    private Set<Search> searchList = new HashSet<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    // liste alerts par user authentifié => update ou delete, modifie ou supprime la liste
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

    public User(String username, String password, String avatar, String email, String bio, Date createdAt, Date updatedAt) {
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.email = email;
        this.bio = bio;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
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

    public Set<Donation> getDonationList() {
        return donationList;
    }

    public void setDonationList(Set<Donation> donationList) {
        this.donationList = donationList;
    }

    public Set<Evaluation> getEvaluationList() {
        return evaluationList;
    }

    public void setEvaluationList(Set<Evaluation> evaluationList) {
        this.evaluationList = evaluationList;
    }

    public Set<Pdf> getPdfList() {
        return pdfList;
    }

    public void setPdfList(Set<Pdf> pdfList) {
        this.pdfList = pdfList;
    }

    public Set<Search> getSearchList() {
        return searchList;
    }

    public void setSearchList(Set<Search> searchList) {
        this.searchList = searchList;
    }

    public Set<Alert> getAlertList() {
        return alertList;
    }

    public void setAlertList(Set<Alert> alertList) {
        this.alertList = alertList;
    }
}
