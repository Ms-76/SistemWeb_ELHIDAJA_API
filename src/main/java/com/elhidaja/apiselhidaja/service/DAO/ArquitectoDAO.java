package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.arquitecto.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.arquitecto.Response.*;

public interface ArquitectoDAO {
    public ResponseArquitectoAllDTO getAllD(RequestArquitectoOptionDTO option);
    public ResponseDetalleArquitectoDTO getByIdD(RequestArquitectoFilterDTO id);
    public ResponseArquitectoMensajeDTO desactivateD(RequestArquitectoIdDTO id);
    public ResponseArquitectoMensajeDTO activateD(RequestArquitectoIdDTO id);
    public ResponseArquitectoMensajeDTO insertD(RequestArquitectoInsertDTO obj);
    public ResponseArquitectoMensajeDTO updateD(RequestArquitectoUpdateDTO obj);
}
