package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.provincia.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.provincia.Request.*;

public interface ProvinciaDAO {
    ResponseProvinciaAllDTO getAllD(RequestProvinciaOptionDTO option);

    ResponseDetalleProvinciaDTO getByIdD(RequestProvinciaFilterDTO id);

    ResponseProvinciaMensajeDTO desactivateD(RequestProvinciaIdDTO id);

    ResponseProvinciaMensajeDTO activateD(RequestProvinciaIdDTO id);

    ResponseProvinciaMensajeDTO insertD(RequestProvinciaInsertDTO objProvincia);

    ResponseProvinciaMensajeDTO updateD(RequestProvinciaUpdateDTO objProvincia);
}
