package com.elhidaja.apiselhidaja.presentation.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.elhidaja.apiselhidaja.presentation.dto.vehiculo.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.vehiculo.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.VehiculoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/vehiculos")
@Validated
public class VehiculoController {
    @Autowired
    private VehiculoService vehiculoService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseVehiculoAllDTO> getVehiculos(
            @Valid @RequestBody RequestVehiculoOptionDTO option) {
        return ResponseEntity.ok(vehiculoService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleVehiculoDTO> getByIdVehiculo(
            @Valid @RequestBody RequestVehiculoFilterDTO id) {
        return ResponseEntity.ok(vehiculoService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseVehiculoMensajeDTO> insertarVehiculo(
            @Valid @RequestBody RequestVehiculoInsertDTO dto) {
        return ResponseEntity.ok(vehiculoService.insertSer(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseVehiculoMensajeDTO> actualizarVehiculo(
            @Valid @RequestBody RequestVehiculoUpdateDTO dto) {
        return ResponseEntity.ok(vehiculoService.updateSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponseVehiculoMensajeDTO> activarVehiculo(
            @Valid @RequestBody RequestVehiculoIdDTO id) {
        return ResponseEntity.ok(vehiculoService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponseVehiculoMensajeDTO> desactivarVehiculo(
            @Valid @RequestBody RequestVehiculoIdDTO id) {
        return ResponseEntity.ok(vehiculoService.desactivateSer(id));
    }
}
