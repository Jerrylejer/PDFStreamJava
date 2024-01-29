package com.jeromerichard.pdfstream.Dto.EntityToDto;

import com.jeromerichard.pdfstream.Entity.Role;
import lombok.*;

import java.util.Date;
import java.util.Set;
@Data
public class UserDTO {
     Long Id;
     String username;
//     String password;
//     String avatar;
//     String email;
     String bio;
     Date createdAt;
     Date updatedAt;
     Set<Role> roles;
}
