package com.elhidaja.apiselhidaja.presentation.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.elhidaja.apiselhidaja.presentation.dto.documentoOperacion.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.documentoOperacion.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.DocumentoOperacionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/documento-operacion")
@Validated
public class DocumentoOperacionController {
    @Autowired
    private DocumentoOperacionService documentoOperacionService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseDocumentoOperacionAllDTO> getDocumentoOperaciones(
            @Valid @RequestBody RequestDocumentoOperacionOptionDTO option) {
        return ResponseEntity.ok(documentoOperacionService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleDocumentoOperacionDTO> getByIdDocumentoOperacion(
            @Valid @RequestBody RequestDocumentoOperacionFilterDTO id) {
        return ResponseEntity.ok(documentoOperacionService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDocumentoOperacionMensajeDTO> insertarDocumentoOperacion(
            @Valid @RequestBody RequestDocumentoOperacionInsertDTO dto) {
        return ResponseEntity.ok(documentoOperacionService.insertSer(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDocumentoOperacionMensajeDTO> actualizarDocumentoOperacion(
            @Valid @RequestBody RequestDocumentoOperacionUpdateDTO dto) {
        return ResponseEntity.ok(documentoOperacionService.updateSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponseDocumentoOperacionMensajeDTO> activarDocumentoOperacion(
            @Valid @RequestBody RequestDocumentoOperacionIdDTO id) {
        return ResponseEntity.ok(documentoOperacionService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponseDocumentoOperacionMensajeDTO> desactivarDocumentoOperacion(
            @Valid @RequestBody RequestDocumentoOperacionIdDTO id) {
        return ResponseEntity.ok(documentoOperacionService.desactivateSer(id));
    }
}
