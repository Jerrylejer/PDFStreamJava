package com.jeromerichard.pdfstream.Service.Implementations;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.PdfDTODown;
import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;
import com.jeromerichard.pdfstream.Repository.PdfRepository;
import com.jeromerichard.pdfstream.Service.Interfaces.PdfServiceInt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@Transactional
public class PdfService implements PdfServiceInt {
    @Autowired
    private PdfRepository repository;

    @Override
    public Pdf savePdf(PdfDTODown pdf) {
        Pdf pdfToSave = new Pdf();
        pdfToSave.setTitle(pdf.getTitle());
        pdfToSave.setSmallDescription(pdf.getSmallDescription());
        pdfToSave.setDescription(pdf.getDescription());
        pdfToSave.setImage(pdf.getImage());
        pdfToSave.setAuthor(pdf.getAuthor());
//        pdfToSave.setSize(pdf.getSize());
//        pdfToSave.setCounter(pdf.getCounter());
        pdfToSave.setCreatedAt(new Date());
        log.info("Nouveau PDF " + pdf.getTitle() + " ajouté");
        repository.save(pdfToSave);
        return pdfToSave;
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
    public Pdf updatePdf(Integer id, PdfDTODown pdf) throws NotFoundException {
        Pdf pdfToUpdate = repository.findById(id).orElseThrow(
                ()-> new NotFoundException("Ce PDF n'existe pas, reformulez votre demande")
        );
        pdfToUpdate.setTitle(pdf.getTitle());
        pdfToUpdate.setSmallDescription(pdf.getSmallDescription());
        pdfToUpdate.setDescription(pdf.getDescription());
        pdfToUpdate.setImage(pdf.getImage());
        pdfToUpdate.setAuthor(pdf.getAuthor());
//        pdfToUpdate.setSize(pdf.getSize());
//        pdfToUpdate.setCounter(pdf.getCounter());
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
}
