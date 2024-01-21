package com.jeromerichard.pdfstream.Service.Interfaces;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.UserDTODown;
import com.jeromerichard.pdfstream.Entity.User;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;

import java.util.Set;

public interface UserServiceInt {
    // CRUD JPA
    public User saveSearch(UserDTODown user);
    public Set<User> getAllUsers() throws EmptyListException;
    public User getUserById(Integer id) throws NotFoundException;
    public User updateUser(Integer id, UserDTODown user) throws NotFoundException;
    public void deleteUser(Integer id) throws NotFoundException;
    // FOREIGN KEY
    public Set<User> findByUsername(String username) throws EmptyListException;
}
