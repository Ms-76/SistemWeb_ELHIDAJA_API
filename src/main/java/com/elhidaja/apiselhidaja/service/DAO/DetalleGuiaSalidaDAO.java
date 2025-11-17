package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaSalida.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaSalida.Response.*;

public interface DetalleGuiaSalidaDAO {
    ResponseDetalleGuiaSalidaAllDTO getAllD(RequestDetalleGuiaSalidaOptionDTO option);

    ResponseDetalleGuiaSalidaDTO getByIdD(RequestDetalleGuiaSalidaFilterDTO id);

    ResponseDetalleGuiaSalidaMensajeDTO desactivateD(RequestDetalleGuiaSalidaIdDTO id);

    ResponseDetalleGuiaSalidaMensajeDTO activateD(RequestDetalleGuiaSalidaIdDTO id);
}
