package com.jeromerichard.pdfstream.Service.Implementations;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.PdfDTOWayIN;
import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Entity.User;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.FileUploadExceptionAdvice;
import com.jeromerichard.pdfstream.Exception.NotFoundException;
import com.jeromerichard.pdfstream.Repository.PdfRepository;
import com.jeromerichard.pdfstream.Service.Interfaces.PdfServiceInt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@Transactional
public class PdfService implements PdfServiceInt {
    @Autowired
    private PdfRepository repository;
    @Override
    public Pdf savePdf(PdfDTOWayIN pdf, MultipartFile pdfFile, MultipartFile image) throws IOException {
    Pdf pdfToSave = Pdf.builder()
            .title(pdfFile.getOriginalFilename())
            .pdfFile(pdfFile.getBytes())
            .size(pdfFile.getSize())
            .type(pdfFile.getContentType())
            .image(image.getBytes())
            .author(pdf.getAuthor())
            .smallDescription(pdf.getSmallDescription())
            .description(pdf.getDescription())
            .categories(pdf.getCategories())
            .createdAt(new Date())
            .build();
            log.info("Nouveau pdf " + pdfToSave.getTitle() + " ajouté");
            return repository.save(pdfToSave);
    }

    @Override
    public List<Pdf> getAllPdfs() throws EmptyListException {
        List<Pdf> pdfsList = repository.findAll();
        if(pdfsList == null) {
            throw new EmptyListException("Aucune liste ne correspond à votre demande");
        }
        return pdfsList;
    }

    @Override
    public Pdf getPdfById(Integer id) throws NotFoundException {
        Pdf pdf = repository.findById(id).orElseThrow(
                ()-> new NotFoundException("Ce PDF n'existe pas, reformulez votre demande")
        );
        log.info("le pdf " + pdf.getTitle() + " est affiché.");
        return pdf;
    }

    @Override
    public Pdf updatePdf(Integer id, PdfDTOWayIN pdf, MultipartFile pdfFile, MultipartFile image) throws NotFoundException, IOException {
        Pdf pdfToUpdate = repository.findById(id).orElseThrow(
                ()-> new NotFoundException("Ce PDF n'existe pas, reformulez votre demande")
        );
        String title = pdfFile.getOriginalFilename();
        if (pdf.getSmallDescription() != null)
            pdfToUpdate.setSmallDescription(pdf.getSmallDescription());
        if (pdf.getDescription() != null)
            pdfToUpdate.setDescription(pdf.getDescription());
        if (pdf.getImage() != null)
            pdfToUpdate.setImage(image.getBytes());
        if (pdf.getPdfFile() != null)
            pdfToUpdate.setPdfFile(pdfFile.getBytes());
            pdfToUpdate.setTitle(title);
        if (pdf.getCategories() != null)
            pdfToUpdate.setCategories(pdf.getCategories());
            pdfToUpdate.setUpdateAt(new Date());
        log.info("Le PDF" + pdfToUpdate.getTitle() + "a correctement été modifié");
        repository.save(pdfToUpdate);
        return pdfToUpdate;
    }

    @Override
    public Pdf updatePdfExceptPdfFile(Integer id, PdfDTOWayIN pdf, MultipartFile image) throws NotFoundException, IOException {
        Pdf pdfToUpdate = repository.findById(id).orElseThrow(
                ()-> new NotFoundException("Ce PDF n'existe pas, reformulez votre demande")
        );
        if (pdf.getSmallDescription() != null)
            pdfToUpdate.setSmallDescription(pdf.getSmallDescription());
        if (pdf.getDescription() != null)
            pdfToUpdate.setDescription(pdf.getDescription());
        if (pdf.getImage() != null)
            pdfToUpdate.setImage(image.getBytes());
        if (pdf.getCategories() != null)
            pdfToUpdate.setCategories(pdf.getCategories());
        pdfToUpdate.setUpdateAt(new Date());
        log.info("Le PDF" + pdfToUpdate.getTitle() + "a correctement été modifié");
        repository.save(pdfToUpdate);
        return pdfToUpdate;
    }

    @Override
    public Pdf updatePdfExceptImage(Integer id, PdfDTOWayIN pdf, MultipartFile pdfFile) throws NotFoundException, IOException {
        Pdf pdfToUpdate = repository.findById(id).orElseThrow(
                ()-> new NotFoundException("Ce PDF n'existe pas, reformulez votre demande")
        );
        String title = pdfFile.getOriginalFilename();
        if (pdf.getSmallDescription() != null)
            pdfToUpdate.setSmallDescription(pdf.getSmallDescription());
        if (pdf.getDescription() != null)
            pdfToUpdate.setDescription(pdf.getDescription());
        if (pdf.getPdfFile() != null)
            pdfToUpdate.setPdfFile(pdfFile.getBytes());
        pdfToUpdate.setTitle(title);
        if (pdf.getCategories() != null)
            pdfToUpdate.setCategories(pdf.getCategories());
        pdfToUpdate.setUpdateAt(new Date());
        log.info("Le PDF" + pdfToUpdate.getTitle() + "a correctement été modifié");
        repository.save(pdfToUpdate);
        return pdfToUpdate;
    }

    @Override
    public Pdf updatePdflight(Integer id, PdfDTOWayIN pdf) throws NotFoundException, IOException {
        Pdf pdfToUpdate = repository.findById(id).orElseThrow(
                ()-> new NotFoundException("Ce PDF n'existe pas, reformulez votre demande")
        );
        if (pdf.getSmallDescription() != null)
            pdfToUpdate.setSmallDescription(pdf.getSmallDescription());
        if (pdf.getDescription() != null)
            pdfToUpdate.setDescription(pdf.getDescription());
        if (pdf.getCategories() != null)
            pdfToUpdate.setCategories(pdf.getCategories());
        pdfToUpdate.setUpdateAt(new Date());
        log.info("Le PDF" + pdfToUpdate.getTitle() + "a correctement été modifié");
        repository.save(pdfToUpdate);
        return pdfToUpdate;
    }

    @Override
    public void deletePdf(Integer id) throws NotFoundException {
        Pdf pdfToDelete = repository.findById(id).orElseThrow(
                ()-> new NotFoundException("Ce PDF n'existe pas, reformulez votre demande")
        );
        log.info("Le PDF " + pdfToDelete.getTitle() + "à correctement été supprimée");
        repository.deleteById(id);
    }

/*    @Override
    public List<Pdf> findByAuthorId(Integer id) throws NotFoundException, EmptyListException {
        if(id == null) {
            throw new NotFoundException("Aucun auteur ne correspond à votre demande");
        }
        List<Pdf> pdfsList = repository.findByAuthorId(id);
        if(pdfsList.isEmpty()) {
            throw new EmptyListException("Aucune liste ne correspond à votre demande");
        }
        return pdfsList;
    }*/

    @Override
    public List<Pdf> findByAuthor(User id) throws EmptyListException {
        List<Pdf> pdfsList = repository.findByAuthor(id);
        if(pdfsList.isEmpty()) {
            throw new EmptyListException("Aucune liste ne correspond à votre demande");
        }
        return pdfsList;
    }
}
