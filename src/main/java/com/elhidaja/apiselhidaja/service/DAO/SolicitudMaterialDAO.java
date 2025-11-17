package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.solicitudMaterial.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.solicitudMaterial.Response.*;

public interface SolicitudMaterialDAO {

    ResponseSolicitudMaterialAllDTO getAllD(RequestSolicitudMaterialOptionDTO option);

    ResponseDetalleSolicitudMaterialDTO getByIdD(RequestSolicitudMaterialFilterDTO id);

    ResponseSolicitudMaterialMensajeDTO desactivateD(RequestSolicitudMaterialIdDTO id);

    ResponseSolicitudMaterialMensajeDTO activateD(RequestSolicitudMaterialIdDTO id);

    ResponseSolicitudMaterialMensajeDTO insertD(RequestSolicitudMaterialInsertDTO objSolicitud);
}
