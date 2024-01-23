package com.jeromerichard.pdfstream.Dto.EntityToDto;

import lombok.Data;

import java.util.Date;

@Data
public class CategoryDTO {
    private Integer id;
    private String title;
    private Integer parent;
    private Date createdAt;
    private Date updateAt;
}
