package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.documentoOperacion.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.documentoOperacion.Response.*;

public interface DocumentoOperacionDAO {
    ResponseDocumentoOperacionAllDTO getAllD(RequestDocumentoOperacionOptionDTO option);

    ResponseDetalleDocumentoOperacionDTO getByIdD(RequestDocumentoOperacionFilterDTO id);

    ResponseDocumentoOperacionMensajeDTO desactivateD(RequestDocumentoOperacionIdDTO id);

    ResponseDocumentoOperacionMensajeDTO activateD(RequestDocumentoOperacionIdDTO id);

    ResponseDocumentoOperacionMensajeDTO insertD(RequestDocumentoOperacionInsertDTO objDocumentoOperacion);

    ResponseDocumentoOperacionMensajeDTO updateD(RequestDocumentoOperacionUpdateDTO objDocumentoOperacion);
}
