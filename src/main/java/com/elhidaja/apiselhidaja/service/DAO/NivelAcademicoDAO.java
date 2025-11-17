package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.nivelAcademico.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.nivelAcademico.Response.*;

public interface NivelAcademicoDAO {
    ResponseNivelAcademicoAllDTO getAllD(RequestNivelAcademicoOptionDTO option);

    ResponseDetalleNivelAcademicoDTO getByIdD(RequestNivelAcademicoFilterDTO id);

    ResponseNivelAcademicoMensajeDTO desactivateD(RequestNivelAcademicoIdDTO id);

    ResponseNivelAcademicoMensajeDTO activateD(RequestNivelAcademicoIdDTO id);

    ResponseNivelAcademicoMensajeDTO insertD(RequestNivelAcademicoInsertDTO objNivel);

    ResponseNivelAcademicoMensajeDTO updateD(RequestNivelAcademicoUpdateDTO objNivel);
}
