package com.elhidaja.apiselhidaja.service.DAO;
import com.elhidaja.apiselhidaja.presentation.dto.puesto.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.puesto.Response.*;
public interface PuestoDAO {
    public ResponsePuestoAllDTO getAllD(RequestPuestoOptionDTO option);

    public ResponseDetallePuestoDTO getByIdD(RequestPuestoFilterDTO id);

    public ResponsePuestoMensajeDTO desactivateD(RequestPuestoIdDTO id);

    public ResponsePuestoMensajeDTO activateD(RequestPuestoIdDTO id);

    public ResponsePuestoMensajeDTO insertD(RequestPuestoInsertDTO objPuesto);

    public ResponsePuestoMensajeDTO updateD(RequestPuestoUpdateDTO objPuesto);
}
