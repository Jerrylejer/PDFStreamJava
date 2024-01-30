package com.jeromerichard.pdfstream.Dto.EntityToDto;

import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Entity.User;

import lombok.*;

import java.util.Date;
import java.util.Set;

@Data
public class CollectionDTO {
    Integer id;
    Date createdAt;
    Date updateAt;
    User userId;
    Set<Pdf> pdfList;
}
