package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.inventario.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.inventario.Response.*;

public interface InventarioDAO {
    ResponseInventarioAllDTO getAllD(RequestInventarioOptionDTO option);

    ResponseDetalleInventarioDTO getByIdD(RequestInventarioFilterDTO id);

    ResponseInventarioMensajeDTO desactivateD(RequestInventarioIdDTO id);

    ResponseInventarioMensajeDTO activateD(RequestInventarioIdDTO id);

    ResponseInventarioMensajeDTO insertD(RequestInventarioInsertDTO objInventario);

    ResponseInventarioMensajeDTO updateD(RequestInventarioUpdateDTO objInventario);
}
