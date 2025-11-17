package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.almacen.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.almacen.Request.*;

public interface AlmacenDAO {
    ResponseAlmacenAllDTO getAllD(RequestAlmacenOptionDTO option);

    ResponseDetalleAlmacenDTO getByIdD(RequestAlmacenFilterDTO id);

    ResponseAlmacenMensajeDTO desactivateD(RequestAlmacenIdDTO id);

    ResponseAlmacenMensajeDTO activateD(RequestAlmacenIdDTO id);

    ResponseAlmacenMensajeDTO insertD(RequestAlmacenInsertDTO objAlmacen);

    ResponseAlmacenMensajeDTO updateD(RequestAlmacenUpdateDTO objAlmacen);
}
