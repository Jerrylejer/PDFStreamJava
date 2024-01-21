package com.jeromerichard.pdfstream.Service.Interfaces;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.DonationDTODown;
import com.jeromerichard.pdfstream.Entity.Donation;
import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Entity.User;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;

import java.util.Set;

public interface DonationServiceInt {
    // CRUD JPA
    public Donation saveDonation(DonationDTODown donation);
    public Set<Donation> getAllDonations() throws EmptyListException;
    public Donation getDonationById(Integer id) throws NotFoundException;
    public Donation updateDonation(Integer id, DonationDTODown donation) throws NotFoundException;
    public void deleteDonation(Integer id) throws NotFoundException;
    // FOREIGN KEY
    public Set<Donation> findByUser(User userId) throws EmptyListException;
    public Set<Donation> findByPdf(Pdf pdfId) throws EmptyListException;
}
