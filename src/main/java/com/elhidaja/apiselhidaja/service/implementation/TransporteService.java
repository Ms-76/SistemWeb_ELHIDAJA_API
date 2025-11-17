package com.elhidaja.apiselhidaja.service.implementation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elhidaja.apiselhidaja.persistence.repository.TransporteRepository;
import com.elhidaja.apiselhidaja.presentation.dto.transporte.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.transporte.Response.*;

@Service
public class TransporteService {
      private final TransporteRepository transporteRepo;

    public TransporteService(TransporteRepository transporteRepo) {
        this.transporteRepo = transporteRepo;
    }

    @Transactional
    public ResponseTransporteMensajeDTO insertSer(RequestTransporteInsertDTO objTransporte) {
        return transporteRepo.insertD(objTransporte);
    }

    @Transactional(readOnly = true)
    public ResponseTransporteAllDTO getAllSer(RequestTransporteOptionDTO option) {
        return transporteRepo.getAllD(option);
    }

    @Transactional(readOnly = true)
    public ResponseDetalleTransporteDTO getByIdSer(RequestTransporteFilterDTO id) {
        return transporteRepo.getByIdD(id);
    }

    @Transactional
    public ResponseTransporteMensajeDTO activateSer(RequestTransporteIdDTO id) {
        return transporteRepo.activateD(id);
    }

    @Transactional
    public ResponseTransporteMensajeDTO desactivateSer(RequestTransporteIdDTO id) {
        return transporteRepo.desactivateD(id);
    }
}
