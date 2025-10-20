package com.elhidaja.apiselhidaja.presentation.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.elhidaja.apiselhidaja.presentation.dto.detalleSolicitudMaterial.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.detalleSolicitudMaterial.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.DetalleSolicitudMaterialService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/detallessolicitudmaterial")
@Validated
public class DetalleSolicitudMaterialController {
    @Autowired
    private DetalleSolicitudMaterialService service;

    @PostMapping("/getall")
    public ResponseEntity<ResponseDetalleSolicitudMaterialAllDTO> getAll(@Valid @RequestBody RequestDetalleSolicitudMaterialOptionDTO option) {
        return ResponseEntity.ok(service.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleSolicitudMaterialDTO> getById(@Valid @RequestBody RequestDetalleSolicitudMaterialFilterDTO id) {
        return ResponseEntity.ok(service.getByIdSer(id));
    }


    @PutMapping("/activate")
    public ResponseEntity<ResponserDetalleSolicitudMaterialMensajeDTO> activate(@Valid @RequestBody RequestDetalleSolicitudMaterialIdDTO id) {
        return ResponseEntity.ok(service.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponserDetalleSolicitudMaterialMensajeDTO> deactivate(@Valid @RequestBody RequestDetalleSolicitudMaterialIdDTO id) {
        return ResponseEntity.ok(service.desactivateSer(id));
    }
}
