package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.elhidaja.apiselhidaja.persistence.repository.DetalleGuiaEntradaRepository;
import com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaEntrada.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaEntrada.Response.*;

@Service
public class DetalleGuiaEntradaService {
    private final DetalleGuiaEntradaRepository detalleRepo;

    public DetalleGuiaEntradaService(DetalleGuiaEntradaRepository detalleRepo) {
        this.detalleRepo = detalleRepo;
    }

    @Transactional
    public ResponseDetalleGuiaEntradaMensajeDTO insertSer(RequestDetalleGuiaEntradaInsertDTO objDetalleGuiaEntrada) {
        return detalleRepo.insertD(objDetalleGuiaEntrada);
    }

    @Transactional
    public ResponseDetalleGuiaEntradaMensajeDTO updateSer(RequestDetalleGuiaEntradaUpdateDTO objDetalleGuiaEntrada) {
        return detalleRepo.updateD(objDetalleGuiaEntrada);
    }

    @Transactional
    public ResponseDetalleGuiaEntradaAllDTO getAllSer(RequestDetalleGuiaEntradaOptionDTO option) {
        return detalleRepo.getAllD(option);
    }

    @Transactional
    public ResponseDetalleGuiaEntradaDTO getByIdSer(RequestDetalleGuiaEntradaFilterDTO id) {
        return detalleRepo.getByIdD(id);
    }

    @Transactional
    public ResponseDetalleGuiaEntradaMensajeDTO activateSer(RequestDetalleGuiaEntradaIdDTO id) {
        return detalleRepo.activateD(id);
    }

    @Transactional
    public ResponseDetalleGuiaEntradaMensajeDTO desactivateSer(RequestDetalleGuiaEntradaIdDTO id) {
        return detalleRepo.desactivateD(id);
    }
}
