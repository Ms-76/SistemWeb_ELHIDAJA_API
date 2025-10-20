package com.elhidaja.apiselhidaja.presentation.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.elhidaja.apiselhidaja.presentation.dto.maestroobra.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.maestroobra.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.MaestroObraService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/maestrosobra")
@Validated
public class MaestroObraController {
    @Autowired
    private MaestroObraService maestroObraService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseMaestroObraAllDTO> getAll(@Valid @RequestBody RequestMaestroObraOptionDTO option) {
        return ResponseEntity.ok(maestroObraService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleMaestroObraDTO> getById(@Valid @RequestBody RequestMaestroObraFilterDTO id) {
        return ResponseEntity.ok(maestroObraService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseMaestroObraMensajeDTO> create(@Valid @RequestBody RequestMaestroObraInsertDTO dto) {
        return ResponseEntity.ok(maestroObraService.insertSer(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseMaestroObraMensajeDTO> update(@Valid @RequestBody RequestMaestroObraUpdateDTO dto) {
        return ResponseEntity.ok(maestroObraService.updateSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponseMaestroObraMensajeDTO> activate(@Valid @RequestBody RequestMaestroObraIdDTO id) {
        return ResponseEntity.ok(maestroObraService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponseMaestroObraMensajeDTO> deactivate(@Valid @RequestBody RequestMaestroObraIdDTO id) {
        return ResponseEntity.ok(maestroObraService.desactivateSer(id));
    }
}
