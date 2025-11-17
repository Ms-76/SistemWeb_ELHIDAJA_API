package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.oficio.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.oficio.Response.*;

public interface OficioDAO {
    ResponseOficioAllDTO getAllD(RequestOficioOptionDTO option);

    ResponseDetalleOficioDTO getByIdD(RequestOficioFilterDTO id);

    ResponseOficioMensajeDTO deactivateD(RequestOficioIdDTO id);

    ResponseOficioMensajeDTO activateD(RequestOficioIdDTO id);

    ResponseOficioMensajeDTO insertD(RequestOficioInsertDTO objOficio);

    ResponseOficioMensajeDTO updateD(RequestOficioUpdateDTO objOficio);

}
