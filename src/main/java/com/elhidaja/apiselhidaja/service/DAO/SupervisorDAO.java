package com.elhidaja.apiselhidaja.service.DAO;
import com.elhidaja.apiselhidaja.presentation.dto.supervisor.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.supervisor.Response.*;
public interface SupervisorDAO {
    public ResponseSupervisorAllDTO getAllD(RequestSupervisorOptionDTO option);

    public ResponseDetalleSupervisorDTO getByIdD(RequestSupervisorFilterDTO id);

    public ResponseSupervisorMensajeDTO desactivateD(RequestSupervisorIdDTO id);

    public ResponseSupervisorMensajeDTO activateD(RequestSupervisorIdDTO id);

    public ResponseSupervisorMensajeDTO insertD(RequestSupervisorInsertDTO objSupervisor);

}
