package com.jeromerichard.pdfstream.Service.Interfaces;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.ProfilDTODown;
import com.jeromerichard.pdfstream.Entity.Profil;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;

import java.util.Set;

public interface ProfilServiceInt {
    // CRUD JPA
    public Profil saveAlert(ProfilDTODown profil);
    public Set<Profil> getAllProfils() throws EmptyListException;
    public Profil getProfilById(Long id) throws NotFoundException;
    public Profil updateProfil(Long id, ProfilDTODown profil) throws NotFoundException;
    public void deleteProfil(Long id) throws NotFoundException;
}
