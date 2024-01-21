package com.jeromerichard.pdfstream.Dto.EntityToDto;

import lombok.Data;

import java.util.Date;

@Data
public class CategoryDTOUp {
    private Integer id;
    private String title;
    private Integer parent;
    private Date createdAt;
    private Date updateAt;
}
