package com.uade.tpo.backendGrupo6MieMa.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

public class UsuarioDetails implements UserDetails {

    private final Usuario user;

    public UsuarioDetails(Usuario user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convierte los roles de la entidad Usuario a GrantedAuthority sin el prefijo "ROLE_"
        return user.getUsuario_tipo_usuario().stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))  // Usa el nombre del rol directamente
                .collect(Collectors.toList());
    }



    @Override
    public String getPassword() {
        return user.getUsuario_contrasenia(); // Devuelve la contraseña
    }

    @Override
    public String getUsername() {
        return user.getUsuario_email(); // Devuelve el email como nombre de usuario
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Ajusta según tu lógica
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Ajusta según tu lógica
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Ajusta según tu lógica
    }

    @Override
    public boolean isEnabled() {
        return true; // Ajusta según tu lógica
    }
}

