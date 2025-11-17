package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elhidaja.apiselhidaja.persistence.repository.AreaRepository;
import com.elhidaja.apiselhidaja.presentation.dto.area.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.area.Response.*;

@Service
public class AreaService {
    private final AreaRepository areaRepo;

    public AreaService(AreaRepository areaRepo) {
        this.areaRepo = areaRepo;
    }

    @Transactional
    public ResponseAreaMensajeDTO insertSer(RequestAreaInsertDTO objArea) {
        return areaRepo.insertD(objArea);
    }

    @Transactional
    public ResponseAreaMensajeDTO updateSer(RequestAreaUpdateDTO objArea) {
        return areaRepo.updateD(objArea);
    }

    @Transactional(readOnly = true)
    public ResponseAreaAllDTO getAllSer(RequestAreaOptionDTO option) {
        return areaRepo.getAllD(option);
    }

    @Transactional(readOnly = true)
    public ResponseDetalleAreaDTO getByIdSer(RequestAreaFilterDTO id) {
        return areaRepo.getByIdD(id);
    }

    @Transactional
    public ResponseAreaMensajeDTO activateSer(RequestAreaIdDTO id) {
        return areaRepo.activateD(id);
    }

    @Transactional
    public ResponseAreaMensajeDTO desactivateSer(RequestAreaIdDTO id) {
        return areaRepo.desactivateD(id);
    }
}
