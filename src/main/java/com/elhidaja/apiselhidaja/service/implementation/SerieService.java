package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elhidaja.apiselhidaja.persistence.repository.SerieRepository;
import com.elhidaja.apiselhidaja.presentation.dto.serie.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.serie.Response.*;

@Service
public class SerieService {
    private final SerieRepository serieRepo;

    public SerieService(SerieRepository serieRepo) {
        this.serieRepo = serieRepo;
    }

    @Transactional
    public ResponseSerieMensajeDTO insertSer(RequestSerieInsertDTO objSerie) {
        return serieRepo.insertD(objSerie);
    }

    @Transactional
    public ResponseSerieMensajeDTO updateSer(RequestSerieUpdateDTO objSerie) {
        return serieRepo.updateD(objSerie);
    }

    @Transactional(readOnly = true)
    public ResponseSerieAllDTO getAllSer(RequestSerieOptionDTO option) {
        return serieRepo.getAllD(option);
    }

    @Transactional(readOnly = true)
    public ResponseDetalleSerieDTO getByIdSer(RequestSerieFilterDTO id) {
        return serieRepo.getByIdD(id);
    }

    @Transactional
    public ResponseSerieMensajeDTO activateSer(RequestSerieIdDTO id) {
        return serieRepo.activateD(id);
    }

    @Transactional
    public ResponseSerieMensajeDTO deactivateSer(RequestSerieIdDTO id) {
        return serieRepo.deactivateD(id);
    }
}
