package com.elhidaja.apiselhidaja.presentation.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.elhidaja.apiselhidaja.presentation.dto.departamento.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.departamento.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.DepartamentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/departamentos")
@Validated
public class DepartamentoController {
    
    @Autowired
    private DepartamentoService departamentoService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseDepartamentoAllDTO> getDepartamentos(
            @Valid @RequestBody RequestDepartamentoOptionDTO option) {
        return ResponseEntity.ok(departamentoService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleDepartamentoDTO> getByIdDepartamento(
            @Valid @RequestBody RequestDepartamentoIdDTO id) {
        return ResponseEntity.ok(departamentoService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDepartamentoMensajeDTO> insertarDepartamento(
            @Valid @RequestBody RequestDepartamentoInsertDTO dto) {
        return ResponseEntity.ok(departamentoService.insertSer(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDepartamentoMensajeDTO> actualizarDepartamento(
            @Valid @RequestBody RequestDepartamentoUpdateDTO dto) {
        return ResponseEntity.ok(departamentoService.updateSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponseDepartamentoMensajeDTO> activarDepartamento(
            @Valid @RequestBody RequestDepartamentoIdDTO id) {
        return ResponseEntity.ok(departamentoService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponseDepartamentoMensajeDTO> desactivarDepartamento(
            @Valid @RequestBody RequestDepartamentoIdDTO id) {
        return ResponseEntity.ok(departamentoService.desactivateSer(id));
    }
}
