package com.uade.tpo.backendGrupo6MieMa.service;

import com.uade.tpo.backendGrupo6MieMa.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario crearUsuario(Usuario user);
    List<Usuario> obtenerTodosUsuarios();
    Optional<Usuario> obtenerPorId(Long id);

    void eliminar(Long id);
}
