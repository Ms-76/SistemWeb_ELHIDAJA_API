package com.elhidaja.apiselhidaja.service.implementation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.elhidaja.apiselhidaja.persistence.repository.ProvinciaRepository;
import com.elhidaja.apiselhidaja.presentation.dto.provincia.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.provincia.Response.*;

@Service
@Validated
public class ProvinciaService {
        private final ProvinciaRepository provinciaRepo;

    public ProvinciaService(ProvinciaRepository provinciaRepo) {
        this.provinciaRepo = provinciaRepo;
    }

    @Transactional
    public ResponseProvinciaMensajeDTO insertSer(RequestProvinciaInsertDTO objProvincia) {
        return provinciaRepo.insertD(objProvincia);
    }

    @Transactional
    public ResponseProvinciaMensajeDTO updateSer(RequestProvinciaUpdateDTO objProvincia) {
        return provinciaRepo.updateD(objProvincia);
    }

    @Transactional
    public ResponseProvinciaAllDTO getAllSer(RequestProvinciaOptionDTO option) {
        return provinciaRepo.getAllD(option);
    }

    @Transactional
    public ResponseDetalleProvinciaDTO getByIdSer(RequestProvinciaIdDTO id) {
        return provinciaRepo.getByIdD(id);
    }

    @Transactional
    public ResponseProvinciaMensajeDTO activateSer(RequestProvinciaIdDTO id) {
        return provinciaRepo.activateD(id);
    }

    @Transactional
    public ResponseProvinciaMensajeDTO desactivateSer(RequestProvinciaIdDTO id) {
        return provinciaRepo.desactivateD(id);
    }
}
