package com.jeromerichard.pdfstream.Controller;

import com.jeromerichard.pdfstream.Entity.ERole;
import com.jeromerichard.pdfstream.Entity.Role;
import com.jeromerichard.pdfstream.Entity.User;
import com.jeromerichard.pdfstream.Payload.request.LoginRequest;
import com.jeromerichard.pdfstream.Payload.request.SignupRequest;
import com.jeromerichard.pdfstream.Payload.response.JwtResponse;
import com.jeromerichard.pdfstream.Payload.response.MessageResponse;
import com.jeromerichard.pdfstream.Repository.RoleRepository;
import com.jeromerichard.pdfstream.Repository.UserRepository;
import com.jeromerichard.pdfstream.Security.jwt.JwtUtils;
import com.jeromerichard.pdfstream.Security.service.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/connexion")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        log.info("connexion réussie");

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/inscription")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        user.setCreatedAt(new Date());
        // Date de création du user
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        log.info("inscription réussie");

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


    @PostMapping("/deconnexion")
    public ResponseEntity<?> logoutUser(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //log.info(String.valueOf(authentication.getPrincipal()));

        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
            log.info("Utilisateur déconnecté avec succès");
            return ResponseEntity.ok(new MessageResponse("User logged out successfully!"));
        }

        log.warn("Aucun utilisateur n'est actuellement connecté");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("No user is currently logged in"));
    }

    @GetMapping("/connectedUsers")
    @ResponseBody
    public List<String> getConnectedUsers() {
        List<String> connectedUsers = new ArrayList<>();
        // Accès au contexte de sécurité
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Vérifier si l'utilisateur est authentifié
        if (authentication != null && authentication.isAuthenticated()) {
            // Obtenir les détails d'authentification de l'utilisateur
            Object principal = authentication.getPrincipal();
            // Si les détails d'authentification sont UserDetails, obtenir le nom d'utilisateur
            if (principal instanceof UserDetails) {
                String username = ((UserDetails) principal).getUsername();
                connectedUsers.add(username);
            } else {
                // Si les détails d'authentification ne sont pas UserDetails, obtenir les informations de l'objet principal
                connectedUsers.add(principal.toString());
            }
        }
        return connectedUsers;
    }
    // Méthode GET temporaire pour voir mes users qui sont connectés (vérification persistence de l'état)

}




