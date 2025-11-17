package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.maestroobra.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.maestroobra.Response.*;

public interface MaestroObraDAO {
    ResponseMaestroObraAllDTO getAllD(RequestMaestroObraOptionDTO option);

    ResponseDetalleMaestroObraDTO getByIdD(RequestMaestroObraFilterDTO id);

    ResponseMaestroObraMensajeDTO desactivateD(RequestMaestroObraIdDTO id);

    ResponseMaestroObraMensajeDTO activateD(RequestMaestroObraIdDTO id);

    ResponseMaestroObraMensajeDTO insertD(RequestMaestroObraInsertDTO obj);

    ResponseMaestroObraMensajeDTO updateD(RequestMaestroObraUpdateDTO obj);
}
