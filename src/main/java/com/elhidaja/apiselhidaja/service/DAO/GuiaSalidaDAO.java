package com.elhidaja.apiselhidaja.service.DAO;
import com.elhidaja.apiselhidaja.presentation.dto.guiaSalida.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.guiaSalida.Response.*;

public interface GuiaSalidaDAO {
    ResponseGuiaSalidaAllDTO getAllD(RequestGuiaSalidaOptionDTO option);
    ResponseDetalleGuiaSalidaDTO getByIdD(RequestGuiaSalidaFilterDTO id);
    ResponseGuiaSalidaMensajeDTO desactivateD(RequestGuiaSalidaIdDTO id);
    ResponseGuiaSalidaMensajeDTO activateD(RequestGuiaSalidaIdDTO id);
    ResponseGuiaSalidaMensajeDTO insertD(RequestGuiaSalidaInsertDTO objGuiaSalida);
    ResponseGuiaSalidaMensajeDTO updateD(RequestGuiaSalidaUpdateDTO objGuiaSalida);
}
