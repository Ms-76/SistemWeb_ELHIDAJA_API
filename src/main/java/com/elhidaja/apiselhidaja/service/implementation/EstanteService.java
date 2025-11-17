package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elhidaja.apiselhidaja.persistence.repository.EstanteRepository;
import com.elhidaja.apiselhidaja.presentation.dto.estante.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.estante.Response.*;

@Service
public class EstanteService {
     private final EstanteRepository estanteRepo;

    public EstanteService(EstanteRepository estanteRepo) {
        this.estanteRepo = estanteRepo;
    }

    @Transactional
    public ResponseEstanteMensajeDTO insertSer(RequestEstanteInsertDTO objEstante) {
        return estanteRepo.insertD(objEstante);
    }

    @Transactional
    public ResponseEstanteMensajeDTO updateSer(RequestEstanteUpdateDTO objEstante) {
        return estanteRepo.updateD(objEstante);
    }

    @Transactional(readOnly = true)
    public ResponseEstanteAllDTO getAllSer(RequestEstanteOptionDTO option) {
        return estanteRepo.getAllD(option);
    }

    @Transactional(readOnly = true)
    public ResponseDetalleEstanteDTO getByIdSer(RequestEstanteFilterDTO id) {
        return estanteRepo.getByIdD(id);
    }

    @Transactional
    public ResponseEstanteMensajeDTO activateSer(RequestEstanteIdDTO id) {
        return estanteRepo.activateD(id);
    }

    @Transactional
    public ResponseEstanteMensajeDTO desactivateSer(RequestEstanteIdDTO id) {
        return estanteRepo.desactivateD(id);
    }
}
