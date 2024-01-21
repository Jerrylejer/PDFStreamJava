package com.jeromerichard.pdfstream.Dto.DtoToEntity;

import com.jeromerichard.pdfstream.Entity.*;
import lombok.Data;

import java.util.Date;

@Data
public class PdfDTODown {
    private String title;
    private String smallDescription;
    private String description;
    private String image;
    private String author;
    private Integer size;
    private Integer counter;
    private Date createdAt;
    private Date updateAt;
//    private Set<Category> CategoriesList;
//    private Set<Collection> collectionList;
    private User userId;
//    private Set<Evaluation> evaluationList;
//    private Set<Alert> alertList;
}
