package com.jeromerichard.pdfstream.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="profil")
public class Profil {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="gender")
    private String gender;
    @Column(name="firstname")
    private String firstname;
    @Column(name="lastname")
    private String lastname;
    @Column(name="address1")
    private String address1;
    @Column(name="address2")
    private String address2;
    @Column(name="zipcode")
    private String zipcode;
    @Column(name="city")
    private String city;
    @Column(name="created_at")
    private Date createdAt;
    @Column(name="updated_at")
    private Date updateAt;

    public Profil() {
    }

    public Profil(String gender, String firstname, String lastname, String address1, String address2, String zipcode, String city, Date createdAt, Date updateAt) {
        this.gender = gender;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address1 = address1;
        this.address2 = address2;
        this.zipcode = zipcode;
        this.city = city;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
}
