package com.jeromerichard.pdfstream.Dto.EntityToDto;

import lombok.*;

import java.util.Date;
@Data
public class ArticleDTO {
    Integer id;
    String title;
    String description;
    Integer ordering;
    Date createdAt;
    Date updateAt;
}
