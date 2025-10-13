package com.elhidaja.apiselhidaja.service.DAO;
import com.elhidaja.apiselhidaja.presentation.dto.loginLogs.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.loginLogs.Response.*;
public interface LoginLogsDAO {
    public ResponseLoginLogsAllDTO getAll(RequestLoginLogsOptionDTO option);

    public ResponseLoginLogByIdDTO getById(RequestLoginLogIdDTO id);

    public ResponseLoginLogMessageDTO insert(RequestLoginLogInsertDTO loginLog);
}
