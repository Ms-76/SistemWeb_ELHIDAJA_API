package com.elhidaja.apiselhidaja.service.DAO;
import com.elhidaja.apiselhidaja.presentation.dto.proveedor.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.proveedor.Response.*;
public interface ProveedorDAO {
    public ResponseProveedorAllDTO getAllD(RequestProveedorOptionDTO option);

    public ResponseDetalleProveedorDTO getByIdD(RequestProveedorFilterDTO id);

    public ResponseProveedorMensajeDTO desactivateD(RequestProveedorIdDTO id);

    public ResponseProveedorMensajeDTO activateD(RequestProveedorIdDTO id);

    public ResponseProveedorMensajeDTO insertD(RequestProveedorInsertDTO objProveedor);

    public ResponseProveedorMensajeDTO updateD(RequestProveedorUpdateDTO objProveedor);
}
