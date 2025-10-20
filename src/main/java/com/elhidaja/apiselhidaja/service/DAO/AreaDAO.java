package com.elhidaja.apiselhidaja.service.DAO;
import com.elhidaja.apiselhidaja.presentation.dto.area.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.area.Request.*;
public interface  AreaDAO {
    
    public ResponseAreaAllDTO getAllD(RequestAreaOptionDTO option);

    public ResponseDetalleAreaDTO getByIdD(RequestAreaFilterDTO id);

    public ResponseAreaMensajeDTO desactivateD(RequestAreaIdDTO id);

    public ResponseAreaMensajeDTO activateD(RequestAreaIdDTO id);

    public ResponseAreaMensajeDTO insertD(RequestAreaInsertDTO objArea);

    public ResponseAreaMensajeDTO updateD(RequestAreaUpdateDTO objArea);
}
