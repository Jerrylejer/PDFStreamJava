package com.jeromerichard.pdfstream.Dto.EntityToDto;

import com.jeromerichard.pdfstream.Entity.*;
import lombok.*;

import java.util.Date;
import java.util.Set;
@Data
public class PdfDTO {
    Integer id;
    String title;
    String smallDescription;
    String description;
    String image;
    Integer size;
    Integer counter;
    Date createdAt;
    Date updateAt;
    Set<Category> categories;
    User user;
}
