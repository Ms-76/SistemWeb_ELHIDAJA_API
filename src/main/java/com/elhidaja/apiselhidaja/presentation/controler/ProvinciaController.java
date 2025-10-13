package com.elhidaja.apiselhidaja.presentation.controler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.elhidaja.apiselhidaja.presentation.dto.provincia.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.provincia.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.ProvinciaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/provincias")
@Validated
public class ProvinciaController {
    
    @Autowired
    private ProvinciaService provinciaService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseProvinciaAllDTO> getProvincias(
            @Valid @RequestBody RequestProvinciaOptionDTO option) {
        return ResponseEntity.ok(provinciaService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleProvinciaDTO> getByIdProvincia(
            @Valid @RequestBody RequestProvinciaIdDTO id) {
        return ResponseEntity.ok(provinciaService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseProvinciaMensajeDTO> insertarProvincia(
            @Valid @RequestBody RequestProvinciaInsertDTO dto) {
        return ResponseEntity.ok(provinciaService.insertSer(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseProvinciaMensajeDTO> actualizarProvincia(
            @Valid @RequestBody RequestProvinciaUpdateDTO dto) {
        return ResponseEntity.ok(provinciaService.updateSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponseProvinciaMensajeDTO> activarProvincia(
            @Valid @RequestBody RequestProvinciaIdDTO id) {
        return ResponseEntity.ok(provinciaService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponseProvinciaMensajeDTO> desactivarProvincia(
            @Valid @RequestBody RequestProvinciaIdDTO id) {
        return ResponseEntity.ok(provinciaService.desactivateSer(id));
    }
}
