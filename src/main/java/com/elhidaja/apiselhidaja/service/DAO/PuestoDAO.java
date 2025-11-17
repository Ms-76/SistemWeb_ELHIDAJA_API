package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.puesto.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.puesto.Response.*;

public interface PuestoDAO {
    ResponsePuestoAllDTO getAllD(RequestPuestoOptionDTO option);

    ResponseDetallePuestoDTO getByIdD(RequestPuestoFilterDTO id);

    ResponsePuestoMensajeDTO desactivateD(RequestPuestoIdDTO id);

    ResponsePuestoMensajeDTO activateD(RequestPuestoIdDTO id);

    ResponsePuestoMensajeDTO insertD(RequestPuestoInsertDTO objPuesto);

    ResponsePuestoMensajeDTO updateD(RequestPuestoUpdateDTO objPuesto);
}
