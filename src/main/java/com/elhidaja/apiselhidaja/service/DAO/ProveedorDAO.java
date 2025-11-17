package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.proveedor.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.proveedor.Response.*;

public interface ProveedorDAO {
    ResponseProveedorAllDTO getAllD(RequestProveedorOptionDTO option);

    ResponseDetalleProveedorDTO getByIdD(RequestProveedorFilterDTO id);

    ResponseProveedorMensajeDTO desactivateD(RequestProveedorIdDTO id);

    ResponseProveedorMensajeDTO activateD(RequestProveedorIdDTO id);

    ResponseProveedorMensajeDTO insertD(RequestProveedorInsertDTO objProveedor);

    ResponseProveedorMensajeDTO updateD(RequestProveedorUpdateDTO objProveedor);
}
