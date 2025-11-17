package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.transporteDetalle.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.transporteDetalle.Response.*;

public interface TransporteDetalleDAO {

    ResponseTransporteDetalleAllDTO getAllD(RequestTransporteDetalleOptionDTO option);

    ResponseDetalleTransporteDetalleDTO getByIdD(RequestTransporteDetalleFilterDTO id);

    ResponseTransporteDetalleMensajeDTO desactivateD(RequestTransporteDetalleIdDTO id);

    ResponseTransporteDetalleMensajeDTO activateD(RequestTransporteDetalleIdDTO id);

}
