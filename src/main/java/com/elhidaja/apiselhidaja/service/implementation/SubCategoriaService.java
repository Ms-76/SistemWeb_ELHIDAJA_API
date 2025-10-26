package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elhidaja.apiselhidaja.persistence.repository.SubCategoriaRepository;
import com.elhidaja.apiselhidaja.presentation.dto.subCategoria.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.subCategoria.Resquest.*;

@Service
public class SubCategoriaService {
      private final SubCategoriaRepository subCatRepo;

    public SubCategoriaService(SubCategoriaRepository subCatRepo) {
        this.subCatRepo = subCatRepo;
    }

    @Transactional
    public ResponserSubCategoriaMensajeDTO insertSer(RequestSubCategoriaInsertDTO dto) {
        return subCatRepo.insertD(dto);
    }

    @Transactional
    public ResponserSubCategoriaMensajeDTO updateSer(RequestSubCategoriaUpdateDTO dto) {
        return subCatRepo.updateD(dto);
    }

    @Transactional
    public ResponseSubCategoriAllDTO getAllSer(ResquestSubCategoriaOptionDTO option) {
        return subCatRepo.getAllD(option);
    }

    @Transactional
    public ResponseDetalleSubCategoriaDTO getByIdSer(RequestSubCategoriaFilterDTO id) {
        return subCatRepo.getByIdD(id);
    }

    @Transactional
    public ResponserSubCategoriaMensajeDTO activateSer(RequestSubCategoriaIdDTO id) {
        return subCatRepo.activateD(id);
    }

    @Transactional
    public ResponserSubCategoriaMensajeDTO desactivateSer(RequestSubCategoriaIdDTO id) {
        return subCatRepo.desactivateD(id);
    }
}
