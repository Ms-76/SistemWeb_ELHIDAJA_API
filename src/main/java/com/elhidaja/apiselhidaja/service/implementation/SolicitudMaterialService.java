package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.elhidaja.apiselhidaja.persistence.repository.SolicitudMaterialRepository;
import com.elhidaja.apiselhidaja.presentation.dto.solicitudMaterial.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.solicitudMaterial.Response.*;

@Service
@Validated
public class SolicitudMaterialService {
        private final SolicitudMaterialRepository solicitudRepo;

    public SolicitudMaterialService(SolicitudMaterialRepository solicitudRepo) {
        this.solicitudRepo = solicitudRepo;
    }

    @Transactional
    public ResponseSolicitudMaterialMensajeDTO insertSer(RequestSolicitudMaterialInsertDTO objSolicitud) {
        return solicitudRepo.insertD(objSolicitud);
    }

    @Transactional
    public ResponseSolicitudMaterialAllDTO getAllSer(RequestSolicitudMaterialOptionDTO option) {
        return solicitudRepo.getAllD(option);
    }

    @Transactional
    public ResponseDetalleSolicitudMaterialDTO getByIdSer(RequestSolicitudMaterialFilterDTO id) {
        return solicitudRepo.getByIdD(id);
    }

    @Transactional
    public ResponseSolicitudMaterialMensajeDTO activateSer(RequestSolicitudMaterialIdDTO id) {
        return solicitudRepo.activateD(id);
    }

    @Transactional
    public ResponseSolicitudMaterialMensajeDTO desactivateSer(RequestSolicitudMaterialIdDTO id) {
        return solicitudRepo.desactivateD(id);
    }
}
