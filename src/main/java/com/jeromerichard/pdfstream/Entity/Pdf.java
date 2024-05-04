package com.jeromerichard.pdfstream.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity @Table(name="pdf") @NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder
public class Pdf {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="title")
    private String title;

    @Column(name="small_description")
    private String smallDescription;

    @Column(name="description")
    private String description;

    @Lob
    @Column(name="image", length = 65555, nullable = true)
    private byte[] image;

    @Column(name="type")
    private String type;

    @Lob
    @Column(name="pdf_file", length = 65555, nullable = true)
    private byte[] pdfFile;

    @Column(name="size")
    private long size;

    @Column(name="counter")
    private Integer counter;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="updated_at")
    private Date updateAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(name="pdf_category",
            joinColumns = @JoinColumn(name="pdf_id"),
            inverseJoinColumns = @JoinColumn(name="category_id"))
    private Set<Category> categories = new HashSet<>();
    // Je récupère un tableau d'objets des catégories qui incluent ce pdf
    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(name="pdf_collection",
            joinColumns = @JoinColumn(name="pdf_id"),
            inverseJoinColumns = @JoinColumn(name="collection_id"))
    private Set<Collection> collections = new HashSet<>();
    // Je "devrais" récupèrer un tableau d'objets des collections qui incluent ce pdf
    @ManyToOne
    @JoinColumn(name="user_id") // user lié au pdf
    //@JsonIgnore
    private User author;

    @OneToMany(mappedBy = "pdf", cascade = CascadeType.ALL)
    @JsonIgnore
    // liste des evaluations par pdf
    private Set<Evaluation> evaluations = new HashSet<>();

    @OneToMany(mappedBy = "pdf", cascade = CascadeType.ALL)
    @JsonIgnore
    // liste des alerts par pdf ok
    private Set<Alert> alerts = new HashSet<>();

    @OneToMany(mappedBy = "pdf", cascade = CascadeType.ALL)
    @JsonIgnore
    // liste des alerts par pdf ok
    private Set<Donation> donations = new HashSet<>();
}
