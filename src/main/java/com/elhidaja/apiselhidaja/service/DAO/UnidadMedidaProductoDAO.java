package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.unidadMedidaProducto.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.unidadMedidaProducto.Response.*;

public interface UnidadMedidaProductoDAO {

    ResponseProductoUnidadMedidaAllDTO getAllD(RequestProductoUnidadMedidaOptionDTO option);

    ResponseDetalleProductoUnidadMedidaDTO getByIdD(RequestUnidadMedidaProductoFilterDTO id);

    ResponserProductoUnidadMedidaMensajeDTO desactivateD(RequestProductoUnidadMedidaIdDTO id);

    ResponserProductoUnidadMedidaMensajeDTO activateD(RequestProductoUnidadMedidaIdDTO id);

    ResponserProductoUnidadMedidaMensajeDTO insertD(RequestProductoUnidadMedidaInsertDTO objProductoUnidadMedida);

    ResponserProductoUnidadMedidaMensajeDTO updateD(RequestProductoUnidadMedidaUpdateDTO objProductoUnidadMedida);
}
