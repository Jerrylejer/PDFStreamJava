package com.jeromerichard.pdfstream.Dto.EntityToDto;

import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Entity.User;
import lombok.Data;

import java.util.Date;
@Data
public class EvaluationDTOUp {
    private Long id;
    private String title;
    private String comment;
    private Byte star;
    private Date createdAt;
    private Date updatedAt;
    private Pdf pdfId;
    private User userId;
}
