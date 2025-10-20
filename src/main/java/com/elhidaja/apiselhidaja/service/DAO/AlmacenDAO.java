package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.almacen.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.almacen.Request.*;

public interface AlmacenDAO {
    public ResponseAlmacenAllDTO getAllD(RequestAlmacenOptionDTO option);

    public ResponseDetalleAlmacenDTO getByIdD(RequestAlmacenFilterDTO id);

    public ResponseAlmacenMensajeDTO desactivateD(RequestAlmacenIdDTO id);

    public ResponseAlmacenMensajeDTO activateD(RequestAlmacenIdDTO id);

    public ResponseAlmacenMensajeDTO insertD(RequestAlmacenInsertDTO objAlmacen);

    public ResponseAlmacenMensajeDTO updateD(RequestAlmacenUpdateDTO objAlmacen);
}
