package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.ingeniero.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.ingeniero.Request.*;

public interface IngenieroDAO {
    ResponseIngenieroAllDTO getAllD(RequestIngenieroOptionDTO option);

    ResponseDetalleIngenieroDTO getByIdD(RequestIngenieroFilterDTO id);

    ResponseIngenieroMensajeDTO desactivateD(RequestIngenieroIdDTO id);

    ResponseIngenieroMensajeDTO activateD(RequestIngenieroIdDTO id);

    ResponseIngenieroMensajeDTO insertD(RequestIngenieroInsertDTO objIngeniero);

    ResponseIngenieroMensajeDTO updateD(RequestIngenieroUpdateDTO objIngeniero);
}
