package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.elhidaja.apiselhidaja.persistence.repository.LogsEditRepository;
import com.elhidaja.apiselhidaja.presentation.dto.LogsEdit.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.LogsEdit.Response.*;

@Service
@Validated
public class LogsEditService {
    
    private final LogsEditRepository logsRepo;

    public LogsEditService(LogsEditRepository logsRepo) {
        this.logsRepo = logsRepo;
    }

    @Transactional
    public ResponseLogsAllDTO getAllSer(RequestLogsOptionDTO option) {
        return logsRepo.getAllD(option);
    }

    @Transactional
    public ResponseDetalleLogDTO getByIdSer(RequestLogIdDTO id) {
        return logsRepo.getByIdD(id);
    }
}
