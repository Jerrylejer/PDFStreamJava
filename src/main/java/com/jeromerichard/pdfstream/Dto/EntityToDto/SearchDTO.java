package com.jeromerichard.pdfstream.Dto.EntityToDto;

import com.jeromerichard.pdfstream.Entity.User;
import lombok.Data;

import java.util.Date;

@Data
public class SearchDTO {
    private Long id;
    private String result;
    private Date createdAt;
    private User userId;
}
