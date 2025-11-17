package com.elhidaja.apiselhidaja.presentation.dto.vehiculo.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.LengthSQL;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestVehiculoUpdateDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotNull(message = "El idVehiculo es obligatorio")
    @Min(value = 1, message = "El idVehiculo debe ser mayor o igual a 1")
    private Long idVehiculo;

    @NotBlank(message = "La placa no puede estar vacía")
    @LengthSQL(tabla = "vehiculo", columna = "placa")
    @Pattern(regexp = "^[A-Z0-9]{6,7}$", message = "La placa debe contener entre 6 y 7 caracteres alfanuméricos en mayúscula")
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
