package com.jeromerichard.pdfstream.Dto.DtoToEntity;

import com.jeromerichard.pdfstream.Entity.Role;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Data
public class UserDTOWayIN {
    String username;
    String password;
    byte[] avatar;
    String email;
    String bio;
    Date createdAt;
    Date updatedAt;
    Set<Role> roles;
}
