package com.jeromerichard.pdfstream.Dto.EntityToDto;

import com.jeromerichard.pdfstream.Entity.Article;
import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Entity.User;
import lombok.*;

import java.util.Date;
@Data
public class AlertDTO {
    Integer id;
    String title;
    String description;
    String state;
    Date createdAt;
    Date updateAt;
    User alertLauncher;
    Article charteArticle;
    Pdf pdfId;
}
