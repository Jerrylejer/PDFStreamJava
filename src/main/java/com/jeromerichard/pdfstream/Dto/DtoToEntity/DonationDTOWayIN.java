package com.jeromerichard.pdfstream.Dto.DtoToEntity;

import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Entity.User;
import lombok.*;

import java.util.Date;
@Data
public class DonationDTOWayIN {
    Integer amount;
    String message;
    String beneficiary;
    Date createdAt;
    User userId;
    Pdf pdfId;
}
