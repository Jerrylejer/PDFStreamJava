package com.jeromerichard.pdfstream.Dto.DtoToEntity;

import com.jeromerichard.pdfstream.Entity.*;
import lombok.Data;

import java.util.Date;

@Data
public class UserDTODown {
    private String username;
    private String password;
    private String avatar;
    private String email;
    private String bio;
    private Date createdAt;
    private Date updateAt;
    private Profil profilId;
//    private Set<Role> role;
//    private Set<Donation> donationList;
//    private Set<Evaluation> evaluationList;
//    private Set<Search> searchList;
//    private Set<Alert> alertList;
}
