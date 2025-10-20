package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.elhidaja.apiselhidaja.persistence.repository.RolRepository;
import com.elhidaja.apiselhidaja.presentation.dto.rol.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.rol.Response.*;

@Service
@Validated
public class RolService {
    private final RolRepository rolRepo;

    public RolService(RolRepository rolRepo) {
        this.rolRepo = rolRepo;
    }

    @Transactional
    public ResponseRolMensajeDTO insertSer(RequestRolInsertDTO objRol) {
        return rolRepo.insertD(objRol);
    }

    @Transactional
    public ResponseRolMensajeDTO updateSer(RequestRolUpdateDTO objRol) {
        return rolRepo.updateD(objRol);
    }

    @Transactional
    public ResponseRolAllDTO getAllSer(RequestRolOptionDTO option) {
        return rolRepo.getAllD(option);
    }

    @Transactional
    public ResponseDetalleRolDTO getByIdSer(RequestRolFilterDTO id) {
        return rolRepo.getByIdD(id);
    }

    @Transactional
    public ResponseRolMensajeDTO activateSer(RequestRolIdDTO id) {
        return rolRepo.activateD(id);
    }

    @Transactional
    public ResponseRolMensajeDTO deactivateSer(RequestRolIdDTO id) {
        return rolRepo.deactivateD(id);
    }
}
