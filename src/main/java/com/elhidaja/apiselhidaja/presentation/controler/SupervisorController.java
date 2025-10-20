package com.elhidaja.apiselhidaja.presentation.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import com.elhidaja.apiselhidaja.presentation.dto.supervisor.Request.RequestSupervisorFilterDTO;
import com.elhidaja.apiselhidaja.presentation.dto.supervisor.Request.RequestSupervisorIdDTO;
import com.elhidaja.apiselhidaja.presentation.dto.supervisor.Request.RequestSupervisorInsertDTO;
import com.elhidaja.apiselhidaja.presentation.dto.supervisor.Request.RequestSupervisorOptionDTO;
import com.elhidaja.apiselhidaja.presentation.dto.supervisor.Response.ResponseDetalleSupervisorDTO;
import com.elhidaja.apiselhidaja.presentation.dto.supervisor.Response.ResponseSupervisorAllDTO;
import com.elhidaja.apiselhidaja.presentation.dto.supervisor.Response.ResponseSupervisorMensajeDTO;
import com.elhidaja.apiselhidaja.service.implementation.SupervisorService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/supervisores")
@Validated
public class SupervisorController {
        @Autowired
    private SupervisorService supervisorService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseSupervisorAllDTO> getSupervisores(
            @Valid @RequestBody RequestSupervisorOptionDTO option) {
        return ResponseEntity.ok(supervisorService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleSupervisorDTO> getByIdSupervisor(
            @Valid @RequestBody RequestSupervisorFilterDTO id) {
        return ResponseEntity.ok(supervisorService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseSupervisorMensajeDTO> insertarSupervisor(
            @Valid @RequestBody RequestSupervisorInsertDTO dto) {
        return ResponseEntity.ok(supervisorService.insertSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponseSupervisorMensajeDTO> activarSupervisor(
            @Valid @RequestBody RequestSupervisorIdDTO id) {
        return ResponseEntity.ok(supervisorService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponseSupervisorMensajeDTO> desactivarSupervisor(
            @Valid @RequestBody RequestSupervisorIdDTO id) {
        return ResponseEntity.ok(supervisorService.desactivateSer(id));
    }
}
