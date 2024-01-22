package com.jeromerichard.pdfstream.Service.Implementations;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.SearchDTODown;
import com.jeromerichard.pdfstream.Entity.Search;
import com.jeromerichard.pdfstream.Entity.User;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;
import com.jeromerichard.pdfstream.Repository.SearchRepository;
import com.jeromerichard.pdfstream.Service.Interfaces.SearchServiceInt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@Transactional
public class SearchService implements SearchServiceInt {
    @Autowired
    private SearchRepository repository;
    @Override
    public Search saveSearch(SearchDTODown search) {
        Search searchToSave = new Search();
        searchToSave.setResult(search.getResult());
        searchToSave.setUser(search.getUserId());
        searchToSave.setCreatedAt(new Date());
        log.info("Nouvelle recherche ajouté");
        repository.save(searchToSave);
        return searchToSave;
    }

    @Override
    public List<Search> getAllSearchs() throws EmptyListException {
        List<Search> searchsList = repository.findAll();
        if(searchsList == null) {
            throw new EmptyListException("Aucune liste ne correspond à votre demande");
        }
        return searchsList;
    }

    @Override
    public Search getSearchById(Integer id) throws NotFoundException {
        Search search = repository.findById(id).orElseThrow(
                ()-> new NotFoundException("Cette recherche n'existe pas, reformulez votre demande")
        );
        log.info("la recherche " + id + " est affichée");
        return search;
    }


    @Override
    public void deleteSearch(Integer id) throws NotFoundException {
        Search searchToDelete = repository.findById(id).orElseThrow(
                ()-> new NotFoundException("Cette recherche n'existe pas, reformulez votre demande")
        );
        log.info("La recherche " + searchToDelete.getId() + "à correctement été supprimée");
        repository.deleteById(id);
    }

    @Override
    public List<Search> findByUser(User user) throws EmptyListException {
        List<Search> searchsList = repository.findByUser(user);
        if(searchsList == null) {
            throw new EmptyListException("Aucune liste ne correspond à votre demande");
        }
        return searchsList;
    }
}
