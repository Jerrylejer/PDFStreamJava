package com.jeromerichard.pdfstream.Dto.EntityToDto;

import com.jeromerichard.pdfstream.Entity.Category;
import lombok.*;

import java.util.Date;

@Data
public class CategoryDTO {
    Integer id;
    String title;
    Category parentId;
    Date createdAt;
    Date updateAt;
}
