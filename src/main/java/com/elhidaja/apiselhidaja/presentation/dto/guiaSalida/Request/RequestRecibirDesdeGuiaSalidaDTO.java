package com.elhidaja.apiselhidaja.presentation.dto.guiaSalida.Request;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestRecibirDesdeGuiaSalidaDTO {

    @NotNull(message = "El idLogin es obligatorio") 
    @Min(value = 1, message = "El idLogin debe ser >= 1")
    private Long idLogin;
    
    @NotNull(message = "El id_guia_salida es obligatorio") 
    @Min(value = 1, message = "El id_guia_salida debe ser mayor o igual a  1")
    private Long idGuiaSalida;

    @NotNull(message = "El idTipoOperacion es obligatorio")
    @Min(value = 1, message = "El idTipoOperacion debe ser mayor o igual a  1")
    private Integer idTipoOperacion;

    @NotNull(message = "El idTipoDocumento es obligatorio")
    @Min(value = 1, message = "El idTipoDocumento debe ser mayor o igual a  1")
    private Integer idTipoDocumento;

    @NotNull(message = "El codigoSunat es obligatorio")
    @Min(value = 1, message = "El codigoSunat debe ser mayor o igual a  1")
    private Integer codigoSunat;

    @NotNull(message = "El codigoInterno es obligatorio")
    @Min(value = 1, message = "El codigoInterno debe ser mayor o igual a  1")
    private Integer codigoInterno;

    @NotNull(message = "El idSerie es obligatorio")
    @Min(value = 1, message = "El idSerie debe ser mayor o igual a  1")
    private Integer idSerie;

    @NotNull(message = "El idProveedorSistema es obligatorio")
    @Min(value = 1, message = "El idProveedorSistema debe ser mayor o igual a  1")
    private Integer idProveedorSistema;
}
