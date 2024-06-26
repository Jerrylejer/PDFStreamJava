package com.jeromerichard.pdfstream.Dto.EntityToDto;

import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Entity.User;

import lombok.*;

import java.util.Date;
@Data
public class DonationDTO {
    Integer id;
    Integer amount;
    String message;
    User beneficiary;
    Date createdAt;
    User donor;
    Pdf pdf;
}
