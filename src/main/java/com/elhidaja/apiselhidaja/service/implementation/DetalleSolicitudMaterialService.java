package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.elhidaja.apiselhidaja.persistence.repository.DetalleSolicitudMaterialRepository;
import com.elhidaja.apiselhidaja.presentation.dto.detalleSolicitudMaterial.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.detalleSolicitudMaterial.Response.*;

@Service
@Validated
public class DetalleSolicitudMaterialService {
    private final DetalleSolicitudMaterialRepository repo;

    public DetalleSolicitudMaterialService(DetalleSolicitudMaterialRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public ResponseDetalleSolicitudMaterialAllDTO getAllSer(RequestDetalleSolicitudMaterialOptionDTO option) {
        return repo.getAllD(option);
    }

    @Transactional
    public ResponseDetalleSolicitudMaterialDTO getByIdSer(RequestDetalleSolicitudMaterialFilterDTO id) {
        return repo.getByIdD(id);
    }

    @Transactional
    public ResponserDetalleSolicitudMaterialMensajeDTO activateSer(RequestDetalleSolicitudMaterialIdDTO id) {
        return repo.activateD(id);
    }

    @Transactional
    public ResponserDetalleSolicitudMaterialMensajeDTO desactivateSer(RequestDetalleSolicitudMaterialIdDTO id) {
        return repo.desactivateD(id);
    }
}
