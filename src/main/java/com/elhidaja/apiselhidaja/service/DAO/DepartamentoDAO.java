package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.departamento.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.departamento.Request.*;

public interface DepartamentoDAO {
    ResponseDepartamentoAllDTO getAll(RequestDepartamentoOptionDTO option);

    ResponseDetalleDepartamentoDTO getById(RequestDepartamentoFilterDTO id);

    ResponseDepartamentoMensajeDTO desactivate(RequestDepartamentoIdDTO id);

    ResponseDepartamentoMensajeDTO activate(RequestDepartamentoIdDTO id);

    ResponseDepartamentoMensajeDTO insert(RequestDepartamentoInsertDTO objDepartamento);

    ResponseDepartamentoMensajeDTO update(RequestDepartamentoUpdateDTO objDepartamento);
}
