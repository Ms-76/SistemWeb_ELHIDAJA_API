package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elhidaja.apiselhidaja.persistence.repository.InventarioRepository;
import com.elhidaja.apiselhidaja.presentation.dto.inventario.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.inventario.Response.*;

@Service
public class InventarioService {

    private final InventarioRepository inventarioRepo;

    public InventarioService(InventarioRepository inventarioRepo) {
        this.inventarioRepo = inventarioRepo;
    }

    @Transactional
    public ResponseInventarioMensajeDTO insertSer(RequestInventarioInsertDTO objInventario) {
        return inventarioRepo.insertD(objInventario);
    }

    @Transactional
    public ResponseInventarioMensajeDTO updateSer(RequestInventarioUpdateDTO objInventario) {
        return inventarioRepo.updateD(objInventario);
    }

    @Transactional(readOnly = true)
    public ResponseInventarioAllDTO getAllSer(RequestInventarioOptionDTO option) {
        return inventarioRepo.getAllD(option);
    }

    @Transactional(readOnly = true)
    public ResponseDetalleInventarioDTO getByIdSer(RequestInventarioFilterDTO id) {
        return inventarioRepo.getByIdD(id);
    }

    @Transactional
    public ResponseInventarioMensajeDTO activateSer(RequestInventarioIdDTO id) {
        return inventarioRepo.activateD(id);
    }

    @Transactional
    public ResponseInventarioMensajeDTO desactivateSer(RequestInventarioIdDTO id) {
        return inventarioRepo.desactivateD(id);
    }
}
