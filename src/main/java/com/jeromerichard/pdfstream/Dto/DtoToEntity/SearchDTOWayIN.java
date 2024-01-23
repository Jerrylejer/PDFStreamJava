package com.jeromerichard.pdfstream.Dto.DtoToEntity;

import com.jeromerichard.pdfstream.Entity.User;
import lombok.Data;

import java.util.Date;
@Data
public class SearchDTOWayIN {
    private String result;
    private Date createdAt;
    private User userId;
}
