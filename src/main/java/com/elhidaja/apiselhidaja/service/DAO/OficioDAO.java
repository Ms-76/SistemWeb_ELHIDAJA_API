package com.elhidaja.apiselhidaja.service.DAO;
import com.elhidaja.apiselhidaja.presentation.dto.oficio.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.oficio.Response.*;

public interface OficioDAO {
    public ResponseOficioAllDTO getAllD(RequestOficioOptionDTO option);

    public ResponseDetalleOficioDTO getByIdD(RequestOficioIdDTO id);

    public ResponseOficioMensajeDTO deactivateD(RequestOficioIdDTO id);

    public ResponseOficioMensajeDTO activateD(RequestOficioIdDTO id);

    public ResponseOficioMensajeDTO insertD(RequestOficioInsertDTO objOficio);

    public ResponseOficioMensajeDTO updateD(RequestOficioUpdateDTO objOficio);

}
