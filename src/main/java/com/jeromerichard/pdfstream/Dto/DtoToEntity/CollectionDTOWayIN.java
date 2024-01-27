package com.jeromerichard.pdfstream.Dto.DtoToEntity;

import com.jeromerichard.pdfstream.Entity.User;
import lombok.*;

import java.util.Date;

@Data
public class CollectionDTOWayIN {
    Date createdAt;
    Date updateAt;
    User userId;
}
