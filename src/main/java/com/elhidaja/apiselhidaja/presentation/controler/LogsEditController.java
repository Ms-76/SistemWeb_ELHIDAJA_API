package com.elhidaja.apiselhidaja.presentation.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.elhidaja.apiselhidaja.presentation.dto.LogsEdit.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.LogsEdit.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.LogsEditService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/logs")
@Validated
public class LogsEditController {
        @Autowired
    private LogsEditService logsEditService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseLogsAllDTO> getLogs(
            @Valid @RequestBody RequestLogsOptionDTO option) {
        return ResponseEntity.ok(logsEditService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleLogDTO> getByIdLog(
            @Valid @RequestBody RequestLogIdDTO id) {
        return ResponseEntity.ok(logsEditService.getByIdSer(id));
    }
}
