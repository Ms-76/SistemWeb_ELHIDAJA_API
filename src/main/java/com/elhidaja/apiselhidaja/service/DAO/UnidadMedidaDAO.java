package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Resquest.*;

public interface UnidadMedidaDAO {
    ResponseUnidadMedidaAllDTO getAllD(RequestUnidadMedidaOptionDTO option);

    ResponseDetalleUnidadMedidaDTO getByIdD(RequestUnidadMedidaFilterDTO id);

    ResponseUnidadMedidaMensajeDTO desactivateD(RequestUnidadMedidaIdDTO id);

    ResponseUnidadMedidaMensajeDTO activateD(RequestUnidadMedidaIdDTO id);

    ResponseUnidadMedidaMensajeDTO insertD(RequestUnidadMedidaInsertDTO objUnidad);

    ResponseUnidadMedidaMensajeDTO updateD(RequestUnidadMedidaUpdateDTO objUnidad);
}
