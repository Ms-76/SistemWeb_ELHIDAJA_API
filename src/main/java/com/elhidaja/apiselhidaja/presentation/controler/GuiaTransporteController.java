package com.elhidaja.apiselhidaja.presentation.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.elhidaja.apiselhidaja.presentation.dto.guiaTransporte.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.guiaTransporte.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.GuiaTransporteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/guias-transporte")
@Validated
public class GuiaTransporteController {
      @Autowired
    private GuiaTransporteService guiaService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseGuiaTransporteAllDTO> getGuiasTransporte(
            @Valid @RequestBody RequestGuiaTransporteOptionDTO option) {
        return ResponseEntity.ok(guiaService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleGuiaTransporteDTO> getByIdGuiaTransporte(
            @Valid @RequestBody RequestGuiaTransporteFilterDTO id) {
        return ResponseEntity.ok(guiaService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseGuiaTransporteMensajeDTO> insertarGuiaTransporte(
            @Valid @RequestBody RequestGuiaTransporteInsertDTO dto) {
        return ResponseEntity.ok(guiaService.insertSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponseGuiaTransporteMensajeDTO> activarGuiaTransporte(
            @Valid @RequestBody RequestGuiaTransporteIdDTO id) {
        return ResponseEntity.ok(guiaService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponseGuiaTransporteMensajeDTO> desactivarGuiaTransporte(
            @Valid @RequestBody RequestGuiaTransporteIdDTO id) {
        return ResponseEntity.ok(guiaService.desactivateSer(id));
    }
}
