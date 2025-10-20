package com.elhidaja.apiselhidaja.service.DAO;
import com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaEntrada.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaEntrada.Response.*;
public interface DetalleGuiaEntradaDAO {
    public ResponseDetalleGuiaEntradaAllDTO getAllD(RequestDetalleGuiaEntradaOptionDTO option);

    public ResponseDetalleGuiaEntradaDTO getByIdD(RequestDetalleGuiaEntradaFilterDTO id);

    public ResponseDetalleGuiaEntradaMensajeDTO desactivateD(RequestDetalleGuiaEntradaIdDTO id);

    public ResponseDetalleGuiaEntradaMensajeDTO activateD(RequestDetalleGuiaEntradaIdDTO id);

    public ResponseDetalleGuiaEntradaMensajeDTO insertD(RequestDetalleGuiaEntradaInsertDTO objDetalleGuiaEntrada);

    public ResponseDetalleGuiaEntradaMensajeDTO updateD(RequestDetalleGuiaEntradaUpdateDTO objDetalleGuiaEntrada);
}
