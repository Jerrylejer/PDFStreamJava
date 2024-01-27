package com.jeromerichard.pdfstream.Dto.DtoToEntity;

import com.jeromerichard.pdfstream.Entity.User;
import lombok.*;

import java.util.Date;
@Data
public class SearchDTOWayIN {
    String result;
    Date createdAt;
    User userId;
}
