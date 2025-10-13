package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import com.elhidaja.apiselhidaja.persistence.repository.DepartamentoRepository;
import com.elhidaja.apiselhidaja.presentation.dto.departamento.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.departamento.Response.*;
@Service
@Validated
public class DepartamentoService {
        private final DepartamentoRepository deptoRepo;

    public DepartamentoService(DepartamentoRepository deptoRepo) {
        this.deptoRepo = deptoRepo;
    }

    @Transactional
    public ResponseDepartamentoMensajeDTO insertSer(RequestDepartamentoInsertDTO objDepartamento) {
        return deptoRepo.insert(objDepartamento);
    }

    @Transactional
    public ResponseDepartamentoMensajeDTO updateSer(RequestDepartamentoUpdateDTO objDepartamento) {
        return deptoRepo.update(objDepartamento);
    }

    @Transactional(readOnly = true)
    public ResponseDepartamentoAllDTO getAllSer(RequestDepartamentoOptionDTO option) {
        return deptoRepo.getAll(option);
    }

    @Transactional(readOnly = true)
    public ResponseDetalleDepartamentoDTO getByIdSer(RequestDepartamentoIdDTO id) {
        return deptoRepo.getById(id);
    }

    @Transactional
    public ResponseDepartamentoMensajeDTO activateSer(RequestDepartamentoIdDTO id) {
        return deptoRepo.activate(id);
    }

    @Transactional
    public ResponseDepartamentoMensajeDTO desactivateSer(RequestDepartamentoIdDTO id) {
        return deptoRepo.desactivate(id);
    }
}
