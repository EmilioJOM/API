package com.uade.tpo.backendGrupo6MieMa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.backendGrupo6MieMa.service.DataCleanupService;

@RestController
@RequestMapping("/cleanup")
public class DataCleanupController {

    @Autowired
    private DataCleanupService dataCleanupService;

    @DeleteMapping("/deleteAllData")
    public ResponseEntity<Void> deleteAllData() {
        dataCleanupService.deleteAllData();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAllDataAndResetId")
    public ResponseEntity<Void> deleteAllDataAndResetId() {
        dataCleanupService.deleteAllDataAndResetId();
        return ResponseEntity.noContent().build();
    }

}