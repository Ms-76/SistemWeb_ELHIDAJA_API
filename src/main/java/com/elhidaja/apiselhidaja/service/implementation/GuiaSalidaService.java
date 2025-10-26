package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.elhidaja.apiselhidaja.persistence.repository.GuiaSalidaRepository;
import com.elhidaja.apiselhidaja.presentation.dto.guiaSalida.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.guiaSalida.Response.*;

@Service
@Validated
public class GuiaSalidaService {
    private final GuiaSalidaRepository guiaSalidaRepo;

    public GuiaSalidaService(GuiaSalidaRepository guiaSalidaRepo) {
        this.guiaSalidaRepo = guiaSalidaRepo;
    }

    @Transactional
    public ResponseGuiaSalidaMensajeDTO insertSer(RequestGuiaSalidaInsertDTO obj) {
        return guiaSalidaRepo.insertD(obj);
    }

    @Transactional
    public ResponseGuiaSalidaMensajeDTO updateSer(RequestGuiaSalidaUpdateDTO obj) {
        return guiaSalidaRepo.updateD(obj);
    }

    @Transactional
    public ResponseGuiaSalidaAllDTO getAllSer(RequestGuiaSalidaOptionDTO option) {
        return guiaSalidaRepo.getAllD(option);
    }

    @Transactional
    public ResponseDetalleGuiaSalidaDTO getByIdSer(RequestGuiaSalidaFilterDTO id) {
        return guiaSalidaRepo.getByIdD(id);
    }

    @Transactional
    public ResponseGuiaSalidaMensajeDTO activateSer(RequestGuiaSalidaIdDTO id) {
        return guiaSalidaRepo.activateD(id);
    }

    @Transactional
    public ResponseGuiaSalidaMensajeDTO desactivateSer(RequestGuiaSalidaIdDTO id) {
        return guiaSalidaRepo.desactivateD(id);
    }


}
