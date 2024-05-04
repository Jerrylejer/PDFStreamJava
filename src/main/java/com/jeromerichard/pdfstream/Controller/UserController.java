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
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private ModelMapper modelMapper;

    // Si je veux stocker les images dans un folder de l'appli ...
    //public static String uploadDirectory = System.getProperty("user.dir")+"/src/main/webapp/avatars/";

    // Pas de endpoint "/new" car mon controller AuthController gère la création d'un nouveau user avec le endpoint '/inscription"

    @PutMapping("/update/{id}")
    @ResponseBody
    //@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id,
                                              @ModelAttribute UserDTOWayIN clientDatas,
                                              @RequestParam(value = "avatar", required = false)MultipartFile avatar) throws NotFoundException, IOException {

            // #################################################################
            // Si je veux stocker les images dans un folder de l'appli ...
//            String originalFilename = file.getOriginalFilename();
//            Path fileNameAndPath= Paths.get(uploadDirectory, originalFilename);
//            Files.write(fileNameAndPath, file.getBytes());
//            clientDatas.setAvatar(originalFilename);
            // #################################################################

            // ... mais J'enregistre directement mes images dans la BDD
            if (avatar != null && !avatar.isEmpty()) {
                // Handle file upload
                byte[] avatarBytes = avatar.getBytes();
                clientDatas.setAvatar(avatarBytes);
            }
            // Conversion sens DTOWayIN à Entité
            User user = service.updateUser(id, clientDatas);
            // Conversion sens Entité à DTO
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);

            return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

/*    @PutMapping("/update/{id}")
    @ResponseBody
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id,
                                              @RequestParam Map<String, String> requestParams,
                                              @RequestParam(value = "updatedAvatar", required = false) MultipartFile updatedAvatar)
            throws NotFoundException, IOException {
        UserDTOWayIN clientDatas = null;

        if (!requestParams.isEmpty()) {
            clientDatas = new UserDTOWayIN();
            if(requestParams.get("username") != null)
                clientDatas.setUsername(requestParams.get("username"));
            if(requestParams.get("email") != null)
                clientDatas.setEmail(requestParams.get("email"));
            if(requestParams.get("password") != null)
                clientDatas.setPassword(requestParams.get("password"));
            if(requestParams.get("bio") != null)
                clientDatas.setBio(requestParams.get("bio"));
        }

        if (updatedAvatar != null && !updatedAvatar.isEmpty()) {
            byte[] avatarBytes = updatedAvatar.getBytes();
            clientDatas.setAvatar(avatarBytes);
        }

        UserDTOWayIN userDTOWayIN = clientDatas;
        if (clientDatas != null) {
            userDTOWayIN = modelMapper.map(clientDatas, UserDTOWayIN.class);
        }

        User user = service.updateUser(id, userDTOWayIN);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }*/

    @DeleteMapping("/delete/{id}")
    //@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) throws NotFoundException {
        service.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDTO>> getUsersList() throws EmptyListException {
        // Le flux de datas retourné par getAllAlerts() est traité pour que chaque data soit convertie en class AlertDTO et retournée sous forme de List
        List<UserDTO> userDTOList = service.getAllUsers().stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }

    @GetMapping("/single/{id}")
    //@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) throws NotFoundException {
        User userById = service.getUserById(id);
        // Conversion sens Entité à DTO
        UserDTO userDTO = modelMapper.map(userById, UserDTO.class);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) throws NotFoundException, EmptyListException {
        User user = service.findByUsername(username);
        // Conversion sens Entité à DTO
        UserDTO userByUsername = modelMapper.map(user, UserDTO.class);
        return new ResponseEntity<>(userByUsername, HttpStatus.OK);
    }

}
