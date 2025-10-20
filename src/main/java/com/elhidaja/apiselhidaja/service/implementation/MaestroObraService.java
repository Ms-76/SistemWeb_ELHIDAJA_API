package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.elhidaja.apiselhidaja.persistence.repository.MaestroObraRepository;
import com.elhidaja.apiselhidaja.presentation.dto.maestroobra.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.maestroobra.Response.*;

@Service
@Validated
public class MaestroObraService {
    private final MaestroObraRepository maestroObraRepo;

    public MaestroObraService(MaestroObraRepository maestroObraRepo) {
        this.maestroObraRepo = maestroObraRepo;
    }

    @Transactional
    public ResponseMaestroObraMensajeDTO insertSer(RequestMaestroObraInsertDTO obj) {
        return maestroObraRepo.insertD(obj);
    }

    @Transactional
    public ResponseMaestroObraMensajeDTO updateSer(RequestMaestroObraUpdateDTO obj) {
        return maestroObraRepo.updateD(obj);
    }

    @Transactional
    public ResponseMaestroObraAllDTO getAllSer(RequestMaestroObraOptionDTO option) {
        return maestroObraRepo.getAllD(option);
    }

    @Transactional
    public ResponseDetalleMaestroObraDTO getByIdSer(RequestMaestroObraFilterDTO id) {
        return maestroObraRepo.getByIdD(id);
    }

    @Transactional
    public ResponseMaestroObraMensajeDTO activateSer(RequestMaestroObraIdDTO id) {
        return maestroObraRepo.activateD(id);
    }

    @Transactional
    public ResponseMaestroObraMensajeDTO desactivateSer(RequestMaestroObraIdDTO id) {
        return maestroObraRepo.desactivateD(id);
    }
}
