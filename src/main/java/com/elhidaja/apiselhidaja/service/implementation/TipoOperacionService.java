package com.elhidaja.apiselhidaja.service.implementation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elhidaja.apiselhidaja.persistence.repository.TipoOperacionRepository;
import com.elhidaja.apiselhidaja.presentation.dto.tipoOperacion.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.tipoOperacion.Response.*;

@Service
public class TipoOperacionService {
     private final TipoOperacionRepository tipoOperacionRepo;

    public TipoOperacionService(TipoOperacionRepository tipoOperacionRepo) {
        this.tipoOperacionRepo = tipoOperacionRepo;
    }

    @Transactional
    public ResponseTipoOperacionMensajeDTO insertSer(RequestTipoOperacionInsertDTO obj) {
        return tipoOperacionRepo.insertD(obj);
    }

    @Transactional
    public ResponseTipoOperacionMensajeDTO updateSer(RequestTipoOperacionUpdateDTO obj) {
        return tipoOperacionRepo.updateD(obj);
    }

    @Transactional(readOnly = true)
    public ResponseTipoOperacionAllDTO getAllSer(RequestTipoOperacionOptionDTO option) {
        return tipoOperacionRepo.getAllD(option);
    }

    @Transactional(readOnly = true)
    public ResponseDetalleTipoOperacionDTO getByIdSer(RequestTipoOperacionFilterDTO id) {
        return tipoOperacionRepo.getByIdD(id);
    }

    @Transactional
    public ResponseTipoOperacionMensajeDTO activateSer(RequestTipoOperacionIdDTO id) {
        return tipoOperacionRepo.activateD(id);
    }

    @Transactional
    public ResponseTipoOperacionMensajeDTO desactivateSer(RequestTipoOperacionIdDTO id) {
        return tipoOperacionRepo.desactivateD(id);
    }
}
