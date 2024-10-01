package com.uade.tpo.backendGrupo6MieMa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DataCleanupService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void deleteAllData() {
        // Add SQL commands to delete data from each table
        jdbcTemplate.execute("DELETE FROM Categoria");
        jdbcTemplate.execute("DELETE FROM Marca");
        // Add more tables as needed
    }

    @Transactional
    public void deleteAllDataAndResetId() {
        // Eliminar todos los datos de la tabla Categoria
        jdbcTemplate.execute("DELETE FROM Categoria");

        // Resetear el contador del ID (MySQL)
        jdbcTemplate.execute("ALTER TABLE Categoria AUTO_INCREMENT = 1");

    }

}