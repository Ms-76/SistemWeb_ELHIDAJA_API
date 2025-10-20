package com.elhidaja.apiselhidaja.presentation.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.elhidaja.apiselhidaja.presentation.dto.detalleInventario.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.detalleInventario.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.DetalleInventarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/detalle-inventario")
@Validated
public class DetalleInventarioController {
     @Autowired
    private DetalleInventarioService detalleInventarioService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseDetalleInventarioAllDTO> getDetalleInventario(
            @Valid @RequestBody RequestDetalleInventarioOptionDTO option) {
        return ResponseEntity.ok(detalleInventarioService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleInventarioDTO> getByIdDetalleInventario(
            @Valid @RequestBody RequestDetalleInventarioFilterDTO id) {
        return ResponseEntity.ok(detalleInventarioService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDetalleInventarioMensajeDTO> insertarDetalleInventario(
            @Valid @RequestBody RequestDetalleInventarioInsertDTO dto) {
        return ResponseEntity.ok(detalleInventarioService.insertSer(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDetalleInventarioMensajeDTO> actualizarDetalleInventario(
            @Valid @RequestBody RequestDetalleInventarioUpdateDTO dto) {
        return ResponseEntity.ok(detalleInventarioService.updateSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponseDetalleInventarioMensajeDTO> activarDetalleInventario(
            @Valid @RequestBody RequestDetalleInventarioIdDTO id) {
        return ResponseEntity.ok(detalleInventarioService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponseDetalleInventarioMensajeDTO> desactivarDetalleInventario(
            @Valid @RequestBody RequestDetalleInventarioIdDTO id) {
        return ResponseEntity.ok(detalleInventarioService.desactivateSer(id));
    }

}
