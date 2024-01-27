package com.jeromerichard.pdfstream.Dto.EntityToDto;

import lombok.*;

import java.util.Date;

@Data
public class CategoryDTO {
    Integer id;
    String title;
    Integer parent;
    Date createdAt;
    Date updateAt;
}
