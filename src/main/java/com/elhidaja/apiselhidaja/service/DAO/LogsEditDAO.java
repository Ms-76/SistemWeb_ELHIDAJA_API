package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.LogsEdit.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.LogsEdit.Response.*;

public interface LogsEditDAO {

    ResponseLogsAllDTO getAllD(RequestLogsOptionDTO option);

    ResponseDetalleLogDTO getByIdD(RequestLogIdDTO id);

}
