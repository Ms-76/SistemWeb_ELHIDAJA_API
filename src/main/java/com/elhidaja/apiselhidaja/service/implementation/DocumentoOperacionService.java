package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elhidaja.apiselhidaja.persistence.repository.DocumentoOperacionRepository;
import com.elhidaja.apiselhidaja.presentation.dto.documentoOperacion.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.documentoOperacion.Response.*;
@Service
public class DocumentoOperacionService {
    private final DocumentoOperacionRepository docOpRepo;

    public DocumentoOperacionService(DocumentoOperacionRepository docOpRepo) {
        this.docOpRepo = docOpRepo;
    }

    @Transactional
    public ResponseDocumentoOperacionMensajeDTO insertSer(RequestDocumentoOperacionInsertDTO objDocumentoOperacion) {
        return docOpRepo.insertD(objDocumentoOperacion);
    }

    @Transactional
    public ResponseDocumentoOperacionMensajeDTO updateSer(RequestDocumentoOperacionUpdateDTO objDocumentoOperacion) {
        return docOpRepo.updateD(objDocumentoOperacion);
    }

    @Transactional(readOnly = true)
    public ResponseDocumentoOperacionAllDTO getAllSer(RequestDocumentoOperacionOptionDTO option) {
        return docOpRepo.getAllD(option);
    }

    @Transactional(readOnly = true)
    public ResponseDetalleDocumentoOperacionDTO getByIdSer(RequestDocumentoOperacionFilterDTO id) {
        return docOpRepo.getByIdD(id);
    }

    @Transactional
    public ResponseDocumentoOperacionMensajeDTO activateSer(RequestDocumentoOperacionIdDTO id) {
        return docOpRepo.activateD(id);
    }

    @Transactional
    public ResponseDocumentoOperacionMensajeDTO desactivateSer(RequestDocumentoOperacionIdDTO id) {
        return docOpRepo.desactivateD(id);
    }
}
