package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.tipoVehiculo.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.tipoVehiculo.Response.*;

public interface TipoVehiculoDAO {

    ResponseTipoVehiculoAllDTO getAllD(RequestTipoVehiculoOptionDTO option);

    ResponseDetalleTipoVehiculoDTO getByIdD(RequestTipoVehiculoFilterDTO id);

    ResponseTipoVehiculoMensajeDTO desactivateD(RequestTipoVehiculoIdDTO id);

    ResponseTipoVehiculoMensajeDTO activateD(RequestTipoVehiculoIdDTO id);

    ResponseTipoVehiculoMensajeDTO insertD(RequestTipoVehiculoInsertDTO objTipoVehiculo);

    ResponseTipoVehiculoMensajeDTO updateD(RequestTipoVehiculoUpdateDTO objTipoVehiculo);
}
