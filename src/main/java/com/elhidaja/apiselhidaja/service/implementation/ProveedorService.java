package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.elhidaja.apiselhidaja.persistence.repository.ProveedorRepository;
import com.elhidaja.apiselhidaja.presentation.dto.proveedor.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.proveedor.Response.*;

@Service
@Validated
public class ProveedorService {
    private final ProveedorRepository provRepo;

    public ProveedorService(ProveedorRepository provRepo) {
        this.provRepo = provRepo;
    }

    @Transactional
    public ResponseProveedorMensajeDTO insertSer(RequestProveedorInsertDTO objProveedor) {
        return provRepo.insertD(objProveedor);
    }

    @Transactional
    public ResponseProveedorMensajeDTO updateSer(RequestProveedorUpdateDTO objProveedor) {
        return provRepo.updateD(objProveedor);
    }

    @Transactional
    public ResponseProveedorAllDTO getAllSer(RequestProveedorOptionDTO option) {
        return provRepo.getAllD(option);
    }

    @Transactional
    public ResponseDetalleProveedorDTO getByIdSer(RequestProveedorFilterDTO id) {
        return provRepo.getByIdD(id);
    }

    @Transactional
    public ResponseProveedorMensajeDTO activateSer(RequestProveedorIdDTO id) {
        return provRepo.activateD(id);
    }

    @Transactional
    public ResponseProveedorMensajeDTO desactivateSer(RequestProveedorIdDTO id) {
        return provRepo.desactivateD(id);
    }
}
