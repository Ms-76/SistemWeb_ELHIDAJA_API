package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.usuario.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.usuario.Response.*;

public interface UsuarioDAO {
    ResponseUsuarioAllDTO getAllD(RequestUsuarioOptionDTO option);

    ResponseDetalleUsuarioDTO getByIdD(RequestUsuarioFilterDTO id);

    ResponseUsuarioMensajeDTO desactivateD(RequestUsuarioIdDTO id);

    ResponseUsuarioMensajeDTO activateD(RequestUsuarioIdDTO id);

    ResponseUsuarioMensajeDTO insertD(RequestUsuarioInsertDTO objUsuario);

    ResponseUsuarioMensajeDTO updateD(RequestUsuarioUpdateDTO objUsuario);
}
