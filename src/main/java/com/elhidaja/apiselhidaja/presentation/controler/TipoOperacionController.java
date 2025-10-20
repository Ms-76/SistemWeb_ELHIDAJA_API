package com.elhidaja.apiselhidaja.presentation.controler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.elhidaja.apiselhidaja.presentation.dto.tipoOperacion.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.tipoOperacion.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.TipoOperacionService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/tipo-operaciones")
@Validated
public class TipoOperacionController {
        @Autowired
    private TipoOperacionService tipoOperacionService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseTipoOperacionAllDTO> getTipoOperaciones(
            @Valid @RequestBody RequestTipoOperacionOptionDTO option) {
        return ResponseEntity.ok(tipoOperacionService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleTipoOperacionDTO> getByIdTipoOperacion(
            @Valid @RequestBody RequestTipoOperacionFilterDTO id) {
        return ResponseEntity.ok(tipoOperacionService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseTipoOperacionMensajeDTO> insertarTipoOperacion(
            @Valid @RequestBody RequestTipoOperacionInsertDTO dto) {
        return ResponseEntity.ok(tipoOperacionService.insertSer(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseTipoOperacionMensajeDTO> actualizarTipoOperacion(
            @Valid @RequestBody RequestTipoOperacionUpdateDTO dto) {
        return ResponseEntity.ok(tipoOperacionService.updateSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponseTipoOperacionMensajeDTO> activarTipoOperacion(
            @Valid @RequestBody RequestTipoOperacionIdDTO id) {
        return ResponseEntity.ok(tipoOperacionService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponseTipoOperacionMensajeDTO> desactivarTipoOperacion(
            @Valid @RequestBody RequestTipoOperacionIdDTO id) {
        return ResponseEntity.ok(tipoOperacionService.desactivateSer(id));
    }
}
