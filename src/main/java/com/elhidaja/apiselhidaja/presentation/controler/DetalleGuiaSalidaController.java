package com.elhidaja.apiselhidaja.presentation.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaSalida.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaSalida.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.DetalleGuiaSalidaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/detallesguiassalida")
@Validated
public class DetalleGuiaSalidaController {
    @Autowired
    private DetalleGuiaSalidaService service;

    @PostMapping("/getall")
    public ResponseEntity<ResponseDetalleGuiaSalidaAllDTO> getAll(@Valid @RequestBody RequestDetalleGuiaSalidaOptionDTO option) {
        return ResponseEntity.ok(service.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleGuiaSalidaDTO> getById(@Valid @RequestBody RequestDetalleGuiaSalidaFilterDTO id) {
        return ResponseEntity.ok(service.getByIdSer(id));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponseDetalleGuiaSalidaMensajeDTO> activate(@Valid @RequestBody RequestDetalleGuiaSalidaIdDTO id) {
        return ResponseEntity.ok(service.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponseDetalleGuiaSalidaMensajeDTO> deactivate(@Valid @RequestBody RequestDetalleGuiaSalidaIdDTO id) {
        return ResponseEntity.ok(service.desactivateSer(id));
    }
}
