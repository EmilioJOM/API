package com.uade.tpo.backendGrupo6MieMa.controllers.auth;

import com.uade.tpo.backendGrupo6MieMa.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String nombre;
    private String apellido;
    private String email;
    private String contrasena;
    private Role rol;
}