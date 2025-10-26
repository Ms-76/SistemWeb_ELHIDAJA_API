package com.elhidaja.apiselhidaja.presentation.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.elhidaja.apiselhidaja.presentation.dto.guiaSalida.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.guiaSalida.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.GuiaSalidaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/guiassalida")
@Validated
public class GuiaSalidaController {
    @Autowired
    private GuiaSalidaService guiaSalidaService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseGuiaSalidaAllDTO> getGuiasSalida(
            @Valid @RequestBody RequestGuiaSalidaOptionDTO option) {
        return ResponseEntity.ok(guiaSalidaService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleGuiaSalidaDTO> getByIdGuiaSalida(
            @Valid @RequestBody RequestGuiaSalidaFilterDTO id) {
        return ResponseEntity.ok(guiaSalidaService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseGuiaSalidaMensajeDTO> insertarGuiaSalida(
            @Valid @RequestBody RequestGuiaSalidaInsertDTO dto) {
        return ResponseEntity.ok(guiaSalidaService.insertSer(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseGuiaSalidaMensajeDTO> actualizarGuiaSalida(
            @Valid @RequestBody RequestGuiaSalidaUpdateDTO dto) {
        return ResponseEntity.ok(guiaSalidaService.updateSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponseGuiaSalidaMensajeDTO> activarGuiaSalida(
            @Valid @RequestBody RequestGuiaSalidaIdDTO id) {
        return ResponseEntity.ok(guiaSalidaService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponseGuiaSalidaMensajeDTO> desactivarGuiaSalida(
            @Valid @RequestBody RequestGuiaSalidaIdDTO id) {
        return ResponseEntity.ok(guiaSalidaService.desactivateSer(id));
    }

}
