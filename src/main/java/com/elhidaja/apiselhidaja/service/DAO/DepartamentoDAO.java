package com.elhidaja.apiselhidaja.service.DAO;
import com.elhidaja.apiselhidaja.presentation.dto.departamento.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.departamento.Request.*;
public interface  DepartamentoDAO {
    public ResponseDepartamentoAllDTO getAll(RequestDepartamentoOptionDTO option);

    public ResponseDetalleDepartamentoDTO getById(RequestDepartamentoFilterDTO id);

    public ResponseDepartamentoMensajeDTO desactivate(RequestDepartamentoIdDTO id);

    public ResponseDepartamentoMensajeDTO activate(RequestDepartamentoIdDTO id);

    public ResponseDepartamentoMensajeDTO insert(RequestDepartamentoInsertDTO objDepartamento);

    public ResponseDepartamentoMensajeDTO update(RequestDepartamentoUpdateDTO objDepartamento);
}
