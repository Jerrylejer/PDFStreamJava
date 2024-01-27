package com.jeromerichard.pdfstream.Dto.DtoToEntity;

import com.jeromerichard.pdfstream.Entity.Article;
import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Entity.User;
import lombok.*;

import java.util.Date;
@Data
public class AlertDTOWayIN {
    String title;
    String description;
    String state;
    Date createdAt;
    Date updateAt;
    User userId;
    Article articleId;
    Pdf pdfId;
}
