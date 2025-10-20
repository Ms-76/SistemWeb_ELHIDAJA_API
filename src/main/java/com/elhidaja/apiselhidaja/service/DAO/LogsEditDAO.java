package com.elhidaja.apiselhidaja.service.DAO;
import com.elhidaja.apiselhidaja.presentation.dto.LogsEdit.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.LogsEdit.Response.*;
public interface LogsEditDAO {

    public ResponseLogsAllDTO getAllD(RequestLogsOptionDTO option);

    public ResponseDetalleLogDTO getByIdD(RequestLogIdDTO id);

}
