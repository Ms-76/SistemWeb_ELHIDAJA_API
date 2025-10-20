package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.elhidaja.apiselhidaja.persistence.repository.GuiaEntradaRepository;
import com.elhidaja.apiselhidaja.presentation.dto.guiaEntrada.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.guiaEntrada.Response.*;

@Service
@Validated
public class GuiaEntradaService {
        private final GuiaEntradaRepository guiaEntradaRepo;

    public GuiaEntradaService(GuiaEntradaRepository guiaEntradaRepo) {
        this.guiaEntradaRepo = guiaEntradaRepo;
    }

    @Transactional
    public ResponseGuiaEntradaMensajeDTO insertSer(RequestGuiaEntradaInsertDTO objGuiaEntrada) {
        return guiaEntradaRepo.insertD(objGuiaEntrada);
    }

    @Transactional
    public ResponseGuiaEntradaMensajeDTO updateSer(RequestGuiaEntradaUpdateDTO objGuiaEntrada) {
        return guiaEntradaRepo.updateD(objGuiaEntrada);
    }

    @Transactional
    public ResponseGuiaEntradaAllDTO getAllSer(RequestGuiaEntradaOptionDTO option) {
        return guiaEntradaRepo.getAllD(option);
    }

    @Transactional
    public ResponseDetalleGuiaEntradaDTO getByIdSer(RequestGuiaEntradaFilterDTO id) {
        return guiaEntradaRepo.getByIdD(id);
    }

    @Transactional
    public ResponseGuiaEntradaMensajeDTO activateSer(RequestGuiaEntradaIdDTO id) {
        return guiaEntradaRepo.activateD(id);
    }

    @Transactional
    public ResponseGuiaEntradaMensajeDTO desactivateSer(RequestGuiaEntradaIdDTO id) {
        return guiaEntradaRepo.desactivateD(id);
    }
}
