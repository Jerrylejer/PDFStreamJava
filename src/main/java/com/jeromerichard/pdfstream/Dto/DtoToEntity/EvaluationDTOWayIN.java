package com.jeromerichard.pdfstream.Dto.DtoToEntity;

import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Entity.User;
import lombok.*;

import java.util.Date;
@Data
public class EvaluationDTOWayIN {
    String title;
    String comment;
    Byte star;
    Date createdAt;
    Date updatedAt;
    Pdf pdfId;
    User userId;
}
