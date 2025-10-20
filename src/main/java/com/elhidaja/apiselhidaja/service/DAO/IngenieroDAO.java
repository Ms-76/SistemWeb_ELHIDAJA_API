package com.elhidaja.apiselhidaja.service.DAO;
import com.elhidaja.apiselhidaja.presentation.dto.ingeniero.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.ingeniero.Request.*;
public interface IngenieroDAO {
    public ResponseIngenieroAllDTO getAllD(RequestIngenieroOptionDTO option);

    public ResponseDetalleIngenieroDTO getByIdD(RequestIngenieroFilterDTO id);

    public ResponseIngenieroMensajeDTO desactivateD(RequestIngenieroIdDTO id);

    public ResponseIngenieroMensajeDTO activateD(RequestIngenieroIdDTO id);

    public ResponseIngenieroMensajeDTO insertD(RequestIngenieroInsertDTO objIngeniero);

    public ResponseIngenieroMensajeDTO updateD(RequestIngenieroUpdateDTO objIngeniero);
}
