package com.elhidaja.apiselhidaja.presentation.controler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.elhidaja.apiselhidaja.presentation.dto.serie.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.serie.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.SerieService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/series")
@Validated
public class SerieController {
     @Autowired
    private SerieService serieService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseSerieAllDTO> getSeries(
            @Valid @RequestBody RequestSerieOptionDTO option) {
        return ResponseEntity.ok(serieService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleSerieDTO> getByIdSerie(
            @Valid @RequestBody RequestSerieFilterDTO id) {
        return ResponseEntity.ok(serieService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseSerieMensajeDTO> insertarSerie(
            @Valid @RequestBody RequestSerieInsertDTO dto) {
        return ResponseEntity.ok(serieService.insertSer(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseSerieMensajeDTO> actualizarSerie(
            @Valid @RequestBody RequestSerieUpdateDTO dto) {
        return ResponseEntity.ok(serieService.updateSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponseSerieMensajeDTO> activarSerie(
            @Valid @RequestBody RequestSerieIdDTO id) {
        return ResponseEntity.ok(serieService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponseSerieMensajeDTO> desactivarSerie(
            @Valid @RequestBody RequestSerieIdDTO id) {
        return ResponseEntity.ok(serieService.deactivateSer(id));
    }
}
