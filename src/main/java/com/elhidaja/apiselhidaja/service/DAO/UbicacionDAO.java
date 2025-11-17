package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.ubicacion.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.ubicacion.Response.*;

public interface UbicacionDAO {
    ResponseUbicacionAllDTO getAllD(RequestUbicacionOptionDTO option);

    ResponseDetalleUbicacionDTO getByIdD(RequestUbicacionIdDTO id);

    ResponserUbicacionMensajeDTO desactivateD(RequestUbicacionIdDTO id);

    ResponserUbicacionMensajeDTO activateD(RequestUbicacionIdDTO id);

}
