package com.elhidaja.apiselhidaja.presentation.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.elhidaja.apiselhidaja.presentation.dto.inventario.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.inventario.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.InventarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/inventarios")
@Validated
public class InventarioController {
      @Autowired
    private InventarioService inventarioService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseInventarioAllDTO> getInventarios(
            @Valid @RequestBody RequestInventarioOptionDTO option) {
        return ResponseEntity.ok(inventarioService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleInventarioDTO> getByIdInventario(
            @Valid @RequestBody RequestInventarioIdDTO id) {
        return ResponseEntity.ok(inventarioService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseInventarioMensajeDTO> insertarInventario(
            @Valid @RequestBody RequestInventarioInsertDTO dto) {
        return ResponseEntity.ok(inventarioService.insertSer(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseInventarioMensajeDTO> actualizarInventario(
            @Valid @RequestBody RequestInventarioUpdateDTO dto) {
        return ResponseEntity.ok(inventarioService.updateSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponseInventarioMensajeDTO> activarInventario(
            @Valid @RequestBody RequestInventarioIdDTO id) {
        return ResponseEntity.ok(inventarioService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponseInventarioMensajeDTO> desactivarInventario(
            @Valid @RequestBody RequestInventarioIdDTO id) {
        return ResponseEntity.ok(inventarioService.desactivateSer(id));
    }
}
