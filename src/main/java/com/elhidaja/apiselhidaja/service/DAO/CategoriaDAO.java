package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.categoria.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.categoria.Resquest.*;

public interface CategoriaDAO {

    public ResponseCategoriAllDTO getAllD(ResquestCategoriaOptionDTO option);

    public ResponseDetalleCategoriaDTO getByIdD(RequestCategoriaFilterDTO id);

    public ResponserCategoriaMensajeDTO desactivateD(RequesteCategoriaIdDTO id);

    public ResponserCategoriaMensajeDTO activateD(RequesteCategoriaIdDTO id);

    public ResponserCategoriaMensajeDTO insertD(RequestCategoriaInsertDTO objCategoria);

    public ResponserCategoriaMensajeDTO updateD(RequestCategoriaUpdateDTO objCategoria);
}
