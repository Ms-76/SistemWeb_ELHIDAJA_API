package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.transporte.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.transporte.Response.*;

public interface TransporteDAO {

    ResponseTransporteAllDTO getAllD(RequestTransporteOptionDTO option);

    ResponseDetalleTransporteDTO getByIdD(RequestTransporteFilterDTO id);

    ResponseTransporteMensajeDTO desactivateD(RequestTransporteIdDTO id);

    ResponseTransporteMensajeDTO activateD(RequestTransporteIdDTO id);

    ResponseTransporteMensajeDTO insertD(RequestTransporteInsertDTO objTransporte);

    // public ResponseTransporteMensajeDTO updateD(RequestTransporteUpdateDTO
    // objTransporte);
}
