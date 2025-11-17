package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.tipoDocumento.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.tipoDocumento.Response.*;

public interface TipoDocumentoDAO {
    ResponseTipoDocumentoAllDTO getAllD(RequestTipoDocumentoOptionDTO option);

    ResponseDetalleTipoDocumentoDTO getByIdD(RequestTipoDocumentoFilterDTO id);

    ResponseTipoDocumentoMensajeDTO desactivateD(RequestTipoDocumentoIdDTO id);

    ResponseTipoDocumentoMensajeDTO activateD(RequestTipoDocumentoIdDTO id);

    ResponseTipoDocumentoMensajeDTO insertD(RequestTipoDocumentoInsertDTO objTipoDocumento);

    ResponseTipoDocumentoMensajeDTO updateD(RequestTipoDocumentoUpdateDTO objTipoDocumento);
}
