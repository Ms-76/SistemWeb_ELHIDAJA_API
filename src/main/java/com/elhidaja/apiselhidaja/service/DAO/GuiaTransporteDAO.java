package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.guiaTransporte.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.guiaTransporte.Response.*;

public interface GuiaTransporteDAO {

    ResponseGuiaTransporteAllDTO getAllD(RequestGuiaTransporteOptionDTO option);

    ResponseDetalleGuiaTransporteDTO getByIdD(RequestGuiaTransporteFilterDTO id);

    ResponseGuiaTransporteMensajeDTO desactivateD(RequestGuiaTransporteIdDTO id);

    ResponseGuiaTransporteMensajeDTO activateD(RequestGuiaTransporteIdDTO id);

    ResponseGuiaTransporteMensajeDTO insertD(RequestGuiaTransporteInsertDTO objGuiaTransporte);

}
