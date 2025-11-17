package com.elhidaja.apiselhidaja.presentation.controler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.elhidaja.apiselhidaja.presentation.dto.transporteDetalle.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.transporteDetalle.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.TransporteDetalleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/transporte-detalles")
@Validated
public class TransporteDetalleController {
     @Autowired
    private TransporteDetalleService transporteDetalleService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseTransporteDetalleAllDTO> getDetalles(
            @Valid @RequestBody RequestTransporteDetalleOptionDTO option) {
        return ResponseEntity.ok(transporteDetalleService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleTransporteDetalleDTO> getByIdDetalle(
            @Valid @RequestBody RequestTransporteDetalleFilterDTO id) {
        return ResponseEntity.ok(transporteDetalleService.getByIdSer(id));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponseTransporteDetalleMensajeDTO> activarDetalle(
            @Valid @RequestBody RequestTransporteDetalleIdDTO id) {
        return ResponseEntity.ok(transporteDetalleService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponseTransporteDetalleMensajeDTO> desactivarDetalle(
            @Valid @RequestBody RequestTransporteDetalleIdDTO id) {
        return ResponseEntity.ok(transporteDetalleService.desactivateSer(id));
    }
}
