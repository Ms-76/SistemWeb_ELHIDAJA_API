package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elhidaja.apiselhidaja.persistence.repository.TransporteDetalleRepository;
import com.elhidaja.apiselhidaja.presentation.dto.transporteDetalle.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.transporteDetalle.Response.*;

@Service
public class TransporteDetalleService {
      private final TransporteDetalleRepository transporteDetalleRepo;

    public TransporteDetalleService(TransporteDetalleRepository transporteDetalleRepo) {
        this.transporteDetalleRepo = transporteDetalleRepo;
    }

    @Transactional(readOnly = true)
    public ResponseTransporteDetalleAllDTO getAllSer(RequestTransporteDetalleOptionDTO option) {
        return transporteDetalleRepo.getAllD(option);
    }

    @Transactional(readOnly = true)
    public ResponseDetalleTransporteDetalleDTO getByIdSer(RequestTransporteDetalleFilterDTO id) {
        return transporteDetalleRepo.getByIdD(id);
    }

    @Transactional
    public ResponseTransporteDetalleMensajeDTO activateSer(RequestTransporteDetalleIdDTO id) {
        return transporteDetalleRepo.activateD(id);
    }

    @Transactional
    public ResponseTransporteDetalleMensajeDTO desactivateSer(RequestTransporteDetalleIdDTO id) {
        return transporteDetalleRepo.desactivateD(id);
    }
}
