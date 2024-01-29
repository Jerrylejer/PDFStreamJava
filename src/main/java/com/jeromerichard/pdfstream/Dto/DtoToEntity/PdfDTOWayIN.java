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
    Integer size;
    Integer counter;
    Date createdAt;
    Date updateAt;
    Set<Category> categories;
//    private Set<Collection> collectionList;
    User user;
//    private Set<Evaluation> evaluationList;
//    private Set<Alert> alertList;
}
