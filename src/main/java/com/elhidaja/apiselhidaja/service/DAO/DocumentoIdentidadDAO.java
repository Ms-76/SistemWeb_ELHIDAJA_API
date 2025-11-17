package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.documentoIdentidad.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.documentoIdentidad.Request.*;

public interface DocumentoIdentidadDAO {

    ResponseDocumentoIdentidadAllDTO getAllD(RequestDocumentoIdentidadOptionDTO option);

    ResponseDetalleDocumentoIdentidadDTO getByIdD(RequestDocumentoIdentidadFilterDTO id);

    ResponseDocumentoIdentidadMensajeDTO desactivateD(RequestDocumentoIdentidadIdDTO id);

    ResponseDocumentoIdentidadMensajeDTO activateD(RequestDocumentoIdentidadIdDTO id);

    ResponseDocumentoIdentidadMensajeDTO insertD(RequestDocumentoIdentidadInsertDTO objDocumentoIdentidad);

    ResponseDocumentoIdentidadMensajeDTO updateD(RequestDocumentoIdentidadUpdateDTO objDocumentoIdentidad);
}
