package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.elhidaja.apiselhidaja.persistence.repository.DetalleInventarioRepository;
import com.elhidaja.apiselhidaja.presentation.dto.detalleInventario.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.detalleInventario.Response.*;

@Service
@Validated
public class DetalleInventarioService {
    private final DetalleInventarioRepository detInvRepo;

    public DetalleInventarioService(DetalleInventarioRepository detInvRepo) {
        this.detInvRepo = detInvRepo;
    }

    @Transactional
    public ResponseDetalleInventarioMensajeDTO insertSer(RequestDetalleInventarioInsertDTO objDetalleInventario) {
        return detInvRepo.insertD(objDetalleInventario);
    }

    @Transactional
    public ResponseDetalleInventarioMensajeDTO updateSer(RequestDetalleInventarioUpdateDTO objDetalleInventario) {
        return detInvRepo.updateD(objDetalleInventario);
    }

    @Transactional
    public ResponseDetalleInventarioAllDTO getAllSer(RequestDetalleInventarioOptionDTO option) {
        return detInvRepo.getAllD(option);
    }

    @Transactional
    public ResponseDetalleInventarioDTO getByIdSer(RequestDetalleInventarioIdDTO id) {
        return detInvRepo.getByIdD(id);
    }

    @Transactional
    public ResponseDetalleInventarioMensajeDTO activateSer(RequestDetalleInventarioIdDTO id) {
        return detInvRepo.activateD(id);
    }

    @Transactional
    public ResponseDetalleInventarioMensajeDTO desactivateSer(RequestDetalleInventarioIdDTO id) {
        return detInvRepo.desactivateD(id);
    }
}
