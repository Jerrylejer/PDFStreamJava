package com.jeromerichard.pdfstream.Service.Interfaces;
import com.jeromerichard.pdfstream.Dto.DtoToEntity.PdfDTOWayIN;
import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.FileUploadExceptionAdvice;
import com.jeromerichard.pdfstream.Exception.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PdfServiceInt {
    // CRUD JPA
    // public Pdf savePdf(PdfDTOWayIN data) throws IOException;
    public Pdf fileStorage(MultipartFile file) throws IOException, FileUploadExceptionAdvice;
    public List<Pdf> getAllPdfs() throws EmptyListException;
    public Pdf getPdfById(Integer id) throws NotFoundException;
    public Pdf updatePdf(Integer id, PdfDTOWayIN pdf) throws NotFoundException;
    public void deletePdf(Integer id) throws NotFoundException;
}
