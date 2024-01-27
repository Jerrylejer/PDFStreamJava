package com.jeromerichard.pdfstream.Service.Interfaces;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.UserDTOWayIN;
import com.jeromerichard.pdfstream.Entity.User;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;

import java.util.List;

public interface UserServiceInt {
    // CRUD JPA
    public User saveUser(UserDTOWayIN user);
    public List<User> getAllUsers() throws EmptyListException;
    public User getUserById(Long id) throws NotFoundException;
    public User updateUser(Long id, UserDTOWayIN user) throws NotFoundException;
    public void deleteUser(Long id) throws NotFoundException;
}
