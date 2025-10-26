package com.elhidaja.apiselhidaja.presentation.dto.guiaSalida.Request;
import com.elhidaja.apiselhidaja.util.validationsPersonalisate.LengthSQL;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "idLogin", "id", "descripcion" })
public class RequestGuiaSalidaUpdateDTO {
    @NotNull(message = "El idLogin es obligatorio") @Min(value = 1, message = "El idLogin debe ser >= 1")
    private Long idLogin;
    @NotNull(message = "El idGuiaSalida es obligatorio") @Min(value = 1, message = "El id debe ser >= 1")
    private Long id;
    @LengthSQL(tabla = "guia_salida", columna = "descripcion")
    private String descripcion;
}
