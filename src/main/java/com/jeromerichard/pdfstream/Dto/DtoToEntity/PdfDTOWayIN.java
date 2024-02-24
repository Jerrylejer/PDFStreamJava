package com.jeromerichard.pdfstream.Dto.DtoToEntity;

import com.jeromerichard.pdfstream.Entity.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Set;

@Data
public class PdfDTOWayIN {
    Integer id;
    String title;
    String smallDescription;
    String description;
    MultipartFile image;
    Integer size;
    MultipartFile pdfFile;
    Integer counter;
    Date createdAt;
    Date updateAt;
    Set<Category> categories;
    Set<Collection> collections;
    Set<Evaluation> evaluations;
    Set<Alert> alerts;
    Set<Donation> donations;
    User author;
}
