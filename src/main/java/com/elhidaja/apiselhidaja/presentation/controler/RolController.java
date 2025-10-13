package com.elhidaja.apiselhidaja.presentation.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.elhidaja.apiselhidaja.presentation.dto.rol.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.rol.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.RolService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/roles")
@Validated
public class RolController {
    @Autowired
    private RolService rolService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseRolAllDTO> getRoles(
            @Valid @RequestBody RequestRolOptionDTO option) {
        return ResponseEntity.ok(rolService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleRolDTO> getByIdRol(
            @Valid @RequestBody RequestRolIdDTO id) {
        return ResponseEntity.ok(rolService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseRolMensajeDTO> insertarRol(
            @Valid @RequestBody RequestRolInsertDTO dto) {
        return ResponseEntity.ok(rolService.insertSer(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseRolMensajeDTO> actualizarRol(
            @Valid @RequestBody RequestRolUpdateDTO dto) {
        return ResponseEntity.ok(rolService.updateSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponseRolMensajeDTO> activarRol(
            @Valid @RequestBody RequestRolIdDTO id) {
        return ResponseEntity.ok(rolService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponseRolMensajeDTO> desactivarRol(
            @Valid @RequestBody RequestRolIdDTO id) {
        return ResponseEntity.ok(rolService.deactivateSer(id));
    }

}
