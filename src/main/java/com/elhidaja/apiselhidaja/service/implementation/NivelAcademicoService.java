package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elhidaja.apiselhidaja.persistence.repository.NivelAcademicoRepository;
import com.elhidaja.apiselhidaja.presentation.dto.nivelAcademico.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.nivelAcademico.Response.*;

@Service
public class NivelAcademicoService {
       private final NivelAcademicoRepository nivelRepo;

    public NivelAcademicoService(NivelAcademicoRepository nivelRepo) {
        this.nivelRepo = nivelRepo;
    }

    @Transactional
    public ResponseNivelAcademicoMensajeDTO insertSer(RequestNivelAcademicoInsertDTO objNivel) {
        return nivelRepo.insertD(objNivel);
    }

    @Transactional
    public ResponseNivelAcademicoMensajeDTO updateSer(RequestNivelAcademicoUpdateDTO objNivel) {
        return nivelRepo.updateD(objNivel);
    }

    @Transactional(readOnly = true)
    public ResponseNivelAcademicoAllDTO getAllSer(RequestNivelAcademicoOptionDTO option) {
        return nivelRepo.getAllD(option);
    }

    @Transactional(readOnly = true)
    public ResponseDetalleNivelAcademicoDTO getByIdSer(RequestNivelAcademicoFilterDTO id) {
        return nivelRepo.getByIdD(id);
    }

    @Transactional
    public ResponseNivelAcademicoMensajeDTO activateSer(RequestNivelAcademicoIdDTO id) {
        return nivelRepo.activateD(id);
    }

    @Transactional
    public ResponseNivelAcademicoMensajeDTO desactivateSer(RequestNivelAcademicoIdDTO id) {
        return nivelRepo.desactivateD(id);
    }
}
