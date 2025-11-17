package com.elhidaja.apiselhidaja.service.implementation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elhidaja.apiselhidaja.persistence.repository.ChoferRepository;
import com.elhidaja.apiselhidaja.presentation.dto.chofer.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.chofer.Response.*;

@Service
public class ChoferService {
     private final ChoferRepository choferRepo;

    public ChoferService(ChoferRepository choferRepo) {
        this.choferRepo = choferRepo;
    }

    @Transactional
    public ResponseChoferMensajeDTO insertSer(RequestChoferInsertDTO objChofer) {
        return choferRepo.insertD(objChofer);
    }

    @Transactional
    public ResponseChoferMensajeDTO updateSer(RequestChoferUpdateDTO objChofer) {
        return choferRepo.updateD(objChofer);
    }

    @Transactional(readOnly = true)
    public ResponseChoferAllDTO getAllSer(RequestChoferOptionDTO option) {
        return choferRepo.getAllD(option);
    }

    @Transactional(readOnly = true)
    public ResponseDetalleChoferDTO getByIdSer(RequestChoferFilterDTO id) {
        return choferRepo.getByIdD(id);
    }

    @Transactional
    public ResponseChoferMensajeDTO activateSer(RequestChoferIdDTO id) {
        return choferRepo.activateD(id);
    }

    @Transactional
    public ResponseChoferMensajeDTO desactivateSer(RequestChoferIdDTO id) {
        return choferRepo.desactivateD(id);
    }
}
