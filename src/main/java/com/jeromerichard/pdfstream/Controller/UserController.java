package com.jeromerichard.pdfstream.Controller;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.UserDTOWayIN;
import com.jeromerichard.pdfstream.Dto.EntityToDto.UserDTO;
import com.jeromerichard.pdfstream.Entity.User;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;
import com.jeromerichard.pdfstream.Service.Implementations.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public static String uploadDirectory = System.getProperty("user.dir")+"/src/main/webapp/avatars/";

/*    @PostMapping("/new")
    //@PreAuthorize("hasRole('ADMIN'), hasRole('USER')")
    // les datas seront placées dans le corps de la réponse HTTP sans être interprétées comme une vue HTML.
    public ResponseEntity<UserDTO> saveUser(@ModelAttribute UserDTOWayIN clientDatas, @RequestParam("file")MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        Path fileNameAndPath= Paths.get(uploadDirectory, originalFilename);
        Files.write(fileNameAndPath, file.getBytes());
        clientDatas.setAvatar(originalFilename);
        // Conversion des datas front en DTOWayIN
        UserDTOWayIN userDTOWayIN = modelMapper.map(clientDatas, UserDTOWayIN.class);
        // Conversion sens DTOWayIN à Entité
        User user = service.saveUser(userDTOWayIN);
        // Conversion sens Entité à DTO
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.CREATED);
    }*/

/*    @PostMapping("/new")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN'), hasRole('USER')")
    // les datas seront placées dans le corps de la réponse HTTP sans être interprétées comme une vue HTML.
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTOWayIN clientDatas) {
        // Conversion des datas front en DTOWayIN
        UserDTOWayIN userDTOWayIN = modelMapper.map(clientDatas, UserDTOWayIN.class);
        // Conversion sens DTOWayIN à Entité
        User user = service.saveUser(userDTOWayIN);
        // Conversion sens Entité à DTO
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.CREATED);
    }*/

    @PutMapping("/update/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @ModelAttribute UserDTOWayIN clientDatas, @RequestParam("file")MultipartFile file) throws NotFoundException, IOException {
        if(!file.isEmpty()){
            String originalFilename = file.getOriginalFilename();
            Path fileNameAndPath= Paths.get(uploadDirectory, originalFilename);
            Files.write(fileNameAndPath, file.getBytes());
            clientDatas.setAvatar(originalFilename);
            // Conversion des datas front en DTOWayIN
            UserDTOWayIN userDTOWayIN = modelMapper.map(clientDatas, UserDTOWayIN.class);
            // Conversion sens DTOWayIN à Entité
            User user = service.updateUser(id, userDTOWayIN);
            // Conversion sens Entité à DTO
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            return new ResponseEntity<UserDTO>(userDTO, HttpStatus.CREATED);
        } else {
        // Conversion des datas front en DTOWayIN
        UserDTOWayIN userDTOWayIN = modelMapper.map(clientDatas, UserDTOWayIN.class);
        // Conversion sens DTOWayIN à Entité
        User user = service.updateUser(id, userDTOWayIN);
        // Conversion sens Entité à DTO
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.CREATED);
    }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN'), hasRole('USER')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) throws NotFoundException {
        service.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDTO>> getUsersList() throws EmptyListException {
        // Le flux de datas retourné par getAllAlerts() est traité pour que chaque data soit convertie en class AlertDTO et retournée sous forme de List
        List<UserDTO> userDTOList = service.getAllUsers().stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) throws NotFoundException {
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

}
