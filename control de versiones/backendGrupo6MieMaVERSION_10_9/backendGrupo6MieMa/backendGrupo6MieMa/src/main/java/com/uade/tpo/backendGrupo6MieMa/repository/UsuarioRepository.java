package com.uade.tpo.backendGrupo6MieMa.repository;

import com.uade.tpo.backendGrupo6MieMa.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Consulta personalizada para buscar por email
    @Query("SELECT u FROM Usuario u WHERE u.usuario_email = :email")
    Optional<Usuario> findByEmail(@Param("email") String email);

    Optional<Usuario> findById(Long id);
}
