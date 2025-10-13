package com.elhidaja.apiselhidaja.service.DAO;
import com.elhidaja.apiselhidaja.presentation.dto.serie.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.serie.Response.*;

public interface SerieDAO {
    public ResponseSerieAllDTO getAllD(RequestSerieOptionDTO option);

    public ResponseDetalleSerieDTO getByIdD(RequestSerieIdDTO id);

    public ResponseSerieMensajeDTO deactivateD(RequestSerieIdDTO id);

    public ResponseSerieMensajeDTO activateD(RequestSerieIdDTO id);

    public ResponseSerieMensajeDTO insertD(RequestSerieInsertDTO objSerie);

    public ResponseSerieMensajeDTO updateD(RequestSerieUpdateDTO objSerie);
}
