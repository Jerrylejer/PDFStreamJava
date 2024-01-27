package com.jeromerichard.pdfstream.Dto.DtoToEntity;

import lombok.*;

import java.util.Date;

@Data
public class CategoryDTOWayIN {
    String title;
    Integer parent;
    Date createdAt;
    Date updateAt;
}
