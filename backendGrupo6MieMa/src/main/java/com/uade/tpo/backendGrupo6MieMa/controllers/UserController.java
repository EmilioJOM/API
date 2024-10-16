package com.uade.tpo.backendGrupo6MieMa.controllers;

import com.uade.tpo.backendGrupo6MieMa.entity.Usuario;

import com.uade.tpo.backendGrupo6MieMa.service.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/Usuario")
public class UserController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario user) {
        Usuario nuevoUser = usuarioService.crearUsuario(user);
        return ResponseEntity.ok(nuevoUser);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodosUsuarios() {
        List<Usuario> users = usuarioService.obtenerTodosUsuarios();
        return ResponseEntity.ok(users);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.obtenerPorId(id);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}


