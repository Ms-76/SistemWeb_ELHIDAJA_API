package com.elhidaja.apiselhidaja.service.DAO;
import com.elhidaja.apiselhidaja.presentation.dto.detalleSolicitudMaterial.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.detalleSolicitudMaterial.Response.*;
public interface DetalleSolicitudMaterialDAO {
    public ResponseDetalleSolicitudMaterialAllDTO getAllD(RequestDetalleSolicitudMaterialOptionDTO option);

    public ResponseDetalleSolicitudMaterialDTO getByIdD(RequestDetalleSolicitudMaterialFilterDTO id);

    public ResponserDetalleSolicitudMaterialMensajeDTO desactivateD(RequestDetalleSolicitudMaterialIdDTO id);

    public ResponserDetalleSolicitudMaterialMensajeDTO activateD(RequestDetalleSolicitudMaterialIdDTO id);
}
