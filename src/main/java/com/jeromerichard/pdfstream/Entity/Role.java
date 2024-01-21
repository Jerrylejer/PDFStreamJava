package com.jeromerichard.pdfstream.Entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="role")
public class Role {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="name")
    @Enumerated(EnumType.STRING) // conversion de Enum à String
    private ERole name;
    @ManyToMany(mappedBy = "role")
    private Set<User> userList;

    public Role() {
    }

    public Role(ERole name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }
}
