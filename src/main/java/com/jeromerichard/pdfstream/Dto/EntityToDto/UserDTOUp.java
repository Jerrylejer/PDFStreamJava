package com.jeromerichard.pdfstream.Dto.EntityToDto;

import com.jeromerichard.pdfstream.Entity.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;
@Data
public class UserDTOUp {
    private Long Id;
    private String username;
    private String password;
    private String avatar;
    private String email;
    private String bio;
    private Date createdAt;
    private Date updatedAt;
    private Profil profilId;
    private Set<Role> role;
    private Set<Donation> donationList;
    private Set<Evaluation> evaluationList;
    private Set<Search> searchList;
    private Set<Alert> alertList;
}
