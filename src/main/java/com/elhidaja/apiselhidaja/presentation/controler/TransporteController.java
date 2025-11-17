package com.elhidaja.apiselhidaja.presentation.controler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.elhidaja.apiselhidaja.presentation.dto.transporte.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.transporte.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.TransporteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/transportes")
@Validated
public class TransporteController {
      @Autowired
    private TransporteService transporteService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseTransporteAllDTO> getTransportes(
            @Valid @RequestBody RequestTransporteOptionDTO option) {
        return ResponseEntity.ok(transporteService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleTransporteDTO> getByIdTransporte(
            @Valid @RequestBody RequestTransporteFilterDTO id) {
        return ResponseEntity.ok(transporteService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseTransporteMensajeDTO> insertarTransporte(
            @Valid @RequestBody RequestTransporteInsertDTO dto) {
        return ResponseEntity.ok(transporteService.insertSer(dto));
    }
/* 
    @PutMapping("/update")
    public ResponseEntity<ResponseTransporteMensajeDTO> actualizarTransporte(
            @Valid @RequestBody RequestTransporteUpdateDTO dto) {
        return ResponseEntity.ok(transporteService.updateSer(dto));
    }
*/
    @PutMapping("/activate")
    public ResponseEntity<ResponseTransporteMensajeDTO> activarTransporte(
            @Valid @RequestBody RequestTransporteIdDTO id) {
        return ResponseEntity.ok(transporteService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponseTransporteMensajeDTO> desactivarTransporte(
            @Valid @RequestBody RequestTransporteIdDTO id) {
        return ResponseEntity.ok(transporteService.desactivateSer(id));
    }
}
