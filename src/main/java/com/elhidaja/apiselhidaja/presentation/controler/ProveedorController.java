package com.elhidaja.apiselhidaja.presentation.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.elhidaja.apiselhidaja.presentation.dto.proveedor.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.proveedor.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.ProveedorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/proveedores")
@Validated
public class ProveedorController {
       @Autowired
    private ProveedorService proveedorService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseProveedorAllDTO> getCategorias(
            @Valid @RequestBody RequestProveedorOptionDTO option) {
        return ResponseEntity.ok(proveedorService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleProveedorDTO> getByIdCategoria(
            @Valid @RequestBody RequestProveedorIdDTO id) {
        return ResponseEntity.ok(proveedorService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseProveedorMensajeDTO> insertarCategoria(
            @Valid @RequestBody RequestProveedorInsertDTO dto) {
        return ResponseEntity.ok(proveedorService.insertSer(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseProveedorMensajeDTO> actualizarCategoria(
            @Valid @RequestBody RequestProveedorUpdateDTO dto) {
        return ResponseEntity.ok(proveedorService.updateSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponseProveedorMensajeDTO> activarCategoria(
            @Valid @RequestBody RequestProveedorIdDTO id) {
        return ResponseEntity.ok(proveedorService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponseProveedorMensajeDTO> desactivarCategoria(
            @Valid @RequestBody RequestProveedorIdDTO id) {
        return ResponseEntity.ok(proveedorService.desactivateSer(id));
    }
}
