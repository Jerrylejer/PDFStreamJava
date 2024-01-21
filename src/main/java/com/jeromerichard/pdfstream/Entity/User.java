package com.jeromerichard.pdfstream.Entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="user")
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
    @Column(name="created_at")
    private Date createdAt;
    @Column(name="updated_at")
    private Date updateAt;
    @ManyToOne
    @JoinColumn(name="user_profil_id")
    private Profil profilId;
    @ManyToMany
    @JoinTable(name="user_role",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> role = new HashSet<>();
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    // liste des donations par user authentifié => update ou delete, modifie ou supprime la liste
    private Set<Donation> donationList = new HashSet<>();
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    // liste des evaluations par user authentifié => update ou delete, modifie ou supprime la liste
    private Set<Evaluation> evaluationList = new HashSet<>();
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    // Liste des pdfs par user authentifié => update ou delete, modifie ou supprime la liste
    private Set<Pdf> pdfList = new HashSet<>();
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    // liste des recherches par user authentifié => update ou delete, modifie ou supprime la liste
    private Set<Search> searchList = new HashSet<>();
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    // liste alerts par user authentifié => update ou delete, modifie ou supprime la liste
    private Set<Alert> alertList = new HashSet<>();

    public User() {
    }

    public User(String username, String password, String avatar, String email, String bio, Date createdAt, Date updateAt, Profil profilId) {
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.email = email;
        this.bio = bio;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.profilId = profilId;
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

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Profil getProfilId() {
        return profilId;
    }

    public void setProfilId(Profil profilId) {
        this.profilId = profilId;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }
}
