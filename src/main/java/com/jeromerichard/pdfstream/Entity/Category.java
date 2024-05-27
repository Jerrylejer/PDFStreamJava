package com.jeromerichard.pdfstream.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name="category")
public class Category {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="title")
    private String title;
    @ManyToOne
    @JoinColumn(name = "parent_id") // pouvoir déclarer une catégorie parent
    @JsonIgnore
    private Category parentId;
    @OneToMany(mappedBy = "parentId", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Category> children = new ArrayList<>(); // We use @OneToMany to refer a category to its children
    @Column(name="created_at")
    private Date createdAt;
    @Column(name="updated_at")
    private Date updateAt;
    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    private Set<Pdf> pdfList = new HashSet<>();
}
