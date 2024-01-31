package com.jeromerichard.pdfstream.Dto.DtoToEntity;

import com.jeromerichard.pdfstream.Entity.Category;
import com.jeromerichard.pdfstream.Entity.Pdf;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Data
public class CategoryDTOWayIN {
    String title;
    Category parentId;
    Set<Pdf> pdfList;
    Date createdAt;
    Date updateAt;
}
