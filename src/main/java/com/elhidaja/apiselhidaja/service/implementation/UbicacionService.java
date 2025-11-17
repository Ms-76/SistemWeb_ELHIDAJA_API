package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elhidaja.apiselhidaja.persistence.repository.UbicacionRepository;
import com.elhidaja.apiselhidaja.presentation.dto.ubicacion.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.ubicacion.Response.*;
@Service
public class UbicacionService {
     private final UbicacionRepository ubicacionRepo;

    public UbicacionService(UbicacionRepository ubicacionRepo) {
        this.ubicacionRepo = ubicacionRepo;
    }

    @Transactional(readOnly = true)
    public ResponseUbicacionAllDTO getAllSer(RequestUbicacionOptionDTO option) {
        return ubicacionRepo.getAllD(option);
    }

    @Transactional(readOnly = true)
    public ResponseDetalleUbicacionDTO getByIdSer(RequestUbicacionIdDTO id) {
        return ubicacionRepo.getByIdD(id);
    }

    @Transactional
    public ResponserUbicacionMensajeDTO activateSer(RequestUbicacionIdDTO id) {
        return ubicacionRepo.activateD(id);
    }

    @Transactional
    public ResponserUbicacionMensajeDTO desactivateSer(RequestUbicacionIdDTO id) {
        return ubicacionRepo.desactivateD(id);
    }
}
