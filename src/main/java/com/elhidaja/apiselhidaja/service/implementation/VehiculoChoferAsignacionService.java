package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elhidaja.apiselhidaja.persistence.repository.VehiculoChoferAsignacionRepository;
import com.elhidaja.apiselhidaja.presentation.dto.vehiculoChoferAsignacion.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.vehiculoChoferAsignacion.Response.*;

@Service
public class VehiculoChoferAsignacionService {
    private final VehiculoChoferAsignacionRepository asignacionRepo;

    public VehiculoChoferAsignacionService(VehiculoChoferAsignacionRepository asignacionRepo) {
        this.asignacionRepo = asignacionRepo;
    }

    @Transactional
    public ResponseVehiculoChoferAsignacionMensajeDTO insertSer(
            RequestVehiculoChoferAsignacionInsertDTO objAsignacion) {
        return asignacionRepo.insertD(objAsignacion);
    }

    @Transactional
    public ResponseVehiculoChoferAsignacionMensajeDTO updateSer(
            RequestVehiculoChoferAsignacionUpdateDTO objAsignacion) {
        return asignacionRepo.updateD(objAsignacion);
    }

    @Transactional(readOnly = true)
    public ResponseVehiculoChoferAsignacionAllDTO getAllSer(RequestVehiculoChoferAsignacionOptionDTO option) {
        return asignacionRepo.getAllD(option);
    }

    @Transactional(readOnly = true)
    public ResponseDetalleVehiculoChoferAsignacionDTO getByIdSer(RequestVehiculoChoferAsignacionFilterDTO id) {
        return asignacionRepo.getByIdD(id);
    }

    @Transactional
    public ResponseVehiculoChoferAsignacionMensajeDTO activateSer(RequestVehiculoChoferAsignacionIdDTO id) {
        return asignacionRepo.activateD(id);
    }

    @Transactional
    public ResponseVehiculoChoferAsignacionMensajeDTO desactivateSer(RequestVehiculoChoferAsignacionIdDTO id) {
        return asignacionRepo.desactivateD(id);
    }
}
