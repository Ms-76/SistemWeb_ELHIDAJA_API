package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.distrito.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.distrito.Request.*;

public interface DistritoDAO {
    ResponseDistritoAllDTO getAllD(RequestDistritoOptionDTO option);

    ResponseDetalleDistritoDTO getByIdD(RequestDistritoFilterDTO id);

    ResponseDistritoMensajeDTO desactivateD(RequestDistritoIdDTO id);

    ResponseDistritoMensajeDTO activateD(RequestDistritoIdDTO id);

    ResponseDistritoMensajeDTO insertD(RequestDistritoInsertDTO objDistrito);

    ResponseDistritoMensajeDTO updateD(RequestDistritoUpdateDTO objDistrito);
}
