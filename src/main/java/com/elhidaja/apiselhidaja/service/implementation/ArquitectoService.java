package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elhidaja.apiselhidaja.persistence.repository.ArquitectoRepository;
import com.elhidaja.apiselhidaja.presentation.dto.arquitecto.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.arquitecto.Response.*;

@Service
public class ArquitectoService {
    private final ArquitectoRepository arquitectoRepo;

    public ArquitectoService(ArquitectoRepository arquitectoRepo) {
        this.arquitectoRepo = arquitectoRepo;
    }

    @Transactional
    public ResponseArquitectoMensajeDTO insertSer(RequestArquitectoInsertDTO obj) {
        return arquitectoRepo.insertD(obj);
    }

    @Transactional
    public ResponseArquitectoMensajeDTO updateSer(RequestArquitectoUpdateDTO obj) {
        return arquitectoRepo.updateD(obj);
    }

    @Transactional(readOnly = true)
    public ResponseArquitectoAllDTO getAllSer(RequestArquitectoOptionDTO option) {
        return arquitectoRepo.getAllD(option);
    }

    @Transactional(readOnly = true)
    public ResponseDetalleArquitectoDTO getByIdSer(RequestArquitectoFilterDTO id) {
        return arquitectoRepo.getByIdD(id);
    }

    @Transactional
    public ResponseArquitectoMensajeDTO activateSer(RequestArquitectoIdDTO id) {
        return arquitectoRepo.activateD(id);
    }

    @Transactional
    public ResponseArquitectoMensajeDTO desactivateSer(RequestArquitectoIdDTO id) {
        return arquitectoRepo.desactivateD(id);
    }
}
