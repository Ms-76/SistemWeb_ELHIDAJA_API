package com.elhidaja.apiselhidaja.service.DAO;
import com.elhidaja.apiselhidaja.presentation.dto.usuario.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.usuario.Response.*;

public interface UsuarioDAO {
    public ResponseUsuarioAllDTO getAllD(RequestUsuarioOptionDTO option);

    public ResponseDetalleUsuarioDTO getByIdD(RequestUsuarioIdDTO id);

    public ResponseUsuarioMensajeDTO desactivateD(RequestUsuarioIdDTO id);

    public ResponseUsuarioMensajeDTO activateD(RequestUsuarioIdDTO id);

    public ResponseUsuarioMensajeDTO insertD(RequestUsuarioInsertDTO objUsuario);

    public ResponseUsuarioMensajeDTO updateD(RequestUsuarioUpdateDTO objUsuario);
}
