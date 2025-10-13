package com.elhidaja.apiselhidaja.service.DAO;
import com.elhidaja.apiselhidaja.presentation.dto.documentoOperacion.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.documentoOperacion.Response.*;
public interface DocumentoOperacionDAO {
    public ResponseDocumentoOperacionAllDTO getAllD(RequestDocumentoOperacionOptionDTO option);

    public ResponseDetalleDocumentoOperacionDTO getByIdD(RequestDocumentoOperacionIdDTO id);

    public ResponseDocumentoOperacionMensajeDTO desactivateD(RequestDocumentoOperacionIdDTO id);

    public ResponseDocumentoOperacionMensajeDTO activateD(RequestDocumentoOperacionIdDTO id);

    public ResponseDocumentoOperacionMensajeDTO insertD(RequestDocumentoOperacionInsertDTO objDocumentoOperacion);

    public ResponseDocumentoOperacionMensajeDTO updateD(RequestDocumentoOperacionUpdateDTO objDocumentoOperacion);
}
