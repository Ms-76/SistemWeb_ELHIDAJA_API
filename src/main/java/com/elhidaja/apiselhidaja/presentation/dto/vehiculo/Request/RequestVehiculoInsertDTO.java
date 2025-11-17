package com.elhidaja.apiselhidaja.presentation.dto.vehiculo.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.LengthSQL;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestVehiculoInsertDTO {

    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotBlank(message = "La placa no puede estar vacía")
    @LengthSQL(tabla = "vehiculo", columna = "placa")
    @Pattern(regexp = "^[A-Z0-9]+-[A-Z0-9]+$", message = "La placa debe tener formato ABC-123, letras mayúsculas y números, con un guion en medio")
    private String placa;

    @NotBlank(message = "El color no puede estar vacío")
    @LengthSQL(tabla = "vehiculo", columna = "color")
    private String color;

    @NotNull(message = "La cantidad de asientos es obligatoria")
    @Min(value = 2, message = "Debe tener al menos 2 asientos")
    private Integer cantidadAsientos;

    @NotNull(message = "El año es obligatorio")
    @Min(value = 1900, message = "El año no puede ser menor a 1900")
    @Max(value = 2100, message = "El año no puede ser mayor a 2100")
    private Integer anio;

    @NotNull(message = "El tipo de vehículo es obligatorio")
    @Min(value = 1, message = "El id_tipo_vehiculo debe ser mayor o igual a 1")
    private Integer idTipoVehiculo;
}
