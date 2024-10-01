package com.uade.tpo.backendGrupo6MieMa.controllers.auth;


import com.uade.tpo.backendGrupo6MieMa.controllers.config.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import com.uade.tpo.backendGrupo6MieMa.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private JwtService jwtService;


    private final AuthenticationService service;
    private UserDetailsService userDetailsService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }


//    @PostMapping("/authenticate")
//    public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest request) {
//        // Lógica para autenticar al usuario
//        String username = request.getEmail();
//        // Generar un nuevo token de autenticación
//        String authToken = jwtService.generateToken(username);
//        return ResponseEntity.ok(new AuthenticationResponse(authToken));
//    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

//    @PostMapping("/confirm-registration")
//    public ResponseEntity<?> confirmRegistration(@RequestParam("token") String token) {
//        // Validar el token de registro
//        String username = jwtService.extractUsername(token);
//        if (username != null && jwtService.isTokenValid(token, userDetailsService.loadUserByUsername(username))) {
//            // Registrar al usuario y generar un nuevo token de autenticación
//            String authToken = jwtService.generateRegistrationToken(username);
//            return ResponseEntity.ok(new AuthenticationResponse(authToken));
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid registration token");
//        }
//    }
}