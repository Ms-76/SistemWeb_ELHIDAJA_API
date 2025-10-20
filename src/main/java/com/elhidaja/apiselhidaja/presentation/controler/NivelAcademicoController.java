package com.elhidaja.apiselhidaja.presentation.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.elhidaja.apiselhidaja.presentation.dto.nivelAcademico.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.nivelAcademico.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.NivelAcademicoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/nivelesacademicos")
@Validated
public class NivelAcademicoController {
    @Autowired
    private NivelAcademicoService nivelAcademicoService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseNivelAcademicoAllDTO> getNivelesAcademicos(
            @Valid @RequestBody RequestNivelAcademicoOptionDTO option) {
        return ResponseEntity.ok(nivelAcademicoService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleNivelAcademicoDTO> getByIdNivelAcademico(
            @Valid @RequestBody RequestNivelAcademicoFilterDTO id) {
        return ResponseEntity.ok(nivelAcademicoService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseNivelAcademicoMensajeDTO> insertarNivelAcademico(
            @Valid @RequestBody RequestNivelAcademicoInsertDTO dto) {
        return ResponseEntity.ok(nivelAcademicoService.insertSer(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseNivelAcademicoMensajeDTO> actualizarNivelAcademico(
            @Valid @RequestBody RequestNivelAcademicoUpdateDTO dto) {
        return ResponseEntity.ok(nivelAcademicoService.updateSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponseNivelAcademicoMensajeDTO> activarNivelAcademico(
            @Valid @RequestBody RequestNivelAcademicoIdDTO id) {
        return ResponseEntity.ok(nivelAcademicoService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponseNivelAcademicoMensajeDTO> desactivarNivelAcademico(
            @Valid @RequestBody RequestNivelAcademicoIdDTO id) {
        return ResponseEntity.ok(nivelAcademicoService.desactivateSer(id));
    }
}
