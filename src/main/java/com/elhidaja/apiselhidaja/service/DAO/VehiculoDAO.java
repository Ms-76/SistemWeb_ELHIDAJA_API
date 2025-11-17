package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.vehiculo.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.vehiculo.Response.*;

public interface VehiculoDAO {
    ResponseVehiculoAllDTO getAllD(RequestVehiculoOptionDTO option);

    ResponseDetalleVehiculoDTO getByIdD(RequestVehiculoFilterDTO id);

    ResponseVehiculoMensajeDTO desactivateD(RequestVehiculoIdDTO id);

    ResponseVehiculoMensajeDTO activateD(RequestVehiculoIdDTO id);

    ResponseVehiculoMensajeDTO insertD(RequestVehiculoInsertDTO objVehiculo);

    ResponseVehiculoMensajeDTO updateD(RequestVehiculoUpdateDTO objVehiculo);
}
