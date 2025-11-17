package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.chofer.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.chofer.Response.*;

public interface ChoferDAO {

    ResponseChoferAllDTO getAllD(RequestChoferOptionDTO option);

    ResponseDetalleChoferDTO getByIdD(RequestChoferFilterDTO id);

    ResponseChoferMensajeDTO desactivateD(RequestChoferIdDTO id);

    ResponseChoferMensajeDTO activateD(RequestChoferIdDTO id);

    ResponseChoferMensajeDTO insertD(RequestChoferInsertDTO objChofer);

    ResponseChoferMensajeDTO updateD(RequestChoferUpdateDTO objChofer);
}