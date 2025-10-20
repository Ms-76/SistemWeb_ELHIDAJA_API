package com.elhidaja.apiselhidaja.service.DAO;
import com.elhidaja.apiselhidaja.presentation.dto.guiaEntrada.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.guiaEntrada.Response.*;
public interface GuiaEntradaDAO {

    public ResponseGuiaEntradaAllDTO getAllD(RequestGuiaEntradaOptionDTO option);

    public ResponseDetalleGuiaEntradaDTO getByIdD(RequestGuiaEntradaFilterDTO id);

    public ResponseGuiaEntradaMensajeDTO desactivateD(RequestGuiaEntradaIdDTO id);

    public ResponseGuiaEntradaMensajeDTO activateD(RequestGuiaEntradaIdDTO id);

    public ResponseGuiaEntradaMensajeDTO insertD(RequestGuiaEntradaInsertDTO objGuiaEntrada);

    public ResponseGuiaEntradaMensajeDTO updateD(RequestGuiaEntradaUpdateDTO objGuiaEntrada);
}
