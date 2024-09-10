package com.uade.tpo.backendGrupo6MieMa.service;

import com.uade.tpo.backendGrupo6MieMa.entity.Role;
import com.uade.tpo.backendGrupo6MieMa.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uade.tpo.backendGrupo6MieMa.controllers.auth.AuthenticationRequest;
import com.uade.tpo.backendGrupo6MieMa.controllers.auth.AuthenticationResponse;
import com.uade.tpo.backendGrupo6MieMa.controllers.auth.RegisterRequest;
import com.uade.tpo.backendGrupo6MieMa.controllers.config.JwtService;
import com.uade.tpo.backendGrupo6MieMa.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

        @Autowired
        private final UsuarioRepository repository;

        @Autowired
        private final PasswordEncoder passwordEncoder;

        @Autowired
        private final JwtService jwtService;

        @Autowired
        private final AuthenticationManager authenticationManager;


        public AuthenticationResponse register(RegisterRequest request) {
                // Convierte el rol que viene en el request a un objeto Role y lo coloca en una lista
                List<Role> roles = List.of(Role.valueOf(request.getRol().toString())); // Convertimos el string del request a Role

                var usuario = Usuario.builder()
                        .usuario_nombre(request.getNombre())
                        .usuario_apellido(request.getApellido())
                        .usuario_email(request.getEmail())
                        .usuario_contrasenia(passwordEncoder.encode(request.getContrasena()))
                        .usuario_tipo_usuario(roles)  // Asignamos la lista de roles
                        .build();

                repository.save(usuario);

                // Generamos las autoridades sin el prefijo "ROLE_"
                var jwtToken = jwtService.generateToken(new org.springframework.security.core.userdetails.User(
                        usuario.getUsuario_email(),
                        usuario.getUsuario_contrasenia(),
                        roles.stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList())  // Generamos las autoridades sin el prefijo "ROLE_"
                ));

                return AuthenticationResponse.builder()
                        .accessToken(jwtToken)
                        .build();
        }



        public AuthenticationResponse authenticate(AuthenticationRequest request) {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(),
                                request.getPassword()));

                var user = repository.findByEmail(request.getEmail())
                        .orElseThrow();
                // Generamos las autoridades sin el prefijo "ROLE_"
                var jwtToken = jwtService.generateToken(new org.springframework.security.core.userdetails.User(
                        user.getUsuario_email(),
                        user.getUsuario_contrasenia(),
                        user.getUsuario_tipo_usuario().stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList())  // Generamos las autoridades sin el prefijo "ROLE_"
                ));
                return AuthenticationResponse.builder()
                        .accessToken(jwtToken)
                        .build();
        }
}