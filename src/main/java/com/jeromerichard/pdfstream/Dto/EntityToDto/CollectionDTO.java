package com.jeromerichard.pdfstream.Dto.EntityToDto;

import com.jeromerichard.pdfstream.Entity.User;

import lombok.*;

import java.util.Date;

@Data
public class CollectionDTO {
    Integer id;
    Date createdAt;
    Date updateAt;
    User userId;
}
