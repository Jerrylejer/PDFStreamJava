package com.jeromerichard.pdfstream.Dto.DtoToEntity;

import com.jeromerichard.pdfstream.Entity.Category;
import lombok.*;

import java.util.Date;

@Data
public class CategoryDTOWayIN {
    String title;
    Category parent;
    Date createdAt;
    Date updateAt;
}
