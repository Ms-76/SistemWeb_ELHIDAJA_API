package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.loginLogs.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.loginLogs.Response.*;

public interface LoginLogsDAO {
    ResponseLoginLogsAllDTO getAll(RequestLoginLogsOptionDTO option);

    ResponseLoginLogByIdDTO getById(RequestLoginLogIdDTO id);

    ResponseLoginLogMessageDTO insert(RequestLoginLogInsertDTO loginLog);
}
