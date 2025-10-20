package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.elhidaja.apiselhidaja.persistence.repository.SerieDocumentoRepository;
import com.elhidaja.apiselhidaja.presentation.dto.serieDocumento.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.serieDocumento.Response.*;

@Service
@Validated
public class SerieDocumentoService {
    private final SerieDocumentoRepository serieRepo;

    public SerieDocumentoService(SerieDocumentoRepository serieRepo) {
        this.serieRepo = serieRepo;
    }

    @Transactional
    public ResponseSerieDocumentoMensajeDTO insertSer(RequestSerieDocumentoInsertDTO objSerieDocumento) {
        return serieRepo.insertD(objSerieDocumento);
    }

    @Transactional
    public ResponseSerieDocumentoMensajeDTO updateSer(RequestSerieDocumentoUpdateDTO objSerieDocumento) {
        return serieRepo.updateD(objSerieDocumento);
    }

    @Transactional
    public ResponseSerieDocumentoAllDTO getAllSer(RequestSerieDocumentoOptionDTO option) {
        return serieRepo.getAllD(option);
    }

    @Transactional
    public ResponseDetalleSerieDocumentoDTO getByIdSer(RequestSerieDocumentoFilterDTO id) {
        return serieRepo.getByIdD(id);
    }

    @Transactional
    public ResponseSerieDocumentoMensajeDTO activateSer(RequestSerieDocumentoIdDTO id) {
        return serieRepo.activateD(id);
    }

    @Transactional
    public ResponseSerieDocumentoMensajeDTO desactivateSer(RequestSerieDocumentoIdDTO id) {
        return serieRepo.desactivateD(id);
    }
}
