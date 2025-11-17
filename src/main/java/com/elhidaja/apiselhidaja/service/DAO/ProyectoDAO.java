package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.proyecto.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.proyecto.Response.*;

public interface ProyectoDAO {

    ResponseProyectoAllDTO getAllD(RequestProyectoOptionDTO option);

    ResponseDetalleProyectoDTO getByIdD(RequestProyectoFilterDTO id);

    ResponseProyectoMensajeDTO desactivateD(RequestProyectoIdDTO id);

    ResponseProyectoMensajeDTO activateD(RequestProyectoIdDTO id);

    ResponseProyectoMensajeDTO insertD(RequestProyectoInsertDTO objProyecto);

    ResponseProyectoMensajeDTO updateD(RequestProyectoUpdateDTO objProyecto);
}
