package com.jeromerichard.pdfstream.Dto.DtoToEntity;

import lombok.*;

import java.util.Date;

@Data
public class ArticleDTOWayIN {
    String title;
    String description;
    Integer order;
    Date createdAt;
    Date updateAt;
}
