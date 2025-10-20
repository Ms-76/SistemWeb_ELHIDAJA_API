package com.elhidaja.apiselhidaja.service.DAO;
import com.elhidaja.apiselhidaja.presentation.dto.nivelAcademico.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.nivelAcademico.Response.*;
public interface NivelAcademicoDAO {
    public ResponseNivelAcademicoAllDTO getAllD(RequestNivelAcademicoOptionDTO option);

    public ResponseDetalleNivelAcademicoDTO getByIdD(RequestNivelAcademicoFilterDTO id);

    public ResponseNivelAcademicoMensajeDTO desactivateD(RequestNivelAcademicoIdDTO id);

    public ResponseNivelAcademicoMensajeDTO activateD(RequestNivelAcademicoIdDTO id);

    public ResponseNivelAcademicoMensajeDTO insertD(RequestNivelAcademicoInsertDTO objNivel);

    public ResponseNivelAcademicoMensajeDTO updateD(RequestNivelAcademicoUpdateDTO objNivel);
}
