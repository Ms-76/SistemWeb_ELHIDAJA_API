package com.elhidaja.apiselhidaja.service.DAO;
import com.elhidaja.apiselhidaja.presentation.dto.tipoOperacion.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.tipoOperacion.Response.*;
public interface TipoOperacionDAO {
    
    public ResponseTipoOperacionAllDTO getAllD(RequestTipoOperacionOptionDTO option);

    public ResponseDetalleTipoOperacionDTO getByIdD(RequestTipoOperacionIdDTO id);

    public ResponseTipoOperacionMensajeDTO desactivateD(RequestTipoOperacionIdDTO id);

    public ResponseTipoOperacionMensajeDTO activateD(RequestTipoOperacionIdDTO id);

    public ResponseTipoOperacionMensajeDTO insertD(RequestTipoOperacionInsertDTO objTipoOperacion);

    public ResponseTipoOperacionMensajeDTO updateD(RequestTipoOperacionUpdateDTO objTipoOperacion);
}
