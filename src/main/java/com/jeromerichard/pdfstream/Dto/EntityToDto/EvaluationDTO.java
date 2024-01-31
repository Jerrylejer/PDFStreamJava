package com.jeromerichard.pdfstream.Dto.EntityToDto;

import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Entity.User;
import lombok.*;

import java.util.Date;
@Data
public class EvaluationDTO {
    Integer id;
    String title;
    String comment;
    Byte star;
    Date createdAt;
    Date updatedAt;
    Pdf pdf;
    User user;
}
