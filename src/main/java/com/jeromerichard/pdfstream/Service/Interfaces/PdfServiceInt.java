package com.jeromerichard.pdfstream.Service.Interfaces;
import com.jeromerichard.pdfstream.Dto.DtoToEntity.PdfDTOWayIN;
import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Entity.User;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
public interface PdfServiceInt {
    // CRUD JPA
    // 2 méthodes Create suivant si image fournie ou image par défaut
    public Pdf savePdf(PdfDTOWayIN clientDatas, MultipartFile pdfFile, MultipartFile image) throws IOException;
    public Pdf savePdfWithDefaultImage(PdfDTOWayIN clientDatas, MultipartFile pdfFile, MultipartFile defaultImageMultipartFile) throws IOException;
    // 4 méthodes d'updates selon les inputs renseignés ou n on
    public Pdf updatePdf(Integer id, PdfDTOWayIN pdf, MultipartFile pdfFile, MultipartFile image) throws NotFoundException, IOException;
    public Pdf updatePdfExceptPdfFile(Integer id, PdfDTOWayIN pdf, MultipartFile image) throws NotFoundException, IOException;
    public Pdf updatePdfExceptImage(Integer id, PdfDTOWayIN pdf, MultipartFile pdfFile) throws NotFoundException, IOException;
    public Pdf updatePdflight(Integer id, PdfDTOWayIN pdf) throws NotFoundException, IOException;
    // Suite du CRUD -> Read, delete
    public List<Pdf> getAllPdfs() throws EmptyListException;
    public Pdf getPdfById(Integer id) throws NotFoundException;
    public void deletePdf(Integer id) throws NotFoundException;
    // Méthode pour afficher une preview d'un PDF
    public byte[] getPdfPreview(Integer id) throws NotFoundException, IOException;
    // Méthode pour afficher une liste de pdfs par auteur
    public List<Pdf> findByAuthor(User id) throws NotFoundException, EmptyListException;
}
