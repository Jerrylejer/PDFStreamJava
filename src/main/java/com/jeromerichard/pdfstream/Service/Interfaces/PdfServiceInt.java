package com.jeromerichard.pdfstream.Service.Interfaces;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.AlertDTODown;
import com.jeromerichard.pdfstream.Dto.DtoToEntity.PdfDTODown;
import com.jeromerichard.pdfstream.Entity.Alert;
import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;

import java.util.Set;

public interface PdfServiceInt {
    // CRUD JPA
    public Pdf savePdf(PdfDTODown pdf);
    public Set<Pdf> getAllPdfs() throws EmptyListException;
    public Pdf getPdfById(Long id) throws NotFoundException;
    public Pdf updatePdf(Long id, PdfDTODown pdf) throws NotFoundException;
    public void deletePdf(Long id) throws NotFoundException;
}
