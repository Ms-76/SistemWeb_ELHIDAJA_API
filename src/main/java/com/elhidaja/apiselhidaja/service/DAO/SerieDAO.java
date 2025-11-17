package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.serie.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.serie.Response.*;

public interface SerieDAO {
    ResponseSerieAllDTO getAllD(RequestSerieOptionDTO option);

    ResponseDetalleSerieDTO getByIdD(RequestSerieFilterDTO id);

    ResponseSerieMensajeDTO deactivateD(RequestSerieIdDTO id);

    ResponseSerieMensajeDTO activateD(RequestSerieIdDTO id);

    ResponseSerieMensajeDTO insertD(RequestSerieInsertDTO objSerie);

    ResponseSerieMensajeDTO updateD(RequestSerieUpdateDTO objSerie);
}
