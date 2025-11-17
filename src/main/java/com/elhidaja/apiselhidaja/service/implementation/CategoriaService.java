package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elhidaja.apiselhidaja.persistence.repository.CategoriaRepository;
import com.elhidaja.apiselhidaja.presentation.dto.categoria.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.categoria.Resquest.*;
import com.elhidaja.apiselhidaja.service.implementation.CategoriaService;

/**
 * @class CategoriaService
 * @brief Servicio para operaciones de negocio relacionadas con categorías.
 * 
 * Este servicio actúa como intermediario entre el controlador y el repositorio, 
 * manejando la lógica de transacciones y llamadas CRUD sobre las categorías.
 */
@Service
public class CategoriaService {

    private final CategoriaRepository catRepo;
    /**
     * @brief Constructor del servicio que inyecta el repositorio de categorías.
     * @param catRepo Repositorio para acceso a datos de categorías.
     */
    public CategoriaService(CategoriaRepository catRepo) {
        this.catRepo = catRepo;
    }
    /**
     * @brief Inserta una nueva categoría en el sistema.
     * @param objCategoria DTO con los datos de la categoría a insertar.
     * @return ResponserCategoriaMensajeDTO con el resultado de la operación.
     */
    @Transactional
    public ResponserCategoriaMensajeDTO insertSer(RequestCategoriaInsertDTO objCategoria) {
        return catRepo.insertD(objCategoria);
    }
    /**
     * @brief Actualiza una categoría existente.
     * @param objCategoria DTO con los datos de la categoría a actualizar.
     * @return ResponserCategoriaMensajeDTO con el resultado de la operación.
     */
    @Transactional
    public ResponserCategoriaMensajeDTO updateSer(RequestCategoriaUpdateDTO objCategoria) {
        return catRepo.updateD(objCategoria);
    }
    /**
     * @brief Obtiene todas las categorías según las opciones proporcionadas.
     * @param option DTO con filtros y opciones de consulta.
     * @return ResponseCategoriAllDTO con la lista de categorías.
     */
    @Transactional(readOnly = true)
    public ResponseCategoriAllDTO getAllSer(ResquestCategoriaOptionDTO option) {
        return catRepo.getAllD(option);
    }
    /**
     * @brief Obtiene los detalles de una categoría por su ID.
     * @param id DTO que contiene el ID de la categoría a buscar.
     * @return ResponseDetalleCategoriaDTO con los detalles de la categoría.
     */
    @Transactional(readOnly = true)
    public ResponseDetalleCategoriaDTO getByIdSer(RequestCategoriaFilterDTO id) {
       
        return catRepo.getByIdD(id);
    }
    /**
     * @brief Activa una categoría por su ID.
     * @param id DTO que contiene el ID de la categoría a activar.
     * @return ResponserCategoriaMensajeDTO con el resultado de la operación.
     */
    @Transactional
    public ResponserCategoriaMensajeDTO activateSer(RequesteCategoriaIdDTO id) {
        return catRepo.activateD(id);
    }
    /**
     * @brief Desactiva una categoría por su ID.
     * @param id DTO que contiene el ID de la categoría a desactivar.
     * @return ResponserCategoriaMensajeDTO con el resultado de la operación.
     */
    @Transactional
    public ResponserCategoriaMensajeDTO desactivateSer(RequesteCategoriaIdDTO id) {
        return catRepo.desactivateD(id);
    }
}
