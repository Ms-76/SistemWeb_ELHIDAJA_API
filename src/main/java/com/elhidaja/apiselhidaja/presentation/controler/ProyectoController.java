package com.elhidaja.apiselhidaja.presentation.controler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.elhidaja.apiselhidaja.presentation.dto.proyecto.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.proyecto.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.ProyectoService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/proyectos")
@Validated
public class ProyectoController {
       @Autowired
    private ProyectoService proyectoService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseProyectoAllDTO> getProyectos(
            @Valid @RequestBody RequestProyectoOptionDTO option) {
        return ResponseEntity.ok(proyectoService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleProyectoDTO> getByIdProyecto(
            @Valid @RequestBody RequestProyectoFilterDTO id) {
        return ResponseEntity.ok(proyectoService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseProyectoMensajeDTO> insertarProyecto(
            @Valid @RequestBody RequestProyectoInsertDTO dto) {
        return ResponseEntity.ok(proyectoService.insertSer(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseProyectoMensajeDTO> actualizarProyecto(
            @Valid @RequestBody RequestProyectoUpdateDTO dto) {
        return ResponseEntity.ok(proyectoService.updateSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponseProyectoMensajeDTO> activarProyecto(
            @Valid @RequestBody RequestProyectoIdDTO id) {
        return ResponseEntity.ok(proyectoService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponseProyectoMensajeDTO> desactivarProyecto(
            @Valid @RequestBody RequestProyectoIdDTO id) {
        return ResponseEntity.ok(proyectoService.desactivateSer(id));
    }
}
