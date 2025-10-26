package com.elhidaja.apiselhidaja.presentation.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.elhidaja.apiselhidaja.presentation.dto.guiaEntrada.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.guiaEntrada.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.guiaSalida.Request.RequestRecibirDesdeGuiaSalidaDTO;
import com.elhidaja.apiselhidaja.presentation.dto.guiaSalida.Response.ResponseGuiaSalidaMensajeDTO;
import com.elhidaja.apiselhidaja.service.implementation.GuiaEntradaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/guiasentrada")
@Validated
public class GuiaEntradaController {
    @Autowired
    private GuiaEntradaService guiaEntradaService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseGuiaEntradaAllDTO> getGuiasEntrada(
            @Valid @RequestBody RequestGuiaEntradaOptionDTO option) {
        return ResponseEntity.ok(guiaEntradaService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleGuiaEntradaDTO> getByIdGuiaEntrada(
            @Valid @RequestBody RequestGuiaEntradaFilterDTO id) {
        return ResponseEntity.ok(guiaEntradaService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseGuiaEntradaMensajeDTO> insertarGuiaEntrada(
            @Valid @RequestBody RequestGuiaEntradaInsertDTO dto) {
        return ResponseEntity.ok(guiaEntradaService.insertSer(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseGuiaEntradaMensajeDTO> actualizarGuiaEntrada(
            @Valid @RequestBody RequestGuiaEntradaUpdateDTO dto) {
        return ResponseEntity.ok(guiaEntradaService.updateSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponseGuiaEntradaMensajeDTO> activarGuiaEntrada(
            @Valid @RequestBody RequestGuiaEntradaIdDTO id) {
        return ResponseEntity.ok(guiaEntradaService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponseGuiaEntradaMensajeDTO> desactivarGuiaEntrada(
            @Valid @RequestBody RequestGuiaEntradaIdDTO id) {
        return ResponseEntity.ok(guiaEntradaService.desactivateSer(id));
    }

    @PostMapping("/receive-from-salida")
    public ResponseEntity<ResponseGuiaSalidaMensajeDTO> recibirDesdeGuiaSalida(
            @Valid @RequestBody RequestRecibirDesdeGuiaSalidaDTO dto) {
        return ResponseEntity.ok(guiaEntradaService.recibirDesdeGuiaSalidaSer(dto));
    }

}
