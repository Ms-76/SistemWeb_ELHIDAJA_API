package com.elhidaja.apiselhidaja.presentation.controler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.elhidaja.apiselhidaja.presentation.dto.vehiculoChoferAsignacion.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.vehiculoChoferAsignacion.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.VehiculoChoferAsignacionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/vehiculo-chofer-asignaciones")
@Validated
public class VehiculoChoferAsignacionController {
    @Autowired
    private VehiculoChoferAsignacionService asignacionService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseVehiculoChoferAsignacionAllDTO> getAsignaciones(
            @Valid @RequestBody RequestVehiculoChoferAsignacionOptionDTO option) {
        return ResponseEntity.ok(asignacionService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleVehiculoChoferAsignacionDTO> getByIdAsignacion(
            @Valid @RequestBody RequestVehiculoChoferAsignacionFilterDTO id) {
        return ResponseEntity.ok(asignacionService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseVehiculoChoferAsignacionMensajeDTO> insertarAsignacion(
            @Valid @RequestBody RequestVehiculoChoferAsignacionInsertDTO dto) {
        return ResponseEntity.ok(asignacionService.insertSer(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseVehiculoChoferAsignacionMensajeDTO> actualizarAsignacion(
            @Valid @RequestBody RequestVehiculoChoferAsignacionUpdateDTO dto) {
        return ResponseEntity.ok(asignacionService.updateSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponseVehiculoChoferAsignacionMensajeDTO> activarAsignacion(
            @Valid @RequestBody RequestVehiculoChoferAsignacionIdDTO id) {
        return ResponseEntity.ok(asignacionService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponseVehiculoChoferAsignacionMensajeDTO> desactivarAsignacion(
            @Valid @RequestBody RequestVehiculoChoferAsignacionIdDTO id) {
        return ResponseEntity.ok(asignacionService.desactivateSer(id));
    }
}
