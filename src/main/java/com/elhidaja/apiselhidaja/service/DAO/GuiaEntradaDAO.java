package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.guiaEntrada.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.guiaEntrada.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.guiaSalida.Request.RequestRecibirDesdeGuiaSalidaDTO;
import com.elhidaja.apiselhidaja.presentation.dto.guiaSalida.Response.ResponseGuiaSalidaMensajeDTO;

public interface GuiaEntradaDAO {

    ResponseGuiaEntradaAllDTO getAllD(RequestGuiaEntradaOptionDTO option);

    ResponseDetalleGuiaEntradaDTO getByIdD(RequestGuiaEntradaFilterDTO id);

    ResponseGuiaEntradaMensajeDTO desactivateD(RequestGuiaEntradaIdDTO id);

    ResponseGuiaEntradaMensajeDTO activateD(RequestGuiaEntradaIdDTO id);

    ResponseGuiaEntradaMensajeDTO insertD(RequestGuiaEntradaInsertDTO objGuiaEntrada);

    ResponseGuiaSalidaMensajeDTO recibirDesdeGuiaSalida(RequestRecibirDesdeGuiaSalidaDTO request);

    ResponseGuiaEntradaMensajeDTO updateD(RequestGuiaEntradaUpdateDTO objGuiaEntrada);
}
