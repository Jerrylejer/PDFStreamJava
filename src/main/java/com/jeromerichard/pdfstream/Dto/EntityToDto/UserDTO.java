package com.jeromerichard.pdfstream.Dto.EntityToDto;

import com.jeromerichard.pdfstream.Entity.*;
import lombok.*;

import java.util.Date;
import java.util.Set;
@Data
public class UserDTO {
     Long Id;
     String username;
     String password;
     byte[] avatar;
     String email;
     String bio;
     Date createdAt;
     Date updatedAt;
     Set<Role> roles;
     Set<Pdf> pdfs;
     Set<Evaluation> evaluations;
     Set<Donation> donationsByBeneficiary;
     Set<Donation> donationsByDonor;
     Set<Search> searches;
     Set<Alert> alertList;
}
