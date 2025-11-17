package com.elhidaja.apiselhidaja.presentation.dto.inventario.Request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import com.elhidaja.apiselhidaja.presentation.dto.detalleInventario.Request.RequestDetalleInventarioInsertDTO;
import com.elhidaja.apiselhidaja.util.validationsPersonalisate.LengthSQL;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RequestInventarioInsertDTO {

    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotNull(message = "La fecha del inicio inventario no puede estar vacía")
    @FutureOrPresent(message = "La fecha del inicio inventario no puede ser anterior a hoy")
    private LocalDate fechaInicioInventario;

    @NotBlank(message = "La descripción es obligatoria")
    @LengthSQL(tabla = "inventario", columna = "descripcion")
    private String descripcion;

    @NotNull(message = "El id del almacén es obligatorio")
    @Min(value = 1, message = "El id del almacén debe ser mayor o igual a 1")
    private Long idAlmacen;

    @NotNull(message = "El del usuario que hara el inventario es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idinventariador;

    @NotNull(message = "La fecha de finalización del inventario no puede estar vacía")
    @FutureOrPresent(message = "La fecha de finalización no puede ser anterior a hoy")
    private LocalDate fechaFinInventario;

    @NotEmpty(message = "Debe incluir al menos un detalle")
    private List<RequestDetalleInventarioInsertDTO> detalles;

}
