package com.jeromerichard.pdfstream.Service.Implementations;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.ProfilDTODown;
import com.jeromerichard.pdfstream.Entity.Profil;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;
import com.jeromerichard.pdfstream.Repository.ProfilRepository;
import com.jeromerichard.pdfstream.Service.Interfaces.ProfilServiceInt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@Transactional
public class ProfilService implements ProfilServiceInt {
    @Autowired
    private ProfilRepository repository;
    @Override
    public Profil saveAlert(ProfilDTODown profil) {
        Profil profilToSave = new Profil();
        profilToSave.setGender(profil.getGender());
        profilToSave.setFirstname(profil.getFirstname());
        profilToSave.setLastname(profil.getLastname());
        profilToSave.setAdress1(profil.getAdress1());
        profilToSave.setAdress2(profil.getAdress2());
        profilToSave.setZipcode(profil.getZipcode());
        profilToSave.setCity(profil.getCity());
        profilToSave.setCreatedAt(new Date());
        log.info("Nouveau profile ajouté");
        repository.save(profilToSave);
        return profilToSave;
    }

    @Override
    public List<Profil> getAllProfils() throws EmptyListException {
        List<Profil> profilsList = repository.findAll();
        if(profilsList == null) {
            throw new EmptyListException("Aucune liste ne correspond à votre demande");
        };
        return profilsList;
    }

    @Override
    public Profil getProfilById(Integer id) throws NotFoundException {
        Profil profil = repository.findById(id).orElseThrow(
                ()-> new NotFoundException("Ce profile n'existe pas, reformulez votre demande")
        );
        log.info("le profile de " + profil.getFirstname() + " est affiché");
        return profil;
    }

    @Override
    public Profil updateProfil(Integer id, ProfilDTODown profil) throws NotFoundException {
        Profil profilToUpdate = repository.findById(id).orElseThrow(
                ()-> new NotFoundException("Ce profile n'existe pas, reformulez votre demande")
        );
        profilToUpdate.setGender(profil.getGender());
        profilToUpdate.setFirstname(profil.getFirstname());
        profilToUpdate.setLastname(profil.getLastname());
        profilToUpdate.setAdress1(profil.getAdress1());
        profilToUpdate.setAdress2(profil.getAdress2());
        profilToUpdate.setZipcode(profil.getZipcode());
        profilToUpdate.setCity(profil.getCity());
        profilToUpdate.setUpdateAt(new Date());
        log.info("Le profile de " + profil.getFirstname() + "a correctement été modifié");
        repository.save(profilToUpdate);
        return profilToUpdate;
    }

    @Override
    public void deleteProfil(Integer id) throws NotFoundException {
        Profil profilToDelete = repository.findById(id).orElseThrow(
                ()-> new NotFoundException("Ce profile n'existe pas, reformulez votre demande")
        );
        log.info("Le profile de " + profilToDelete.getFirstname() + "à correctement été supprimée");
        repository.deleteById(id);
    }
}
