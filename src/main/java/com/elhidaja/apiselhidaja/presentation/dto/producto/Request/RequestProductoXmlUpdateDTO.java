package com.elhidaja.apiselhidaja.presentation.dto.producto.Request;

import java.util.List;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestProductoXmlUpdateDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotEmpty(message = "Debe incluir al menos un detalle")
    private List<RequestProductoUpdateDTO> detalles;
}
