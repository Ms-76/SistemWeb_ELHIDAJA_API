package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.subCategoria.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.subCategoria.Resquest.*;

public interface SubCategoriaDAO {
     ResponseSubCategoriAllDTO getAllD(ResquestSubCategoriaOptionDTO option);

    ResponseDetalleSubCategoriaDTO getByIdD(RequestSubCategoriaIdDTO id);

    ResponserSubCategoriaMensajeDTO insertD(RequestSubCategoriaInsertDTO dto);

    ResponserSubCategoriaMensajeDTO updateD(RequestSubCategoriaUpdateDTO dto);

    ResponserSubCategoriaMensajeDTO activateD(RequestSubCategoriaIdDTO id);

    ResponserSubCategoriaMensajeDTO desactivateD(RequestSubCategoriaIdDTO id);
}
