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
    String author;
    Integer size;
    Integer counter;
    Date createdAt;
    Date updateAt;
    Set<Category> CategoriesList;
//    private Set<Collection> collectionList;
    User userId;
//    private Set<Evaluation> evaluationList;
//    private Set<Alert> alertList;
}
