package com.jeromerichard.pdfstream.Service.Interfaces;
import com.jeromerichard.pdfstream.Dto.DtoToEntity.PdfDTOWayIN;
import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Entity.User;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.FileUploadExceptionAdvice;
import com.jeromerichard.pdfstream.Exception.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PdfServiceInt {
    // CRUD JPA
    // 2 méthodes pour save un PDF selon l'image fournie par le user ou l'image par défaut
    public Pdf savePdf(PdfDTOWayIN clientDatas, MultipartFile pdfFile, MultipartFile image) throws IOException;
    public Pdf savePdfWithDefaultImage(PdfDTOWayIN clientDatas, MultipartFile pdfFile, MultipartFile defaultImageMultipartFile) throws IOException;

    // Je rends possible les updates au cas par cas des PDFS (sinon message d'erreur car param manquant)
    public Pdf updatePdf(Integer id, PdfDTOWayIN pdf, MultipartFile pdfFile, MultipartFile image) throws NotFoundException, IOException;
    public Pdf updatePdfExceptPdfFile(Integer id, PdfDTOWayIN pdf, MultipartFile image) throws NotFoundException, IOException;
    public Pdf updatePdfExceptImage(Integer id, PdfDTOWayIN pdf, MultipartFile pdfFile) throws NotFoundException, IOException;
    public Pdf updatePdflight(Integer id, PdfDTOWayIN pdf) throws NotFoundException, IOException;

    // Méthode pour afficher une preview d'un PDF
    public byte[] getPdfPreview(Integer id) throws NotFoundException, IOException;

    // Fin des méthodes
    public List<Pdf> getAllPdfs() throws EmptyListException;
    public Pdf getPdfById(Integer id) throws NotFoundException;
    public void deletePdf(Integer id) throws NotFoundException;
    public List<Pdf> findByAuthor(User id) throws NotFoundException, EmptyListException;
}
