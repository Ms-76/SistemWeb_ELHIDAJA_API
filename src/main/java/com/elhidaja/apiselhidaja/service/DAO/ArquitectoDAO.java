package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.arquitecto.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.arquitecto.Response.*;

public interface ArquitectoDAO {
    ResponseArquitectoAllDTO getAllD(RequestArquitectoOptionDTO option);

    ResponseDetalleArquitectoDTO getByIdD(RequestArquitectoFilterDTO id);

    ResponseArquitectoMensajeDTO desactivateD(RequestArquitectoIdDTO id);

    ResponseArquitectoMensajeDTO activateD(RequestArquitectoIdDTO id);

    ResponseArquitectoMensajeDTO insertD(RequestArquitectoInsertDTO obj);

    ResponseArquitectoMensajeDTO updateD(RequestArquitectoUpdateDTO obj);
}
