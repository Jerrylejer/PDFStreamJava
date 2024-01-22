package com.jeromerichard.pdfstream.Service.Implementations;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.UserDTODown;
import com.jeromerichard.pdfstream.Entity.Profil;
import com.jeromerichard.pdfstream.Entity.User;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;
import com.jeromerichard.pdfstream.Repository.UserRepository;
import com.jeromerichard.pdfstream.Service.Interfaces.UserServiceInt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@Transactional
public class UserService implements UserServiceInt {
    @Autowired
    private UserRepository repository;
    @Override
    public User saveSearch(UserDTODown user) {
        User userToSave = new User();
        userToSave.setUsername(user.getUsername());
        userToSave.setPassword(user.getPassword());
        userToSave.setAvatar(user.getAvatar());
        userToSave.setEmail(user.getEmail());
        userToSave.setBio(user.getBio());
        userToSave.setCreatedAt(new Date());
        log.info("Nouvel utilisateur " + userToSave.getUsername() + " ajouté");
        repository.save(userToSave);
        return userToSave;
    }

    @Override
    public List<User> getAllUsers() throws EmptyListException {
        List<User> usersList = repository.findAll();
        if(usersList == null) {
            throw new EmptyListException("Aucune liste ne correspond à votre demande");
        }
        return usersList;
    }

    @Override
    public User getUserById(Integer id) throws NotFoundException {
        User user = repository.findById(id).orElseThrow(
                ()-> new NotFoundException("Ce user n'existe pas, reformulez votre demande")
        );
        log.info("le user " + user.getUsername() + " est affiché.");
        return user;
    }

    @Override
    public User updateUser(Integer id, UserDTODown user) throws NotFoundException {
        User userToUpdate = repository.findById(id).orElseThrow(
                ()-> new NotFoundException("Ce user n'existe pas, reformulez votre demande")
        );
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setAvatar(user.getAvatar());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setBio(user.getBio());
        userToUpdate.setUpdatedAt(new Date());
        log.info("L'utilisateur " + userToUpdate.getUsername() + " a correctement été modifié");
        repository.save(userToUpdate);
        return userToUpdate;
    }

    @Override
    public void deleteUser(Integer id) throws NotFoundException {
        User userToDelete = repository.findById(id).orElseThrow(
                ()-> new NotFoundException("Ce user n'existe pas, reformulez votre demande")
        );
        log.info("L'utilisateur " + userToDelete.getUsername() + "à correctement été supprimée");
        repository.deleteById(id);
    }

    @Override
    public List<User> findByProfil(Profil profil) throws EmptyListException {
        List<User> userListByProfil = repository.findByProfil(profil);
        if(profil == null) {
            throw new EmptyListException("Aucun utilisateur ne correspond à cette recherche par profile, reformulez votre demande");
        };
        return userListByProfil;
    }

    @Override
    public User findByUsername(String username) throws NotFoundException {
        User user = repository.findByUsername(username);
        if(user == null) {
            throw new NotFoundException("Ce user n'existe pas, reformulez votre demande");
        }
        log.info("le user " + user.getUsername() + " est affiché.");
        return user;
    }
}
