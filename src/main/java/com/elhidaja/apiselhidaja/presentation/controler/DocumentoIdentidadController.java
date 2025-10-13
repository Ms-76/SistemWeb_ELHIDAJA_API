package com.elhidaja.apiselhidaja.presentation.controler;
import com.elhidaja.apiselhidaja.presentation.dto.documentoIdentidad.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.DocumentoIdentidadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.elhidaja.apiselhidaja.presentation.dto.documentoIdentidad.Request.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/documentos-identidad")
@Validated
public class DocumentoIdentidadController {
    @Autowired
    private DocumentoIdentidadService documentoIdentidadService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseDocumentoIdentidadAllDTO> getDocumentos(
            @Valid @RequestBody RequestDocumentoIdentidadOptionDTO option) {
        return ResponseEntity.ok(documentoIdentidadService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleDocumentoIdentidadDTO> getByIdDocumento(
            @Valid @RequestBody RequestDocumentoIdentidadIdDTO id) {
        return ResponseEntity.ok(documentoIdentidadService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDocumentoIdentidadMensajeDTO> insertarDocumento(
            @Valid @RequestBody RequestDocumentoIdentidadInsertDTO dto) {
        return ResponseEntity.ok(documentoIdentidadService.insertSer(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDocumentoIdentidadMensajeDTO> actualizarDocumento(
            @Valid @RequestBody RequestDocumentoIdentidadUpdateDTO dto) {
        return ResponseEntity.ok(documentoIdentidadService.updateSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponseDocumentoIdentidadMensajeDTO> activarDocumento(
            @Valid @RequestBody RequestDocumentoIdentidadIdDTO id) {
        return ResponseEntity.ok(documentoIdentidadService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponseDocumentoIdentidadMensajeDTO> desactivarDocumento(
            @Valid @RequestBody RequestDocumentoIdentidadIdDTO id) {
        return ResponseEntity.ok(documentoIdentidadService.desactivateSer(id));
    }
}
