package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elhidaja.apiselhidaja.persistence.repository.DocumentoIdentidadRepository;
import com.elhidaja.apiselhidaja.presentation.dto.documentoIdentidad.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.documentoIdentidad.Request.*;
@Service
public class DocumentoIdentidadService {
  
    private final DocumentoIdentidadRepository docRepo;

    public DocumentoIdentidadService(DocumentoIdentidadRepository docRepo) {
        this.docRepo = docRepo;
    }

    @Transactional
    public ResponseDocumentoIdentidadMensajeDTO insertSer(RequestDocumentoIdentidadInsertDTO obj) {
        return docRepo.insertD(obj);
    }

    @Transactional
    public ResponseDocumentoIdentidadMensajeDTO updateSer(RequestDocumentoIdentidadUpdateDTO obj) {
        return docRepo.updateD(obj);
    }

    @Transactional(readOnly = true)
    public ResponseDocumentoIdentidadAllDTO getAllSer(RequestDocumentoIdentidadOptionDTO option) {
        return docRepo.getAllD(option);
    }

    @Transactional(readOnly = true)
    public ResponseDetalleDocumentoIdentidadDTO getByIdSer(RequestDocumentoIdentidadFilterDTO id) {
        return docRepo.getByIdD(id);
    }

    @Transactional
    public ResponseDocumentoIdentidadMensajeDTO activateSer(RequestDocumentoIdentidadIdDTO id) {
        return docRepo.activateD(id);
    }

    @Transactional
    public ResponseDocumentoIdentidadMensajeDTO desactivateSer(RequestDocumentoIdentidadIdDTO id) {
        return docRepo.desactivateD(id);
    }  
}
