package com.jeromerichard.pdfstream.Dto.EntityToDto;

import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Entity.User;

import lombok.Data;

import java.util.Date;
@Data
public class DonationDTO {
    private Long id;
    private Integer amount;
    private String message;
    private String beneficiary;
    private Date createdAt;
    private User userId;
    private Pdf pdfId;
}
