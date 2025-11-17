package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.area.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.area.Request.*;

public interface AreaDAO {

    ResponseAreaAllDTO getAllD(RequestAreaOptionDTO option);

    ResponseDetalleAreaDTO getByIdD(RequestAreaFilterDTO id);

    ResponseAreaMensajeDTO desactivateD(RequestAreaIdDTO id);

    ResponseAreaMensajeDTO activateD(RequestAreaIdDTO id);

    ResponseAreaMensajeDTO insertD(RequestAreaInsertDTO objArea);

    ResponseAreaMensajeDTO updateD(RequestAreaUpdateDTO objArea);
}
