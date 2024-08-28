package com.uade.tpo.backendGrupo6MieMa.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
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

    @Column
    private String usuario_tipo_usuario;


    @OneToMany(mappedBy = "usuario")
    private List<Orden> usuario_ordenes;

//    @ManyToMany
//    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private List<Role> roles;
}
