package com.elhidaja.apiselhidaja.presentation.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.elhidaja.apiselhidaja.presentation.dto.area.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.area.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.AreaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/areas")
@Validated
public class AreaController {
    @Autowired
    private AreaService areaService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseAreaAllDTO> getAreas(
            @Valid @RequestBody RequestAreaOptionDTO option) {
        return ResponseEntity.ok(areaService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleAreaDTO> getByIdArea(
            @Valid @RequestBody RequestAreaFilterDTO id) {
        return ResponseEntity.ok(areaService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseAreaMensajeDTO> insertarArea(
            @Valid @RequestBody RequestAreaInsertDTO dto) {
        return ResponseEntity.ok(areaService.insertSer(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseAreaMensajeDTO> actualizarArea(
            @Valid @RequestBody RequestAreaUpdateDTO dto) {
        return ResponseEntity.ok(areaService.updateSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponseAreaMensajeDTO> activarArea(
            @Valid @RequestBody RequestAreaIdDTO id) {
        return ResponseEntity.ok(areaService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponseAreaMensajeDTO> desactivarArea(
            @Valid @RequestBody RequestAreaIdDTO id) {
        return ResponseEntity.ok(areaService.desactivateSer(id));
    }
}
