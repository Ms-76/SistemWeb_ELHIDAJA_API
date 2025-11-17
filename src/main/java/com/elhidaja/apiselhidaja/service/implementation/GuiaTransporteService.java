package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elhidaja.apiselhidaja.persistence.repository.GuiaTransporteRepository;
import com.elhidaja.apiselhidaja.presentation.dto.guiaTransporte.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.guiaTransporte.Response.*;

@Service
public class GuiaTransporteService {
     private final GuiaTransporteRepository guiaRepo;

    public GuiaTransporteService(GuiaTransporteRepository guiaRepo) {
        this.guiaRepo = guiaRepo;
    }

    @Transactional
    public ResponseGuiaTransporteMensajeDTO insertSer(RequestGuiaTransporteInsertDTO obj) {
        return guiaRepo.insertD(obj);
    }

    @Transactional(readOnly = true)
    public ResponseGuiaTransporteAllDTO getAllSer(RequestGuiaTransporteOptionDTO option) {
        return guiaRepo.getAllD(option);
    }

    @Transactional(readOnly = true)
    public ResponseDetalleGuiaTransporteDTO getByIdSer(RequestGuiaTransporteFilterDTO id) {
        return guiaRepo.getByIdD(id);
    }

    @Transactional
    public ResponseGuiaTransporteMensajeDTO activateSer(RequestGuiaTransporteIdDTO id) {
        return guiaRepo.activateD(id);
    }

    @Transactional
    public ResponseGuiaTransporteMensajeDTO desactivateSer(RequestGuiaTransporteIdDTO id) {
        return guiaRepo.desactivateD(id);
    }
}
