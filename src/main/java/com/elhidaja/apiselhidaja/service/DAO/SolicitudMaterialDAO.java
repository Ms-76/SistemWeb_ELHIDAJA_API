package com.elhidaja.apiselhidaja.service.DAO;
import com.elhidaja.apiselhidaja.presentation.dto.solicitudMaterial.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.solicitudMaterial.Response.*;
public interface SolicitudMaterialDAO {
    
    public ResponseSolicitudMaterialAllDTO getAllD(RequestSolicitudMaterialOptionDTO option);

    public ResponseDetalleSolicitudMaterialDTO getByIdD(RequestSolicitudMaterialFilterDTO id);

    public ResponseSolicitudMaterialMensajeDTO desactivateD(RequestSolicitudMaterialIdDTO id);

    public ResponseSolicitudMaterialMensajeDTO activateD(RequestSolicitudMaterialIdDTO id);

    public ResponseSolicitudMaterialMensajeDTO insertD(RequestSolicitudMaterialInsertDTO objSolicitud);
}
