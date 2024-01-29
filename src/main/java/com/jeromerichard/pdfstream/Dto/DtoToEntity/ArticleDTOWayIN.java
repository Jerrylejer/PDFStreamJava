package com.jeromerichard.pdfstream.Dto.DtoToEntity;

import lombok.*;

import java.util.Date;

@Data
public class ArticleDTOWayIN {
    String title;
    String description;
    Integer ordering;
    Date createdAt;
    Date updateAt;
}
