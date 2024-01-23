package com.jeromerichard.pdfstream.Controller;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.UserDTOWayIN;
import com.jeromerichard.pdfstream.Dto.EntityToDto.UserDTO;
import com.jeromerichard.pdfstream.Entity.Profil;
import com.jeromerichard.pdfstream.Entity.User;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;
import com.jeromerichard.pdfstream.Service.Implementations.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private ModelMapper modelMapper;
    @PostMapping("/new")
    @ResponseBody
    // les datas seront placées dans le corps de la réponse HTTP sans être interprétées comme une vue HTML.
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTOWayIN clientDatas) {
        // Conversion des datas front en DTOWayIN
        UserDTOWayIN userDTOWayIN = modelMapper.map(clientDatas, UserDTOWayIN.class);
        // Conversion sens DTOWayIN à Entité
        User user = service.saveUser(userDTOWayIN);
        // Conversion sens Entité à DTO
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id, @RequestBody UserDTOWayIN clientDatas) throws NotFoundException {
        // Conversion des datas front en DTOWayIN
        UserDTOWayIN userDTOWayIN = modelMapper.map(clientDatas, UserDTOWayIN.class);
        // Conversion sens DTOWayIN à Entité
        User user = service.updateUser(id, userDTOWayIN);
        // Conversion sens Entité à DTO
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) throws NotFoundException {
        service.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> getUsersList() throws EmptyListException {
        // Le flux de datas retourné par getAllAlerts() est traité pour que chaque data soit convertie en class AlertDTO et retournée sous forme de List
        List<UserDTO> userDTOList = service.getAllUsers().stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer id) throws NotFoundException {
        User user = service.getUserById(id);
        // Conversion sens Entité à DTO
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
    }

/*    @GetMapping("/user/{username}")
    public ResponseEntity<List<UserDTO>> getUserByUsername(@PathVariable String username) throws NotFoundException, EmptyListException {
        List<UserDTO> userDTOList = service.findByUser(username).stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }*/
    @GetMapping("/profil/{profil}")
    public ResponseEntity<List<UserDTO>> getUserByProfil(@PathVariable Profil profil) throws NotFoundException, EmptyListException {
        List<UserDTO> userDTOList = service.findByProfil(profil).stream().map(item -> modelMapper.map(item, UserDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }

}
