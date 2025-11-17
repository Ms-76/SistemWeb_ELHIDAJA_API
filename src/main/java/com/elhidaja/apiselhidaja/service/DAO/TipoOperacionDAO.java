package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.tipoOperacion.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.tipoOperacion.Response.*;

public interface TipoOperacionDAO {

    ResponseTipoOperacionAllDTO getAllD(RequestTipoOperacionOptionDTO option);

    ResponseDetalleTipoOperacionDTO getByIdD(RequestTipoOperacionFilterDTO id);

    ResponseTipoOperacionMensajeDTO desactivateD(RequestTipoOperacionIdDTO id);

    ResponseTipoOperacionMensajeDTO activateD(RequestTipoOperacionIdDTO id);

    ResponseTipoOperacionMensajeDTO insertD(RequestTipoOperacionInsertDTO objTipoOperacion);

    ResponseTipoOperacionMensajeDTO updateD(RequestTipoOperacionUpdateDTO objTipoOperacion);
}
