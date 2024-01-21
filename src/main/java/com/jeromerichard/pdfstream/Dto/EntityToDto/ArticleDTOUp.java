package com.jeromerichard.pdfstream.Dto.EntityToDto;

import lombok.Data;

import java.util.Date;
@Data
public class ArticleDTOUp {
    private Integer id;
    private String title;
    private String description;
    private Integer order;
    private Date createdAt;
    private Date updateAt;
}
