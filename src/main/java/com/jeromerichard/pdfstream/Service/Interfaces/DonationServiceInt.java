package com.jeromerichard.pdfstream.Service.Interfaces;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.DonationDTOWayIN;
import com.jeromerichard.pdfstream.Entity.Donation;
import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Entity.User;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;

import java.util.List;

public interface DonationServiceInt {
    // CRUD JPA
    public Donation saveDonation(DonationDTOWayIN donation);
    public List<Donation> getAllDonations() throws EmptyListException;
    public Donation getDonationById(Integer id) throws NotFoundException;
    public void deleteDonation(Integer id) throws NotFoundException;
    // FOREIGN KEY
    public List<Donation> findByDonor(User donor) throws EmptyListException;
    public List<Donation> findByBeneficiary(User beneficiary) throws EmptyListException;
    public List<Donation> findByPdf(Pdf pdfId) throws EmptyListException;
}
