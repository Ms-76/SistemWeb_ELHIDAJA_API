package com.elhidaja.apiselhidaja.presentation.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.elhidaja.apiselhidaja.presentation.dto.arquitecto.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.arquitecto.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.ArquitectoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/arquitectos")
@Validated
public class ArquitectoController {
    @Autowired
    private ArquitectoService arquitectoService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseArquitectoAllDTO> getAll(@Valid @RequestBody RequestArquitectoOptionDTO option) {
        return ResponseEntity.ok(arquitectoService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleArquitectoDTO> getById(@Valid @RequestBody RequestArquitectoFilterDTO id) {
        return ResponseEntity.ok(arquitectoService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseArquitectoMensajeDTO> create(@Valid @RequestBody RequestArquitectoInsertDTO dto) {
        return ResponseEntity.ok(arquitectoService.insertSer(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseArquitectoMensajeDTO> update(@Valid @RequestBody RequestArquitectoUpdateDTO dto) {
        return ResponseEntity.ok(arquitectoService.updateSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponseArquitectoMensajeDTO> activate(@Valid @RequestBody RequestArquitectoIdDTO id) {
        return ResponseEntity.ok(arquitectoService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponseArquitectoMensajeDTO> deactivate(@Valid @RequestBody RequestArquitectoIdDTO id) {
        return ResponseEntity.ok(arquitectoService.desactivateSer(id));
    }
}
