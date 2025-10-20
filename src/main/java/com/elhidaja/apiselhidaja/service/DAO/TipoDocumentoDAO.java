package com.elhidaja.apiselhidaja.service.DAO;
import com.elhidaja.apiselhidaja.presentation.dto.tipoDocumento.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.tipoDocumento.Response.*;

public interface TipoDocumentoDAO {
    public ResponseTipoDocumentoAllDTO getAllD(RequestTipoDocumentoOptionDTO option);

    public ResponseDetalleTipoDocumentoDTO getByIdD(RequestTipoDocumentoFilterDTO id);

    public ResponseTipoDocumentoMensajeDTO desactivateD(RequestTipoDocumentoIdDTO id);

    public ResponseTipoDocumentoMensajeDTO activateD(RequestTipoDocumentoIdDTO id);

    public ResponseTipoDocumentoMensajeDTO insertD(RequestTipoDocumentoInsertDTO objTipoDocumento);

    public ResponseTipoDocumentoMensajeDTO updateD(RequestTipoDocumentoUpdateDTO objTipoDocumento);
}
