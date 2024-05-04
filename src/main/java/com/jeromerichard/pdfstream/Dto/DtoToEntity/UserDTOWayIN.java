package com.jeromerichard.pdfstream.Dto.DtoToEntity;

import com.jeromerichard.pdfstream.Entity.Role;
import jakarta.annotation.Nullable;
import lombok.*;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Data
public class UserDTOWayIN {
    Long id;
    String username;
    String password;
    byte[] avatar;
    String email;
    String bio;
    Date createdAt;
    Date updatedAt;
    Set<Role> roles;
}
