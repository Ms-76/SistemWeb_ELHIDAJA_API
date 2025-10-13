package com.elhidaja.apiselhidaja.presentation.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.elhidaja.apiselhidaja.presentation.dto.loginLogs.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.loginLogs.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.LoginLogsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/login")
@Validated
public class LoginLogsController {

    @Autowired
    private LoginLogsService loginLogsService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseLoginLogsAllDTO> getLoginLogs(
            @Valid @RequestBody RequestLoginLogsOptionDTO option) {
        return ResponseEntity.ok(loginLogsService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseLoginLogByIdDTO> getByIdLoginLog(
            @Valid @RequestBody RequestLoginLogIdDTO id) {
        return ResponseEntity.ok(loginLogsService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseLoginLogMessageDTO> insertarLoginLog(
            @Valid @RequestBody RequestLoginLogInsertDTO dto) {
        return ResponseEntity.ok(loginLogsService.insertSer(dto));
    }
}
