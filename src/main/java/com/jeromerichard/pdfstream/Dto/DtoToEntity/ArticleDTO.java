package com.jeromerichard.pdfstream.Dto.DtoToEntity;

import lombok.Data;

import java.util.Date;
@Data
public class ArticleDTO {
    private Integer id;
    private String title;
    private String description;
    private Integer order;
    private Date createdAt;
    private Date updateAt;
}
