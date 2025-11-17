package com.elhidaja.apiselhidaja.presentation.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.elhidaja.apiselhidaja.presentation.dto.chofer.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.chofer.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.ChoferService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/choferes")
@Validated
public class ChoferController {
    @Autowired
    private ChoferService choferService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseChoferAllDTO> getChoferes(
            @Valid @RequestBody RequestChoferOptionDTO option) {
        return ResponseEntity.ok(choferService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleChoferDTO> getByIdChofer(
            @Valid @RequestBody RequestChoferFilterDTO id) {
        return ResponseEntity.ok(choferService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseChoferMensajeDTO> insertarChofer(
            @Valid @RequestBody RequestChoferInsertDTO dto) {
        return ResponseEntity.ok(choferService.insertSer(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseChoferMensajeDTO> actualizarChofer(
            @Valid @RequestBody RequestChoferUpdateDTO dto) {
        return ResponseEntity.ok(choferService.updateSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponseChoferMensajeDTO> activarChofer(
            @Valid @RequestBody RequestChoferIdDTO id) {
        return ResponseEntity.ok(choferService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponseChoferMensajeDTO> desactivarChofer(
            @Valid @RequestBody RequestChoferIdDTO id) {
        return ResponseEntity.ok(choferService.desactivateSer(id));
    }
}
