package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.maestroobra.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.maestroobra.Response.*;

public interface MaestroObraDAO {
    public ResponseMaestroObraAllDTO getAllD(RequestMaestroObraOptionDTO option);
    public ResponseDetalleMaestroObraDTO getByIdD(RequestMaestroObraFilterDTO id);
    public ResponseMaestroObraMensajeDTO desactivateD(RequestMaestroObraIdDTO id);
    public ResponseMaestroObraMensajeDTO activateD(RequestMaestroObraIdDTO id);
    public ResponseMaestroObraMensajeDTO insertD(RequestMaestroObraInsertDTO obj);
    public ResponseMaestroObraMensajeDTO updateD(RequestMaestroObraUpdateDTO obj);
}
