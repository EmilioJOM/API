package com.uade.tpo.backendGrupo6MieMa.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuario_idUsuario;

    @Column
    private String usuario_nombre;

    @Column
    private String usuario_apellido;

    @Column
    private String usuario_email;

    @Column
    private String usuario_contrasenia;

    @Column
    private String usuario_direccion;

    @Column
    private String usuario_telefono;

    @Column
    private String usuario_fechaRegistro;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<Role> usuario_tipo_usuario;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Orden> usuario_ordenes;

}