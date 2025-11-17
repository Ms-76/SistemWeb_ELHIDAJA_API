package com.elhidaja.apiselhidaja.presentation.controler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.elhidaja.apiselhidaja.presentation.dto.tipoVehiculo.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.tipoVehiculo.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.TipoVehiculoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tipovehiculos")
@Validated
public class TipoVehiculoController {
      @Autowired
    private TipoVehiculoService tipoVehiculoService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseTipoVehiculoAllDTO> getTipoVehiculos(
            @Valid @RequestBody RequestTipoVehiculoOptionDTO option) {
        return ResponseEntity.ok(tipoVehiculoService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleTipoVehiculoDTO> getByIdTipoVehiculo(
            @Valid @RequestBody RequestTipoVehiculoFilterDTO id) {
        return ResponseEntity.ok(tipoVehiculoService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseTipoVehiculoMensajeDTO> insertarTipoVehiculo(
            @Valid @RequestBody RequestTipoVehiculoInsertDTO dto) {
        return ResponseEntity.ok(tipoVehiculoService.insertSer(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseTipoVehiculoMensajeDTO> actualizarTipoVehiculo(
            @Valid @RequestBody RequestTipoVehiculoUpdateDTO dto) {
        return ResponseEntity.ok(tipoVehiculoService.updateSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponseTipoVehiculoMensajeDTO> activarTipoVehiculo(
            @Valid @RequestBody RequestTipoVehiculoIdDTO id) {
        return ResponseEntity.ok(tipoVehiculoService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponseTipoVehiculoMensajeDTO> desactivarTipoVehiculo(
            @Valid @RequestBody RequestTipoVehiculoIdDTO id) {
        return ResponseEntity.ok(tipoVehiculoService.desactivateSer(id));
    }
}
