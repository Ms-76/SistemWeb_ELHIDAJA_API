package com.elhidaja.apiselhidaja.service.implementation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.elhidaja.apiselhidaja.persistence.repository.IngenieroRepository;
import com.elhidaja.apiselhidaja.presentation.dto.ingeniero.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.ingeniero.Response.*;

@Service
@Validated
public class IngenieroService {
        private final IngenieroRepository ingenieroRepo;

    public IngenieroService(IngenieroRepository ingenieroRepo) {
        this.ingenieroRepo = ingenieroRepo;
    }

    @Transactional
    public ResponseIngenieroMensajeDTO insertSer(RequestIngenieroInsertDTO objIngeniero) {
        return ingenieroRepo.insertD(objIngeniero);
    }

    @Transactional
    public ResponseIngenieroMensajeDTO updateSer(RequestIngenieroUpdateDTO objIngeniero) {
        return ingenieroRepo.updateD(objIngeniero);
    }

    @Transactional
    public ResponseIngenieroAllDTO getAllSer(RequestIngenieroOptionDTO option) {
        return ingenieroRepo.getAllD(option);
    }

    @Transactional
    public ResponseDetalleIngenieroDTO getByIdSer(RequestIngenieroFilterDTO id) {
        return ingenieroRepo.getByIdD(id);
    }

    @Transactional
    public ResponseIngenieroMensajeDTO activateSer(RequestIngenieroIdDTO id) {
        return ingenieroRepo.activateD(id);
    }

    @Transactional
    public ResponseIngenieroMensajeDTO desactivateSer(RequestIngenieroIdDTO id) {
        return ingenieroRepo.desactivateD(id);
    }
}
