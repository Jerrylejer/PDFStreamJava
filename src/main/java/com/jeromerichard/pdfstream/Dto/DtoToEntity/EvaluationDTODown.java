package com.jeromerichard.pdfstream.Dto.DtoToEntity;

import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Entity.User;
import lombok.Data;

import java.util.Date;
@Data
public class EvaluationDTODown {
    private String title;
    private String comment;
    private Byte star;
    private Date createdAt;
    private Date updatedAt;
    private Pdf pdfId;
    private User userId;
}
