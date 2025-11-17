package com.elhidaja.apiselhidaja.service.DAO;

import com.elhidaja.apiselhidaja.presentation.dto.categoria.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.categoria.Resquest.*;
/**
 * @interface CategoriaDAO
 * @brief Interfaz para operaciones CRUD sobre los dtos de Categoria.
 */
public interface CategoriaDAO {
    /**
     * @brief Obtiene todas las categorías según las opciones dadas.
     * @param option Objeto con filtros y opciones de consulta.
     * @return ResponseCategoriAllDTO con la lista de categorías.
     */
    ResponseCategoriAllDTO getAllD(ResquestCategoriaOptionDTO option);
    /**
     * @brief Obtiene los detalles de una categoría por su ID.
     * @param id DTO que contiene el ID de la categoría a buscar.
     * @return ResponseDetalleCategoriaDTO con los detalles de la categoría.
     */
    ResponseDetalleCategoriaDTO getByIdD(RequestCategoriaFilterDTO id);
    /**
     * @brief Desactiva una categoría por su ID.
     * @param id DTO que contiene el ID de la categoría a desactivar.
     * @return ResponserCategoriaMensajeDTO con el resultado de la operación.
     */
    ResponserCategoriaMensajeDTO desactivateD(RequesteCategoriaIdDTO id);
    /**
     * @brief Activa una categoría por su ID.
     * @param id DTO que contiene el ID de la categoría a activar.
     * @return ResponserCategoriaMensajeDTO con el resultado de la operación.
     */
    ResponserCategoriaMensajeDTO activateD(RequesteCategoriaIdDTO id);
    /**
     * @brief Inserta una nueva categoría en el sistema.
     * @param objCategoria DTO con los datos de la nueva categoría.
     * @return ResponserCategoriaMensajeDTO con el resultado de la operación.
     */
    ResponserCategoriaMensajeDTO insertD(RequestCategoriaInsertDTO objCategoria);
    /**
     * @brief Actualiza una categoría existente.
     * @param objCategoria DTO con los datos actualizados de la categoría.
     * @return ResponserCategoriaMensajeDTO con el resultado de la operación.
     */
    ResponserCategoriaMensajeDTO updateD(RequestCategoriaUpdateDTO objCategoria);
}
