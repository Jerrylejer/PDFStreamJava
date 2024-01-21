package com.jeromerichard.pdfstream.Dto.DtoToEntity;

import lombok.Data;

import java.util.Date;
@Data
public class ProfilDTODown {
    private String gender;
    private String firstname;
    private String lastname;
    private String adress1;
    private String adress2;
    private String zipcode;
    private String city;
    private Date createdAt;
    private Date updateAt;
}
