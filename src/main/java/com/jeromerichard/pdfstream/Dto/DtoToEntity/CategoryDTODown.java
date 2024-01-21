package com.jeromerichard.pdfstream.Dto.DtoToEntity;

import lombok.Data;

import java.util.Date;

@Data
public class CategoryDTODown {
    private String title;
    private Integer parent;
    private Date createdAt;
    private Date updateAt;
}
