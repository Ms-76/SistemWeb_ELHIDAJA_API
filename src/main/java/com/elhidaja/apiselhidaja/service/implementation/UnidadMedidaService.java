package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elhidaja.apiselhidaja.persistence.repository.UnidadMedidaRepository;
import com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Resquest.*;

@Service
public class UnidadMedidaService {

    private final UnidadMedidaRepository unidadRepo;

    public UnidadMedidaService(UnidadMedidaRepository unidadRepo) {
        this.unidadRepo = unidadRepo;
    }

    @Transactional
    public ResponseUnidadMedidaMensajeDTO insertSer(RequestUnidadMedidaInsertDTO dto) {
        return unidadRepo.insertD(dto);
    }

    @Transactional
    public ResponseUnidadMedidaMensajeDTO updateSer(RequestUnidadMedidaUpdateDTO dto) {
        return unidadRepo.updateD(dto);
    }

    @Transactional(readOnly = true)
    public ResponseUnidadMedidaAllDTO getAllSer(RequestUnidadMedidaOptionDTO option) {
        return unidadRepo.getAllD(option);
    }

    @Transactional(readOnly = true)
    public ResponseDetalleUnidadMedidaDTO getByIdSer(RequestUnidadMedidaFilterDTO id) {
        return unidadRepo.getByIdD(id);
    }

    @Transactional
    public ResponseUnidadMedidaMensajeDTO activateSer(RequestUnidadMedidaIdDTO id) {
        return unidadRepo.activateD(id);
    }

    @Transactional
    public ResponseUnidadMedidaMensajeDTO desactivateSer(RequestUnidadMedidaIdDTO id) {
        return unidadRepo.desactivateD(id);
    }
}
