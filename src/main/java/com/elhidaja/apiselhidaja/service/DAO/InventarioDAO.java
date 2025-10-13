package com.elhidaja.apiselhidaja.service.DAO;
import com.elhidaja.apiselhidaja.presentation.dto.inventario.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.inventario.Response.*;
public interface InventarioDAO {
    public ResponseInventarioAllDTO getAllD(RequestInventarioOptionDTO option);

    public ResponseDetalleInventarioDTO getByIdD(RequestInventarioIdDTO id);

    public ResponseInventarioMensajeDTO desactivateD(RequestInventarioIdDTO id);

    public ResponseInventarioMensajeDTO activateD(RequestInventarioIdDTO id);

    public ResponseInventarioMensajeDTO insertD(RequestInventarioInsertDTO objInventario);

    public ResponseInventarioMensajeDTO updateD(RequestInventarioUpdateDTO objInventario);
}
