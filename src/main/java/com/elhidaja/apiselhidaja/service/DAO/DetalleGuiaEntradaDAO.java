package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaEntrada.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaEntrada.Response.*;

public interface DetalleGuiaEntradaDAO {
    ResponseDetalleGuiaEntradaAllDTO getAllD(RequestDetalleGuiaEntradaOptionDTO option);

    ResponseDetalleGuiaEntradaDTO getByIdD(RequestDetalleGuiaEntradaFilterDTO id);

    ResponseDetalleGuiaEntradaMensajeDTO desactivateD(RequestDetalleGuiaEntradaIdDTO id);

    ResponseDetalleGuiaEntradaMensajeDTO activateD(RequestDetalleGuiaEntradaIdDTO id);

    ResponseDetalleGuiaEntradaMensajeDTO updateObservacionD(
            RequestActualizarObservacionDetalleGuiaEntradaDTO objDetalleGuiaEntrada);
}
