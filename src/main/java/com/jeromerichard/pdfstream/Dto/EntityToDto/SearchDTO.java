package com.jeromerichard.pdfstream.Dto.EntityToDto;

import com.jeromerichard.pdfstream.Entity.User;
import lombok.*;

import java.util.Date;

@Data
public class SearchDTO {
    Integer id;
    String result;
    Date createdAt;
    User userId;
}
