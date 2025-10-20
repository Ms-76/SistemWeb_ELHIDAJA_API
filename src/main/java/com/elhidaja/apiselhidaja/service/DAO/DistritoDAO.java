package com.elhidaja.apiselhidaja.service.DAO;
import com.elhidaja.apiselhidaja.presentation.dto.distrito.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.distrito.Request.*;

public interface DistritoDAO {
     public ResponseDistritoAllDTO getAllD(RequestDistritoOptionDTO option);

    public ResponseDetalleDistritoDTO getByIdD(RequestDistritoFilterDTO id);

    public ResponseDistritoMensajeDTO desactivateD(RequestDistritoIdDTO id);

    public ResponseDistritoMensajeDTO activateD(RequestDistritoIdDTO id);

    public ResponseDistritoMensajeDTO insertD(RequestDistritoInsertDTO objDistrito);

    public ResponseDistritoMensajeDTO updateD(RequestDistritoUpdateDTO objDistrito);
}
