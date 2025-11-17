package com.elhidaja.apiselhidaja.presentation.dto.inventario.Request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestInventarioUpdateDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotNull(message = "El id del inventario es obligatorio")
    @Min(value = 1, message = "El id del inventario debe ser mayor a 0")
    private Long id;

    @NotNull(message = "La fecha del inicio inventario no puede estar vacía")
    @FutureOrPresent(message = "La fecha del inicio inventario no puede ser anterior a hoy")
    private LocalDate fechaInicioInventario;

    @NotNull(message = "La fecha de finalización del inventario no puede estar vacía")
    @FutureOrPresent(message = "La fecha de finalización no puede ser anterior a hoy")
    private LocalDate fechaFinInventario;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 255, message = "La descripción no puede tener más de 255 caracteres")
    private String descripcion;

    @NotNull(message = "El del usuario que hara el inventario es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idinventariador;
}
