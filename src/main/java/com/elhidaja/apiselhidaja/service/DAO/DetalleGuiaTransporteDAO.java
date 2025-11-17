package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaTransporte.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaTransporte.Request.*;

public interface DetalleGuiaTransporteDAO {

    ResponseDetalleGuiaTransporteAllDTO getAllD(RequestDetalleGuiaTransporteOptionDTO option);

    ResponseDetalleGuiaTransporteDTO getByIdD(RequestDetalleGuiaTransporteFilterDTO id);

    ResponseDetalleGuiaTransporteMensajeDTO desactivateD(RequestDetalleGuiaTransporteIdDTO id);

    ResponseDetalleGuiaTransporteMensajeDTO activateD(RequestDetalleGuiaTransporteIdDTO id);

}
