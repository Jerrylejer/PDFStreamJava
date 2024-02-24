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
    byte[] image;
    Integer size;
    byte[] pdfFile;
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
