package com.elhidaja.apiselhidaja.presentation.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import com.elhidaja.apiselhidaja.presentation.dto.ingeniero.Request.RequestIngenieroFilterDTO;
import com.elhidaja.apiselhidaja.presentation.dto.ingeniero.Request.RequestIngenieroIdDTO;
import com.elhidaja.apiselhidaja.presentation.dto.ingeniero.Request.RequestIngenieroInsertDTO;
import com.elhidaja.apiselhidaja.presentation.dto.ingeniero.Request.RequestIngenieroOptionDTO;
import com.elhidaja.apiselhidaja.presentation.dto.ingeniero.Request.RequestIngenieroUpdateDTO;
import com.elhidaja.apiselhidaja.presentation.dto.ingeniero.Response.ResponseDetalleIngenieroDTO;
import com.elhidaja.apiselhidaja.presentation.dto.ingeniero.Response.ResponseIngenieroAllDTO;
import com.elhidaja.apiselhidaja.presentation.dto.ingeniero.Response.ResponseIngenieroMensajeDTO;
import com.elhidaja.apiselhidaja.service.implementation.IngenieroService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/ingenieros")
@Validated
public class IngenieroController {
        @Autowired
    private IngenieroService ingenieroService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseIngenieroAllDTO> getIngenieros(
            @Valid @RequestBody RequestIngenieroOptionDTO option) {
        return ResponseEntity.ok(ingenieroService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleIngenieroDTO> getByIdIngeniero(
            @Valid @RequestBody RequestIngenieroFilterDTO id) {
        return ResponseEntity.ok(ingenieroService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseIngenieroMensajeDTO> insertarIngeniero(
            @Valid @RequestBody RequestIngenieroInsertDTO dto) {
        return ResponseEntity.ok(ingenieroService.insertSer(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseIngenieroMensajeDTO> actualizarIngeniero(
            @Valid @RequestBody RequestIngenieroUpdateDTO dto) {
        return ResponseEntity.ok(ingenieroService.updateSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponseIngenieroMensajeDTO> activarIngeniero(
            @Valid @RequestBody RequestIngenieroIdDTO id) {
        return ResponseEntity.ok(ingenieroService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponseIngenieroMensajeDTO> desactivarIngeniero(
            @Valid @RequestBody RequestIngenieroIdDTO id) {
        return ResponseEntity.ok(ingenieroService.desactivateSer(id));
    }
}
