package com.jeromerichard.pdfstream.Dto.DtoToEntity;

import com.jeromerichard.pdfstream.Entity.Alert;
import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Entity.User;
import lombok.Data;

import java.util.Date;
@Data
public class AlertDTO {
    private Integer id;
    private String title;
    private String description;
    private String state;
    private Date createdAt;
    private Date updateAt;
    private User userId;
    private Alert articleId;
    private Pdf pdfId;
}
