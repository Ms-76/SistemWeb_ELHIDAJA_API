package com.elhidaja.apiselhidaja.presentation.controler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaEntrada.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaEntrada.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.DetalleGuiaEntradaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/detalle-guia-entrada")
@Validated
public class DetalleGuiaEntradaController {
    
    @Autowired
    private DetalleGuiaEntradaService detalleGuiaEntradaService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseDetalleGuiaEntradaAllDTO> getDetalleGuiaEntradas(
            @Valid @RequestBody RequestDetalleGuiaEntradaOptionDTO option) {
        return ResponseEntity.ok(detalleGuiaEntradaService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleGuiaEntradaDTO> getByIdDetalleGuiaEntrada(
            @Valid @RequestBody RequestDetalleGuiaEntradaFilterDTO id) {
        return ResponseEntity.ok(detalleGuiaEntradaService.getByIdSer(id));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDetalleGuiaEntradaMensajeDTO> actualizarDetalleGuiaEntrada(
            @Valid @RequestBody RequestActualizarObservacionDetalleGuiaEntradaDTO dto) {
        return ResponseEntity.ok(detalleGuiaEntradaService.updateSer(dto));
    }
    @PutMapping("/activate")
    public ResponseEntity<ResponseDetalleGuiaEntradaMensajeDTO> activarDetalleGuiaEntrada(
            @Valid @RequestBody RequestDetalleGuiaEntradaIdDTO id) {
        return ResponseEntity.ok(detalleGuiaEntradaService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponseDetalleGuiaEntradaMensajeDTO> desactivarDetalleGuiaEntrada(
            @Valid @RequestBody RequestDetalleGuiaEntradaIdDTO id) {
        return ResponseEntity.ok(detalleGuiaEntradaService.desactivateSer(id));
    }

}
