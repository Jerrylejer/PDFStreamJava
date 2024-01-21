package com.jeromerichard.pdfstream.Dto.DtoToEntity;

import com.jeromerichard.pdfstream.Entity.User;

import lombok.Data;

import java.util.Date;

@Data
public class CollectionDTO {
    private Integer id;
    private Date createdAt;
    private Date updateAt;
    private User userId;
}
