package com.elhidaja.apiselhidaja.service.implementation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elhidaja.apiselhidaja.persistence.repository.VehiculoRepository;
import com.elhidaja.apiselhidaja.presentation.dto.vehiculo.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.vehiculo.Response.*;

@Service
public class VehiculoService {
        private final VehiculoRepository vehiculoRepo;

    public VehiculoService(VehiculoRepository vehiculoRepo) {
        this.vehiculoRepo = vehiculoRepo;
    }

    @Transactional
    public ResponseVehiculoMensajeDTO insertSer(RequestVehiculoInsertDTO objVehiculo) {
        return vehiculoRepo.insertD(objVehiculo);
    }

    @Transactional
    public ResponseVehiculoMensajeDTO updateSer(RequestVehiculoUpdateDTO objVehiculo) {
        return vehiculoRepo.updateD(objVehiculo);
    }

    @Transactional(readOnly = true)
    public ResponseVehiculoAllDTO getAllSer(RequestVehiculoOptionDTO option) {
        return vehiculoRepo.getAllD(option);
    }

    @Transactional(readOnly = true)
    public ResponseDetalleVehiculoDTO getByIdSer(RequestVehiculoFilterDTO id) {
        return vehiculoRepo.getByIdD(id);
    }

    @Transactional
    public ResponseVehiculoMensajeDTO activateSer(RequestVehiculoIdDTO id) {
        return vehiculoRepo.activateD(id);
    }

    @Transactional
    public ResponseVehiculoMensajeDTO desactivateSer(RequestVehiculoIdDTO id) {
        return vehiculoRepo.desactivateD(id);
    }

}
