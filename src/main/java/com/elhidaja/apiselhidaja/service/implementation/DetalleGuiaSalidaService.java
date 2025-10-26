package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.elhidaja.apiselhidaja.persistence.repository.DetalleGuiaSalidaRepository;
import com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaSalida.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaSalida.Response.*;

@Service
public class DetalleGuiaSalidaService {
    private final DetalleGuiaSalidaRepository repo;

    public DetalleGuiaSalidaService(DetalleGuiaSalidaRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public ResponseDetalleGuiaSalidaAllDTO getAllSer(RequestDetalleGuiaSalidaOptionDTO option) {
        return repo.getAllD(option);
    }

    @Transactional
    public ResponseDetalleGuiaSalidaDTO getByIdSer(RequestDetalleGuiaSalidaFilterDTO id) {
        return repo.getByIdD(id);
    }

    @Transactional
    public ResponseDetalleGuiaSalidaMensajeDTO activateSer(RequestDetalleGuiaSalidaIdDTO id) {
        return repo.activateD(id);
    }

    @Transactional
    public ResponseDetalleGuiaSalidaMensajeDTO desactivateSer(RequestDetalleGuiaSalidaIdDTO id) {
        return repo.desactivateD(id);
    }
}
