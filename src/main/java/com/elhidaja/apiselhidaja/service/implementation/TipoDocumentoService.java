package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elhidaja.apiselhidaja.persistence.repository.TipoDocumentoRepository;
import com.elhidaja.apiselhidaja.presentation.dto.tipoDocumento.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.tipoDocumento.Response.*;

@Service
public class TipoDocumentoService {
    private final TipoDocumentoRepository tipoDocRepo;

    public TipoDocumentoService(TipoDocumentoRepository tipoDocRepo) {
        this.tipoDocRepo = tipoDocRepo;
    }

    @Transactional
    public ResponseTipoDocumentoMensajeDTO insertSer(RequestTipoDocumentoInsertDTO objTipoDocumento) {
        return tipoDocRepo.insertD(objTipoDocumento);
    }

    @Transactional
    public ResponseTipoDocumentoMensajeDTO updateSer(RequestTipoDocumentoUpdateDTO objTipoDocumento) {
        return tipoDocRepo.updateD(objTipoDocumento);
    }

    @Transactional(readOnly = true)
    public ResponseTipoDocumentoAllDTO getAllSer(RequestTipoDocumentoOptionDTO option) {
        return tipoDocRepo.getAllD(option);
    }

    @Transactional(readOnly = true)
    public ResponseDetalleTipoDocumentoDTO getByIdSer(RequestTipoDocumentoFilterDTO id) {
        return tipoDocRepo.getByIdD(id);
    }

    @Transactional
    public ResponseTipoDocumentoMensajeDTO activateSer(RequestTipoDocumentoIdDTO id) {
        return tipoDocRepo.activateD(id);
    }

    @Transactional
    public ResponseTipoDocumentoMensajeDTO desactivateSer(RequestTipoDocumentoIdDTO id) {
        return tipoDocRepo.desactivateD(id);
    }
}
