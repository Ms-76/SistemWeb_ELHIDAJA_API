package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.detalleInventario.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.detalleInventario.Response.*;

public interface DetalleInventarioDAO {

    public ResponseDetalleInventarioAllDTO getAllD(RequestDetalleInventarioOptionDTO option);

    public ResponseDetalleInventarioDTO getByIdD(RequestDetalleInventarioIdDTO id);

    public ResponseDetalleInventarioMensajeDTO desactivateD(RequestDetalleInventarioIdDTO id);

    public ResponseDetalleInventarioMensajeDTO activateD(RequestDetalleInventarioIdDTO id);

    public ResponseDetalleInventarioMensajeDTO insertD(RequestDetalleInventarioInsertDTO objDetalleInventario);

    public ResponseDetalleInventarioMensajeDTO updateD(RequestDetalleInventarioUpdateDTO objDetalleInventario);
}
