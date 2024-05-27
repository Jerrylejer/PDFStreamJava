package com.jeromerichard.pdfstream.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
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
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
