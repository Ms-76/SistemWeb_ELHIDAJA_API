package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elhidaja.apiselhidaja.persistence.repository.LoginLogsRepository;
import com.elhidaja.apiselhidaja.presentation.dto.loginLogs.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.loginLogs.Response.*;

@Service
public class LoginLogsService {
       private final LoginLogsRepository loginLogsRepo;

    public LoginLogsService(LoginLogsRepository loginLogsRepo) {
        this.loginLogsRepo = loginLogsRepo;
    }

    @Transactional
    public ResponseLoginLogMessageDTO insertSer(RequestLoginLogInsertDTO loginLog) {
        return loginLogsRepo.insert(loginLog);
    }

    @Transactional(readOnly = true)
    public ResponseLoginLogsAllDTO getAllSer(RequestLoginLogsOptionDTO option) {
        return loginLogsRepo.getAll(option);
    }

    @Transactional(readOnly = true)
    public ResponseLoginLogByIdDTO getByIdSer(RequestLoginLogIdDTO id) {
        return loginLogsRepo.getById(id);
    }
}
