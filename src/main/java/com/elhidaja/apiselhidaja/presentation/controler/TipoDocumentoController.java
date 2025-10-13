package com.elhidaja.apiselhidaja.presentation.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.elhidaja.apiselhidaja.presentation.dto.tipoDocumento.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.tipoDocumento.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.TipoDocumentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tipo-documentos")
@Validated
public class TipoDocumentoController {
    @Autowired
    private TipoDocumentoService tipoDocumentoService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseTipoDocumentoAllDTO> getTipoDocumentos(
            @Valid @RequestBody RequestTipoDocumentoOptionDTO option) {
        return ResponseEntity.ok(tipoDocumentoService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleTipoDocumentoDTO> getByIdTipoDocumento(
            @Valid @RequestBody RequestTipoDocumentoIdDTO id) {
        return ResponseEntity.ok(tipoDocumentoService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseTipoDocumentoMensajeDTO> insertarTipoDocumento(
            @Valid @RequestBody RequestTipoDocumentoInsertDTO dto) {
        return ResponseEntity.ok(tipoDocumentoService.insertSer(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseTipoDocumentoMensajeDTO> actualizarTipoDocumento(
            @Valid @RequestBody RequestTipoDocumentoUpdateDTO dto) {
        return ResponseEntity.ok(tipoDocumentoService.updateSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponseTipoDocumentoMensajeDTO> activarTipoDocumento(
            @Valid @RequestBody RequestTipoDocumentoIdDTO id) {
        return ResponseEntity.ok(tipoDocumentoService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponseTipoDocumentoMensajeDTO> desactivarTipoDocumento(
            @Valid @RequestBody RequestTipoDocumentoIdDTO id) {
        return ResponseEntity.ok(tipoDocumentoService.desactivateSer(id));
    }
}
