package com.elhidaja.apiselhidaja.presentation.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elhidaja.apiselhidaja.presentation.dto.categoria.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.categoria.Resquest.*;
import com.elhidaja.apiselhidaja.service.implementation.CategoriaService;

import jakarta.validation.Valid;
/**
 * @class CategoriaController
 * @brief Controlador REST para operaciones sobre categorías.
 * 
 * Proporciona endpoints para crear, actualizar, activar, desactivar y obtener categorías.
 */
@RestController
@RequestMapping("/api/categorias")
@Validated
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;
    /**
     * @brief Obtiene todas las categorías según las opciones proporcionadas.
     * @param option DTO con filtros y opciones de consulta.
     * @return ResponseEntity conteniendo ResponseCategoriAllDTO con la lista de categorías.
     */
    @PostMapping("/getall")
    public ResponseEntity<ResponseCategoriAllDTO> getCategorias(
            @Valid @RequestBody ResquestCategoriaOptionDTO option) {
        return ResponseEntity.ok(categoriaService.getAllSer(option));
    }
    /**
     * @brief Obtiene los detalles de una categoría por su ID.
     * @param id DTO que contiene el ID de la categoría a buscar.
     * @return ResponseEntity conteniendo ResponseDetalleCategoriaDTO con los detalles de la categoría.
     */
    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleCategoriaDTO> getByIdCategoria(
            @Valid @RequestBody RequestCategoriaFilterDTO id) {
        return ResponseEntity.ok(categoriaService.getByIdSer(id));
    }
    /**
     * @brief Inserta una nueva categoría.
     * @param dto DTO con los datos de la nueva categoría.
     * @return ResponseEntity conteniendo ResponserCategoriaMensajeDTO con el resultado de la operación.
     */
    @PostMapping("/create")
    public ResponseEntity<ResponserCategoriaMensajeDTO> insertarCategoria(
            @Valid @RequestBody RequestCategoriaInsertDTO dto) {
        return ResponseEntity.ok(categoriaService.insertSer(dto));
    }
    /**
     * @brief Actualiza una categoría existente.
     * @param dto DTO con los datos actualizados de la categoría.
     * @return ResponseEntity conteniendo ResponserCategoriaMensajeDTO con el resultado de la operación.
     */
    @PutMapping("/update")
    public ResponseEntity<ResponserCategoriaMensajeDTO> actualizarCategoria(
            @Valid @RequestBody RequestCategoriaUpdateDTO dto) {
        return ResponseEntity.ok(categoriaService.updateSer(dto));
    }
    /**
     * @brief Activa una categoría por su ID.
     * @param id DTO que contiene el ID de la categoría a activar.
     * @return ResponseEntity conteniendo ResponserCategoriaMensajeDTO con el resultado de la operación.
     */
    @PutMapping("/activate")
    public ResponseEntity<ResponserCategoriaMensajeDTO> activarCategoria(
            @Valid @RequestBody RequesteCategoriaIdDTO id) {
        return ResponseEntity.ok(categoriaService.activateSer(id));
    }
    /**
     * @brief Desactiva una categoría por su ID.
     * @param id DTO que contiene el ID de la categoría a desactivar.
     * @return ResponseEntity conteniendo ResponserCategoriaMensajeDTO con el resultado de la operación.
     */
    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponserCategoriaMensajeDTO> desactivarCategoria(
            @Valid @RequestBody RequesteCategoriaIdDTO id) {
        return ResponseEntity.ok(categoriaService.desactivateSer(id));
    }

}
