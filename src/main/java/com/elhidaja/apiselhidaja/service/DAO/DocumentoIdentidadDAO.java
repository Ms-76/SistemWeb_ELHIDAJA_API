package com.elhidaja.apiselhidaja.service.DAO;
import com.elhidaja.apiselhidaja.presentation.dto.documentoIdentidad.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.documentoIdentidad.Request.*;

public interface DocumentoIdentidadDAO {

    public ResponseDocumentoIdentidadAllDTO getAllD(RequestDocumentoIdentidadOptionDTO option);

    public ResponseDetalleDocumentoIdentidadDTO getByIdD(RequestDocumentoIdentidadIdDTO id);

    public ResponseDocumentoIdentidadMensajeDTO desactivateD(RequestDocumentoIdentidadIdDTO id);

    public ResponseDocumentoIdentidadMensajeDTO activateD(RequestDocumentoIdentidadIdDTO id);

    public ResponseDocumentoIdentidadMensajeDTO insertD(RequestDocumentoIdentidadInsertDTO objDocumentoIdentidad);

    public ResponseDocumentoIdentidadMensajeDTO updateD(RequestDocumentoIdentidadUpdateDTO objDocumentoIdentidad);
}
