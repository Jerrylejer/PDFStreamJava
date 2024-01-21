package com.jeromerichard.pdfstream.Dto.EntityToDto;

import com.jeromerichard.pdfstream.Entity.User;

import lombok.Data;

import java.util.Date;

@Data
public class CollectionDTOUp {
    private Integer id;
    private Date createdAt;
    private Date updateAt;
    private User userId;
}
