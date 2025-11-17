package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.detalleInventario.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.detalleInventario.Response.*;

public interface DetalleInventarioDAO {

    ResponseDetalleInventarioAllDTO getAllD(RequestDetalleInventarioOptionDTO option);

    ResponseDetalleInventarioDTO getByIdD(RequestDetalleInventarioFilterDTO id);

    ResponseDetalleInventarioMensajeDTO desactivateD(RequestDetalleInventarioIdDTO id);

    ResponseDetalleInventarioMensajeDTO activateD(RequestDetalleInventarioIdDTO id);

    ResponseDetalleInventarioMensajeDTO updateD(RequestDetalleInventarioUpdateDTO objDetalleInventario);

}
