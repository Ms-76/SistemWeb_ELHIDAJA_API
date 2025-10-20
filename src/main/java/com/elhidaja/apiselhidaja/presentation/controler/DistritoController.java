package com.elhidaja.apiselhidaja.presentation.controler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.elhidaja.apiselhidaja.presentation.dto.distrito.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.distrito.Request.*;
import com.elhidaja.apiselhidaja.service.implementation.DistritoService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/distritos")
@Validated
public class DistritoController {
     @Autowired
    private DistritoService distritoService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseDistritoAllDTO> getDistritos(
            @Valid @RequestBody RequestDistritoOptionDTO option) {
        return ResponseEntity.ok(distritoService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleDistritoDTO> getByIdDistrito(
            @Valid @RequestBody RequestDistritoFilterDTO id) {
        return ResponseEntity.ok(distritoService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDistritoMensajeDTO> insertarDistrito(
            @Valid @RequestBody RequestDistritoInsertDTO dto) {
        return ResponseEntity.ok(distritoService.insertSer(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDistritoMensajeDTO> actualizarDistrito(
            @Valid @RequestBody RequestDistritoUpdateDTO dto) {
        return ResponseEntity.ok(distritoService.updateSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponseDistritoMensajeDTO> activarDistrito(
            @Valid @RequestBody RequestDistritoIdDTO id) {
        return ResponseEntity.ok(distritoService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponseDistritoMensajeDTO> desactivarDistrito(
            @Valid @RequestBody RequestDistritoIdDTO id) {
        return ResponseEntity.ok(distritoService.desactivateSer(id));
    }
}
