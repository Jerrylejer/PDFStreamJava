package com.jeromerichard.pdfstream.Service.Interfaces;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.UserDTODown;
import com.jeromerichard.pdfstream.Entity.Profil;
import com.jeromerichard.pdfstream.Entity.User;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;

import java.util.List;
import java.util.Set;

public interface UserServiceInt {
    // CRUD JPA
    public User saveSearch(UserDTODown user);
    public List<User> getAllUsers() throws EmptyListException;
    public User getUserById(Integer id) throws NotFoundException;
    public User updateUser(Integer id, UserDTODown user) throws NotFoundException;
    public void deleteUser(Integer id) throws NotFoundException;
    // FOREIGN KEY
    public List<User> findByProfil(Profil profil) throws EmptyListException;
    public User findByUsername(String username) throws EmptyListException, NotFoundException;
}
