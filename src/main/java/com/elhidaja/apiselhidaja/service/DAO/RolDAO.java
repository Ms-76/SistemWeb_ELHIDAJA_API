package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.rol.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.rol.Response.*;
public interface RolDAO {
    public ResponseRolAllDTO getAllD(RequestRolOptionDTO option);

    public ResponseDetalleRolDTO getByIdD(RequestRolIdDTO id);

    public ResponseRolMensajeDTO deactivateD(RequestRolIdDTO id);

    public ResponseRolMensajeDTO activateD(RequestRolIdDTO id);

    public ResponseRolMensajeDTO insertD(RequestRolInsertDTO objRol);

    public ResponseRolMensajeDTO updateD(RequestRolUpdateDTO objRol);
}
