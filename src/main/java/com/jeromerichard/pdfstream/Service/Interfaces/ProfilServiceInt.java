package com.jeromerichard.pdfstream.Service.Interfaces;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.ProfilDTOWayIN;
import com.jeromerichard.pdfstream.Entity.Profil;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;

import java.util.List;

public interface ProfilServiceInt {
    // CRUD JPA
    public Profil saveAlert(ProfilDTOWayIN profil);
    public List<Profil> getAllProfils() throws EmptyListException;
    public Profil getProfilById(Integer id) throws NotFoundException;
    public Profil updateProfil(Integer id, ProfilDTOWayIN profil) throws NotFoundException;
    public void deleteProfil(Integer id) throws NotFoundException;
}
