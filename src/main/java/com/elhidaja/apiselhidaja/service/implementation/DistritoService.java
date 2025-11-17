package com.elhidaja.apiselhidaja.service.implementation;

import com.elhidaja.apiselhidaja.presentation.dto.distrito.Response.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elhidaja.apiselhidaja.persistence.repository.DistritoRepository;
import com.elhidaja.apiselhidaja.presentation.dto.distrito.Request.*;

@Service
public class DistritoService {
    private final DistritoRepository distritoRepo;

    public DistritoService(DistritoRepository distritoRepo) {
        this.distritoRepo = distritoRepo;
    }

    @Transactional
    public ResponseDistritoMensajeDTO insertSer(RequestDistritoInsertDTO objDistrito) {
        return distritoRepo.insertD(objDistrito);
    }

    @Transactional
    public ResponseDistritoMensajeDTO updateSer(RequestDistritoUpdateDTO objDistrito) {
        return distritoRepo.updateD(objDistrito);
    }

    @Transactional(readOnly = true)
    public ResponseDistritoAllDTO getAllSer(RequestDistritoOptionDTO option) {
        return distritoRepo.getAllD(option);
    }

    @Transactional(readOnly = true)
    public ResponseDetalleDistritoDTO getByIdSer(RequestDistritoFilterDTO id) {
        return distritoRepo.getByIdD(id);
    }

    @Transactional
    public ResponseDistritoMensajeDTO activateSer(RequestDistritoIdDTO id) {
        return distritoRepo.activateD(id);
    }

    @Transactional
    public ResponseDistritoMensajeDTO desactivateSer(RequestDistritoIdDTO id) {
        return distritoRepo.desactivateD(id);
    }
}
