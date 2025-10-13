package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.elhidaja.apiselhidaja.persistence.repository.OficioRepository;
import com.elhidaja.apiselhidaja.presentation.dto.oficio.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.oficio.Response.*;

@Service
@Validated
public class OficioService {
    private final OficioRepository oficioRepo;

    public OficioService(OficioRepository oficioRepo) {
        this.oficioRepo = oficioRepo;
    }

    @Transactional
    public ResponseOficioMensajeDTO insertSer(RequestOficioInsertDTO objOficio) {
        return oficioRepo.insertD(objOficio);
    }

    @Transactional
    public ResponseOficioMensajeDTO updateSer(RequestOficioUpdateDTO objOficio) {
        return oficioRepo.updateD(objOficio);
    }

    @Transactional
    public ResponseOficioAllDTO getAllSer(RequestOficioOptionDTO option) {
        return oficioRepo.getAllD(option);
    }

    @Transactional
    public ResponseDetalleOficioDTO getByIdSer(RequestOficioIdDTO id) {
        return oficioRepo.getByIdD(id);
    }

    @Transactional
    public ResponseOficioMensajeDTO activateSer(RequestOficioIdDTO id) {
        return oficioRepo.activateD(id);
    }

    @Transactional
    public ResponseOficioMensajeDTO desactivateSer(RequestOficioIdDTO id) {
        return oficioRepo.deactivateD(id);
    }
}
