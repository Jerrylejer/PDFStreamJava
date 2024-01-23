package com.jeromerichard.pdfstream.Dto.EntityToDto;

import lombok.Data;

import java.util.Date;

@Data
public class ProfilDTO {
    private Long id;
    private String gender;
    private String firstname;
    private String lastname;
    private String address1;
    private String address2;
    private String zipcode;
    private String city;
    private Date createdAt;
    private Date updateAt;
}
