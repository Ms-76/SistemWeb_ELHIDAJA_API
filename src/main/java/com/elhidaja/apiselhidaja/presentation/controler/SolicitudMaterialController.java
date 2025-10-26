package com.elhidaja.apiselhidaja.presentation.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.elhidaja.apiselhidaja.presentation.dto.solicitudMaterial.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.solicitudMaterial.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.SolicitudMaterialService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/solicitudes-material")
@Validated
public class SolicitudMaterialController {
        @Autowired
    private SolicitudMaterialService solicitudMaterialService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseSolicitudMaterialAllDTO> getSolicitudes(
            @Valid @RequestBody RequestSolicitudMaterialOptionDTO option) {
        return ResponseEntity.ok(solicitudMaterialService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleSolicitudMaterialDTO> getByIdSolicitud(
            @Valid @RequestBody RequestSolicitudMaterialFilterDTO id) {
        return ResponseEntity.ok(solicitudMaterialService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseSolicitudMaterialMensajeDTO> insertarSolicitud(
            @Valid @RequestBody RequestSolicitudMaterialInsertDTO dto) {
        return ResponseEntity.ok(solicitudMaterialService.insertSer(dto));
    }

    @PostMapping("/activate")
    public ResponseEntity<ResponseSolicitudMaterialMensajeDTO> activarSolicitud(
            @Valid @RequestBody RequestSolicitudMaterialIdDTO id) {
        return ResponseEntity.ok(solicitudMaterialService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponseSolicitudMaterialMensajeDTO> desactivarSolicitud(
            @Valid @RequestBody RequestSolicitudMaterialIdDTO id) {
        return ResponseEntity.ok(solicitudMaterialService.desactivateSer(id));
    }
}
