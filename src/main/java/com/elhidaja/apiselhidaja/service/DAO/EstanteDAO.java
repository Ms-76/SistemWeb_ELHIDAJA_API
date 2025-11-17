package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.estante.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.estante.Request.*;

public interface EstanteDAO {
    ResponseEstanteAllDTO getAllD(RequestEstanteOptionDTO option);

    ResponseDetalleEstanteDTO getByIdD(RequestEstanteFilterDTO id);

    ResponseEstanteMensajeDTO desactivateD(RequestEstanteIdDTO id);

    ResponseEstanteMensajeDTO activateD(RequestEstanteIdDTO id);

    ResponseEstanteMensajeDTO insertD(RequestEstanteInsertDTO objEstante);

    ResponseEstanteMensajeDTO updateD(RequestEstanteUpdateDTO objEstante);
}
