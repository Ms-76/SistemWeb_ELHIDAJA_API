package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.elhidaja.apiselhidaja.persistence.repository.PuestoRepository;
import com.elhidaja.apiselhidaja.presentation.dto.puesto.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.puesto.Response.*;

@Service
public class PuestoService {
    private final PuestoRepository puestoRepo;

    public PuestoService(PuestoRepository puestoRepo) {
        this.puestoRepo = puestoRepo;
    }

    @Transactional
    public ResponsePuestoMensajeDTO insertSer(RequestPuestoInsertDTO objPuesto) {
        return puestoRepo.insertD(objPuesto);
    }

    @Transactional
    public ResponsePuestoMensajeDTO updateSer(RequestPuestoUpdateDTO objPuesto) {
        return puestoRepo.updateD(objPuesto);
    }

    @Transactional(readOnly = true)
    public ResponsePuestoAllDTO getAllSer(RequestPuestoOptionDTO option) {
        return puestoRepo.getAllD(option);
    }

     @Transactional(readOnly = true)
    public ResponseDetallePuestoDTO getByIdSer(RequestPuestoFilterDTO id) {
        return puestoRepo.getByIdD(id);
    }

    @Transactional
    public ResponsePuestoMensajeDTO activateSer(RequestPuestoIdDTO id) {
        return puestoRepo.activateD(id);
    }

    @Transactional
    public ResponsePuestoMensajeDTO desactivateSer(RequestPuestoIdDTO id) {
        return puestoRepo.desactivateD(id);
    }
}
