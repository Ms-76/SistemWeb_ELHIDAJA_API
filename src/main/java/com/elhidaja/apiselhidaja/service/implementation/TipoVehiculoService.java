package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elhidaja.apiselhidaja.persistence.repository.TipoVehiculoRepository;
import com.elhidaja.apiselhidaja.presentation.dto.tipoVehiculo.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.tipoVehiculo.Response.*;

@Service
public class TipoVehiculoService {
    private final TipoVehiculoRepository tipoVehiculoRepo;

    public TipoVehiculoService(TipoVehiculoRepository tipoVehiculoRepo) {
        this.tipoVehiculoRepo = tipoVehiculoRepo;
    }

    @Transactional
    public ResponseTipoVehiculoMensajeDTO insertSer(RequestTipoVehiculoInsertDTO objTipoVehiculo) {
        return tipoVehiculoRepo.insertD(objTipoVehiculo);
    }

    @Transactional
    public ResponseTipoVehiculoMensajeDTO updateSer(RequestTipoVehiculoUpdateDTO objTipoVehiculo) {
        return tipoVehiculoRepo.updateD(objTipoVehiculo);
    }

    @Transactional(readOnly = true)
    public ResponseTipoVehiculoAllDTO getAllSer(RequestTipoVehiculoOptionDTO option) {
        return tipoVehiculoRepo.getAllD(option);
    }

    @Transactional(readOnly = true)
    public ResponseDetalleTipoVehiculoDTO getByIdSer(RequestTipoVehiculoFilterDTO id) {
        return tipoVehiculoRepo.getByIdD(id);
    }

    @Transactional
    public ResponseTipoVehiculoMensajeDTO activateSer(RequestTipoVehiculoIdDTO id) {
        return tipoVehiculoRepo.activateD(id);
    }

    @Transactional
    public ResponseTipoVehiculoMensajeDTO desactivateSer(RequestTipoVehiculoIdDTO id) {
        return tipoVehiculoRepo.desactivateD(id);
    }
}
