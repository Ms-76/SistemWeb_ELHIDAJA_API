package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.serieDocumento.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.serieDocumento.Response.*;

public interface SerieDocumentoDAO {
    ResponseSerieDocumentoAllDTO getAllD(RequestSerieDocumentoOptionDTO option);

    ResponseDetalleSerieDocumentoDTO getByIdD(RequestSerieDocumentoFilterDTO id);

    ResponseSerieDocumentoMensajeDTO desactivateD(RequestSerieDocumentoIdDTO id);

    ResponseSerieDocumentoMensajeDTO activateD(RequestSerieDocumentoIdDTO id);

    ResponseSerieDocumentoMensajeDTO insertD(RequestSerieDocumentoInsertDTO objSerieDocumento);

    ResponseSerieDocumentoMensajeDTO updateD(RequestSerieDocumentoUpdateDTO objSerieDocumento);
}
