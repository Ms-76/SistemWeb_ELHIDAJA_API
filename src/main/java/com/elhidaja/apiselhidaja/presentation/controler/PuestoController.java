package com.elhidaja.apiselhidaja.presentation.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.elhidaja.apiselhidaja.presentation.dto.puesto.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.puesto.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.PuestoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/puestos")
@Validated
public class PuestoController {
    @Autowired
    private PuestoService puestoService;

    @PostMapping("/getall")
    public ResponseEntity<ResponsePuestoAllDTO> getPuestos(
            @Valid @RequestBody RequestPuestoOptionDTO option) {
        return ResponseEntity.ok(puestoService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetallePuestoDTO> getByIdPuesto(
            @Valid @RequestBody RequestPuestoIdDTO id) {
        return ResponseEntity.ok(puestoService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponsePuestoMensajeDTO> insertarPuesto(
            @Valid @RequestBody RequestPuestoInsertDTO dto) {
        return ResponseEntity.ok(puestoService.insertSer(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponsePuestoMensajeDTO> actualizarPuesto(
            @Valid @RequestBody RequestPuestoUpdateDTO dto) {
        return ResponseEntity.ok(puestoService.updateSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponsePuestoMensajeDTO> activarPuesto(
            @Valid @RequestBody RequestPuestoIdDTO id) {
        return ResponseEntity.ok(puestoService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponsePuestoMensajeDTO> desactivarPuesto(
            @Valid @RequestBody RequestPuestoIdDTO id) {
        return ResponseEntity.ok(puestoService.desactivateSer(id));
    }
}
