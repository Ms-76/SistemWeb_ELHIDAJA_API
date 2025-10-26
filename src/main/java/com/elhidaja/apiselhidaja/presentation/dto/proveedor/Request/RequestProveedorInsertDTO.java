package com.elhidaja.apiselhidaja.presentation.dto.proveedor.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.LengthSQL;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "idLogin", "numeroDocumento", "nombres", "idDocumentoIdentidad", "direccion", "telefono", "email",
        "idDistrito" })
public class RequestProveedorInsertDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotBlank(message = "El número de documento no puede estar vacío")
    @LengthSQL(tabla = "entidad", columna = "numero_documento")
    @Pattern(regexp = "^[a-zA-Z0-9\\-]+$", message = "El número de documento solo puede contener letras, números y guiones")
    private String numeroDocumento;

    @NotBlank(message = "Los nombres no pueden estar vacíos")
    @LengthSQL(tabla = "entidad", columna = "nombres")
    @Pattern(regexp = "^[a-zA-ZÁÉÍÓÚáéíóúÑñ\\s]+$", message = "Los nombres solo deben contener letras y espacios")
    private String nombres;

    @NotNull(message = "El ID del documento de identidad es obligatorio")
    @Min(value = 1, message = "ID de documento de identidad no válido")
    private Integer idDocumentoIdentidad;

    @NotBlank(message = "La dirección no puede estar vacía")
    @LengthSQL(tabla = "entidad", columna = "direccion")
    private String direccion;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @LengthSQL(tabla = "entidad", columna = "telefono")
    @Pattern(regexp = "^9[0-9]{8}$", message = "El teléfono debe comenzar con 9 y contener exactamente 9 dígitos numéricos sin espacios")
    private String telefono;

    @NotBlank(message = "El email no puede estar vacío")
    @LengthSQL(tabla = "entidad", columna = "email")
    @Email(message = "El email debe tener un formato válido")
    private String email;

    @NotNull(message = "El ID del distrito es obligatorio")
    @Min(value = 1, message = "ID de distrito no válido")
    private Integer idDistrito;

}
