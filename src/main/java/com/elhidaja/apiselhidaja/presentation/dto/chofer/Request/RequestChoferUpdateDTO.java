package com.elhidaja.apiselhidaja.presentation.dto.chofer.Request;
import java.time.LocalDate;
import jakarta.validation.constraints.*;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.LengthSQL;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestChoferUpdateDTO {

    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotNull(message = "El idChofer es obligatorio")
    @Min(value = 1, message = "El idChofer debe ser mayor o igual a 1")
    private Long idChofer;

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

    @NotBlank(message = "La licencia de conducir no puede estar vacía")
    @LengthSQL(tabla = "chofer", columna = "licencia_conducir")
    private String licenciaConducir;

    @NotBlank(message = "La categoría de licencia no puede estar vacía")
    @LengthSQL(tabla = "chofer", columna = "categoria_licencia")
    private String categoriaLicencia;

    @NotNull(message = "La fecha de emisión de licencia es obligatoria")
    private LocalDate fechaEmisionLicencia;

    @NotNull(message = "La fecha de vencimiento de licencia es obligatoria")
    private LocalDate fechaVencimientoLicencia;

    @Min(value = 0, message = "La experiencia en años no puede ser negativa")
    private Integer experienciaAnios;
}