package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.proyecto.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.proyecto.Response.*;
public interface ProyectoDAO {
    
    public ResponseProyectoAllDTO getAllD(RequestProyectoOptionDTO option);

    public ResponseDetalleProyectoDTO getByIdD(RequestProyectoFilterDTO id);

    public ResponseProyectoMensajeDTO desactivateD(RequestProyectoIdDTO id);

    public ResponseProyectoMensajeDTO activateD(RequestProyectoIdDTO id);

    public ResponseProyectoMensajeDTO insertD(RequestProyectoInsertDTO objProyecto);

    public ResponseProyectoMensajeDTO updateD(RequestProyectoUpdateDTO objProyecto);
}
