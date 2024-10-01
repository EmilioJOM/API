package com.uade.tpo.backendGrupo6MieMa.controllers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.uade.tpo.backendGrupo6MieMa.repository.UsuarioRepository;
import com.uade.tpo.backendGrupo6MieMa.entity.Usuario;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final UsuarioRepository repository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Usuario user = repository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
            return new org.springframework.security.core.userdetails.User(
                    user.getUsuario_email(),
                    user.getUsuario_contrasenia(),
                    new ArrayList<>()
            );
        };
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        return username -> {
//            Usuario user = repository.findByEmail(username)
//                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
//            return new org.springframework.security.core.userdetails.User(
//                    user.getUsuario_email(),
//                    user.getUsuario_contrasenia(),
//                    new ArrayList<>()
//            );
//        };
//    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}