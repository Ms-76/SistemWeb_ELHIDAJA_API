package com.elhidaja.apiselhidaja.presentation.dto.ubicacion.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;
import lombok.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestUbicacionOptionDTO {
    @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado;

    @NotNull(message = "El id del almacen es obligatorio")
    @Min(value = 0, message = "El id del almacen debe ser mayor o igual a 0")
    private Long idAlmacen;

    @NotNull(message = "El id de la categoría es obligatorio")
    @Min(value = 0, message = "El id de la categoría debe ser mayor o igual a 0")
    private Integer idCategoria;

    @NotNull(message = "El id de la subcategoría es obligatorio")
    @Min(value = 0, message = "El id de la subcategoría debe ser mayor o igual a 0")
    private Integer idSubcategoria;

    @NotNull(message = "El id de la unidad_medida es obligatorio")
    @Min(value = 0, message = "El id de la unidad_medida debe ser mayor o igual a 0")
    private Integer idUnidadMedida;

    @NotNull(message = "El id del estante es obligatorio")
    @Min(value = 0, message = "El id del estante debe ser mayor o igual a 0")
    private Long idEstante;

    @NotNull(message = "El id del pallet es obligatorio")
    @Min(value = 0, message = "El id del pallet debe ser mayor o igual a 0")
    private Long idPallet;
}
