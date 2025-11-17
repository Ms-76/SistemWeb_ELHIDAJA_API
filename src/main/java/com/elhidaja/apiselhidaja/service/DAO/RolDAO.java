package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.rol.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.rol.Response.*;

public interface RolDAO {
    ResponseRolAllDTO getAllD(RequestRolOptionDTO option);

    ResponseDetalleRolDTO getByIdD(RequestRolFilterDTO id);

    ResponseRolMensajeDTO deactivateD(RequestRolIdDTO id);

    ResponseRolMensajeDTO activateD(RequestRolIdDTO id);

    ResponseRolMensajeDTO insertD(RequestRolInsertDTO objRol);

    ResponseRolMensajeDTO updateD(RequestRolUpdateDTO objRol);
}
