package com.elhidaja.apiselhidaja.service.implementation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.elhidaja.apiselhidaja.persistence.repository.SupervisorRepository;
import com.elhidaja.apiselhidaja.presentation.dto.supervisor.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.supervisor.Response.*;

@Service
@Validated
public class SupervisorService {
        private final SupervisorRepository supervisorRepo;

    public SupervisorService(SupervisorRepository supervisorRepo) {
        this.supervisorRepo = supervisorRepo;
    }

    @Transactional
    public ResponseSupervisorMensajeDTO insertSer(RequestSupervisorInsertDTO objSupervisor) {
        return supervisorRepo.insertD(objSupervisor);
    }

    @Transactional
    public ResponseSupervisorAllDTO getAllSer(RequestSupervisorOptionDTO option) {
        return supervisorRepo.getAllD(option);
    }

    @Transactional
    public ResponseDetalleSupervisorDTO getByIdSer(RequestSupervisorFilterDTO id) {
        return supervisorRepo.getByIdD(id);
    }

    @Transactional
    public ResponseSupervisorMensajeDTO activateSer(RequestSupervisorIdDTO id) {
        return supervisorRepo.activateD(id);
    }

    @Transactional
    public ResponseSupervisorMensajeDTO desactivateSer(RequestSupervisorIdDTO id) {
        return supervisorRepo.desactivateD(id);
    }
}
