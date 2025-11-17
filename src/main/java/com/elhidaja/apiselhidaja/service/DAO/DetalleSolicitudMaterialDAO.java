package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.detalleSolicitudMaterial.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.detalleSolicitudMaterial.Response.*;

public interface DetalleSolicitudMaterialDAO {
    ResponseDetalleSolicitudMaterialAllDTO getAllD(RequestDetalleSolicitudMaterialOptionDTO option);

    ResponseDetalleSolicitudMaterialDTO getByIdD(RequestDetalleSolicitudMaterialFilterDTO id);

    ResponserDetalleSolicitudMaterialMensajeDTO desactivateD(RequestDetalleSolicitudMaterialIdDTO id);

    ResponserDetalleSolicitudMaterialMensajeDTO activateD(RequestDetalleSolicitudMaterialIdDTO id);
}
