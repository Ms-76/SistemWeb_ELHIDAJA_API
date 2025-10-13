package com.elhidaja.apiselhidaja.presentation.dto.serie.Request;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestSerieInsertDTO {
    @NotBlank(message = "La serie no puede estar vacía")
    @Size(min = 1, max = 50, message = "La serie debe tener entre 1 y 50 caracteres")
    private String serie;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 255, message = "La descripción no debe exceder los 255 caracteres")
    private String descripcion;
}
