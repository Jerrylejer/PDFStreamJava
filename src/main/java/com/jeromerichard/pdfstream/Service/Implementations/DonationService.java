package com.jeromerichard.pdfstream.Service.Implementations;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.DonationDTOWayIN;
import com.jeromerichard.pdfstream.Entity.Donation;
import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Entity.User;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;
import com.jeromerichard.pdfstream.Repository.DonationRepository;
import com.jeromerichard.pdfstream.Service.Interfaces.DonationServiceInt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@Transactional
public class DonationService implements DonationServiceInt {
    @Autowired
    private DonationRepository repository;
    @Override
    public Donation saveDonation(DonationDTOWayIN donation) {
        Donation donationToSave = new Donation();
        donationToSave.setAmount(donation.getAmount());
        donationToSave.setMessage(donation.getMessage());
        donationToSave.setBeneficiary(donation.getBeneficiary());
        donationToSave.setDonor(donation.getDonor());
        donationToSave.setPdf(donation.getPdf());
        donationToSave.setCreatedAt(new Date());
        log.info("Nouveau don fait à l'auteur-trice " + donationToSave.getBeneficiary().getUsername() + " confirmé");
        repository.save(donationToSave);
        return donationToSave;
    }

    @Override
    public List<Donation> getAllDonations() throws EmptyListException {
        List<Donation> donationsList = repository.findAll();
        if(donationsList == null) {
            throw new EmptyListException("Aucune liste ne correspond à votre demande");
        }
        return donationsList;
    }

    @Override
    public Donation getDonationById(Integer id) throws NotFoundException {
        Donation donation = repository.findById(id).orElseThrow(
                ()-> new NotFoundException("Ce don n'existe pas, reformulez votre demande")
        );
        log.info("le don " + donation.getId() + " est affiché");
        return donation;
    }

    @Override
    public void deleteDonation(Integer id) throws NotFoundException {
        Donation donationToDelete = repository.findById(id).orElseThrow(
                ()-> new NotFoundException("Ce don n'existe pas, reformulez votre demande")
        );
        log.info("Le don " + donationToDelete.getId() + "a correctement été supprimé");
        repository.deleteById(id);
    }

    @Override
    public List<Donation> findByDonor(User donor) throws EmptyListException {
        List<Donation> donationsList = repository.findByDonor(donor);
        if(donationsList == null) {
            throw new EmptyListException("Aucune liste ne correspond à votre demande");
        };
        return donationsList;
    }

    @Override
    public List<Donation> findByBeneficiary(User beneficiary) throws EmptyListException {
        List<Donation> donationsList = repository.findByBeneficiary(beneficiary);
        if(donationsList == null) {
            throw new EmptyListException("Aucune liste ne correspond à votre demande");
        };
        return donationsList;
    }

    @Override
    public List<Donation> findByPdf(Pdf pdf) throws EmptyListException {
        List<Donation> donationsList = repository.findByPdf(pdf);
        if(donationsList == null) {
            throw new EmptyListException("Aucune liste ne correspond à votre demande");
        };
        return donationsList;
    }
}
