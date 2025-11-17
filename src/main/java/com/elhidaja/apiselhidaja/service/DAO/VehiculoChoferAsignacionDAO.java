package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.vehiculoChoferAsignacion.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.vehiculoChoferAsignacion.Response.*;

public interface VehiculoChoferAsignacionDAO {
    ResponseVehiculoChoferAsignacionAllDTO getAllD(RequestVehiculoChoferAsignacionOptionDTO option);

    ResponseDetalleVehiculoChoferAsignacionDTO getByIdD(RequestVehiculoChoferAsignacionFilterDTO id);

    ResponseVehiculoChoferAsignacionMensajeDTO desactivateD(RequestVehiculoChoferAsignacionIdDTO id);

    ResponseVehiculoChoferAsignacionMensajeDTO activateD(RequestVehiculoChoferAsignacionIdDTO id);

    ResponseVehiculoChoferAsignacionMensajeDTO insertD(RequestVehiculoChoferAsignacionInsertDTO objAsignacion);

    ResponseVehiculoChoferAsignacionMensajeDTO updateD(RequestVehiculoChoferAsignacionUpdateDTO objAsignacion);
}
