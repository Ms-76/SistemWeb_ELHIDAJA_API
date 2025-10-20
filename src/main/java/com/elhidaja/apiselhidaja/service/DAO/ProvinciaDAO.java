package com.elhidaja.apiselhidaja.service.DAO;
import com.elhidaja.apiselhidaja.presentation.dto.provincia.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.provincia.Request.*;
public interface ProvinciaDAO {
     public ResponseProvinciaAllDTO getAllD(RequestProvinciaOptionDTO option);

    public ResponseDetalleProvinciaDTO getByIdD(RequestProvinciaFilterDTO id);

    public ResponseProvinciaMensajeDTO desactivateD(RequestProvinciaIdDTO id);

    public ResponseProvinciaMensajeDTO activateD(RequestProvinciaIdDTO id);

    public ResponseProvinciaMensajeDTO insertD(RequestProvinciaInsertDTO objProvincia);

    public ResponseProvinciaMensajeDTO updateD(RequestProvinciaUpdateDTO objProvincia);
}
