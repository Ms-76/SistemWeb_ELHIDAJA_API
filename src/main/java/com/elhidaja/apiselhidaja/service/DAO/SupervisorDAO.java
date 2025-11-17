package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.supervisor.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.supervisor.Response.*;

public interface SupervisorDAO {
    ResponseSupervisorAllDTO getAllD(RequestSupervisorOptionDTO option);

    ResponseDetalleSupervisorDTO getByIdD(RequestSupervisorFilterDTO id);

    ResponseSupervisorMensajeDTO desactivateD(RequestSupervisorIdDTO id);

    ResponseSupervisorMensajeDTO activateD(RequestSupervisorIdDTO id);

    ResponseSupervisorMensajeDTO insertD(RequestSupervisorInsertDTO objSupervisor);

}
