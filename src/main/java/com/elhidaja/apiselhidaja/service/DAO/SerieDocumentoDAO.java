package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.serieDocumento.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.serieDocumento.Response.*;

public interface SerieDocumentoDAO {
    public ResponseSerieDocumentoAllDTO getAllD(RequestSerieDocumentoOptionDTO option);

    public ResponseDetalleSerieDocumentoDTO getByIdD(RequestSerieDocumentoIdDTO id);

    public ResponseSerieDocumentoMensajeDTO desactivateD(RequestSerieDocumentoIdDTO id);

    public ResponseSerieDocumentoMensajeDTO activateD(RequestSerieDocumentoIdDTO id);

    public ResponseSerieDocumentoMensajeDTO insertD(RequestSerieDocumentoInsertDTO objSerieDocumento);

    public ResponseSerieDocumentoMensajeDTO updateD(RequestSerieDocumentoUpdateDTO objSerieDocumento);
}
