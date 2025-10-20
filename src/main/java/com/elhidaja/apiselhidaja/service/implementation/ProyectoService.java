package com.elhidaja.apiselhidaja.service.implementation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.elhidaja.apiselhidaja.persistence.repository.ProyectoRepository;
import com.elhidaja.apiselhidaja.presentation.dto.proyecto.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.proyecto.Response.*;
@Service
@Validated
public class ProyectoService {
        private final ProyectoRepository proyRepo;

    public ProyectoService(ProyectoRepository proyRepo) {
        this.proyRepo = proyRepo;
    }

    @Transactional
    public ResponseProyectoMensajeDTO insertSer(RequestProyectoInsertDTO objProyecto) {
        return proyRepo.insertD(objProyecto);
    }

    @Transactional
    public ResponseProyectoMensajeDTO updateSer(RequestProyectoUpdateDTO objProyecto) {
        return proyRepo.updateD(objProyecto);
    }

    @Transactional
    public ResponseProyectoAllDTO getAllSer(RequestProyectoOptionDTO option) {
        return proyRepo.getAllD(option);
    }

    @Transactional
    public ResponseDetalleProyectoDTO getByIdSer(RequestProyectoFilterDTO id) {
        return proyRepo.getByIdD(id);
    }

    @Transactional
    public ResponseProyectoMensajeDTO activateSer(RequestProyectoIdDTO id) {
        return proyRepo.activateD(id);
    }

    @Transactional
    public ResponseProyectoMensajeDTO desactivateSer(RequestProyectoIdDTO id) {
        return proyRepo.desactivateD(id);
    }
}
