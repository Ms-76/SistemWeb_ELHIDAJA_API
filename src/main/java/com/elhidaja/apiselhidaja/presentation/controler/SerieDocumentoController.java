package com.elhidaja.apiselhidaja.presentation.controler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.elhidaja.apiselhidaja.presentation.dto.serieDocumento.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.serieDocumento.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.SerieDocumentoService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/seriedocumento")
@Validated
public class SerieDocumentoController {
    
    @Autowired
    private SerieDocumentoService serieDocumentoService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseSerieDocumentoAllDTO> getSerieDocumentos(
            @Valid @RequestBody RequestSerieDocumentoOptionDTO option) {
        return ResponseEntity.ok(serieDocumentoService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleSerieDocumentoDTO> getByIdSerieDocumento(
            @Valid @RequestBody RequestSerieDocumentoIdDTO id) {
        return ResponseEntity.ok(serieDocumentoService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseSerieDocumentoMensajeDTO> insertarSerieDocumento(
            @Valid @RequestBody RequestSerieDocumentoInsertDTO dto) {
        return ResponseEntity.ok(serieDocumentoService.insertSer(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseSerieDocumentoMensajeDTO> actualizarSerieDocumento(
            @Valid @RequestBody RequestSerieDocumentoUpdateDTO dto) {
        return ResponseEntity.ok(serieDocumentoService.updateSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponseSerieDocumentoMensajeDTO> activarSerieDocumento(
            @Valid @RequestBody RequestSerieDocumentoIdDTO id) {
        return ResponseEntity.ok(serieDocumentoService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponseSerieDocumentoMensajeDTO> desactivarSerieDocumento(
            @Valid @RequestBody RequestSerieDocumentoIdDTO id) {
        return ResponseEntity.ok(serieDocumentoService.desactivateSer(id));
    }
}
