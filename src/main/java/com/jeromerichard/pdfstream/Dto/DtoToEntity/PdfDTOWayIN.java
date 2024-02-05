package com.jeromerichard.pdfstream.Dto.DtoToEntity;

import com.jeromerichard.pdfstream.Entity.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Data
public class PdfDTOWayIN {
    String title;
    String smallDescription;
    String description;
    String image;
    byte[] file;
    Integer size;
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
