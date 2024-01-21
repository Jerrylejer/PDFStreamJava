package com.jeromerichard.pdfstream.Dto.DtoToEntity;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleDTODown {
    private String title;
    private String description;
    private Integer order;
    private Date createdAt;
    private Date updateAt;
}
