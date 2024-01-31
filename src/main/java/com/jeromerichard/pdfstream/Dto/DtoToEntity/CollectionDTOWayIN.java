package com.jeromerichard.pdfstream.Dto.DtoToEntity;

import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Entity.User;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Data
public class CollectionDTOWayIN {
    Date createdAt;
    Date updateAt;
    User user;
    Set<Pdf> pdfList;
}
