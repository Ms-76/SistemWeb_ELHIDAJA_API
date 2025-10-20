package com.elhidaja.apiselhidaja.presentation.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.elhidaja.apiselhidaja.presentation.dto.oficio.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.oficio.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.OficioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/oficios")
@Validated
public class OficioController {
        @Autowired
    private OficioService oficioService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseOficioAllDTO> getOficios(
            @Valid @RequestBody RequestOficioOptionDTO option) {
        return ResponseEntity.ok(oficioService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleOficioDTO> getByIdOficio(
            @Valid @RequestBody RequestOficioFilterDTO id) {
        return ResponseEntity.ok(oficioService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseOficioMensajeDTO> insertarOficio(
            @Valid @RequestBody RequestOficioInsertDTO dto) {
        return ResponseEntity.ok(oficioService.insertSer(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseOficioMensajeDTO> actualizarOficio(
            @Valid @RequestBody RequestOficioUpdateDTO dto) {
        return ResponseEntity.ok(oficioService.updateSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponseOficioMensajeDTO> activarOficio(
            @Valid @RequestBody RequestOficioIdDTO id) {
        return ResponseEntity.ok(oficioService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponseOficioMensajeDTO> desactivarOficio(
            @Valid @RequestBody RequestOficioIdDTO id) {
        return ResponseEntity.ok(oficioService.desactivateSer(id));
    }
}
