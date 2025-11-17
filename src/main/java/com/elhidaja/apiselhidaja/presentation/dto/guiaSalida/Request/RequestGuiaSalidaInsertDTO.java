package com.elhidaja.apiselhidaja.presentation.dto.guiaSalida.Request;

import java.util.List;

import java.util.Date;

import com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaSalida.Request.RequestDetalleGuiaSalidaInsertDTO;
import com.elhidaja.apiselhidaja.util.validationsPersonalisate.LengthSQL;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestGuiaSalidaInsertDTO {

    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual 1")
    private Long idLogin;

    @NotNull(message = "El id del TipoOperacion es obligatorio")
    @Min(value = 1, message = "El idTipoOperacion debe ser mayor o igual 1")
    private Integer idTipoOperacion;

    @NotNull(message = "El id del TipoDocumento es obligatorio")
    @Min(value = 1, message = "El idTipoDocumento debe ser mayor o igual 1")
    private Integer idTipoDocumento;

    @NotNull(message = "El codigo sunat es obligatorio")
    private Integer codigoSunat;

    @NotNull(message = "El codigo interno es obligatorio")
    private Integer codigoInterno;

    @NotNull(message = "El idSerie es obligatorio")
    @Min(value = 1, message = "El idSerie debe ser mayor o igual 1")
    private Integer idSerie;

    @NotNull(message = "El id del Proveedor es obligatorio")
    @Min(value = 1, message = "El idProveedor debe ser mayor o igual 1")
    private Integer idProveedor;

    @NotNull(message = "El id del Usuario es obligatorio")
    @Min(value = 1, message = "El idUsuario debe ser mayor o igual 1")
    private Integer idUsuario;

    @NotNull(message = "El id del Almacen es obligatorio")
    @Min(value = 1, message = "El idAlmacen debe ser mayor o igual 1")
    private Integer idAlmacen;
    
    @NotNull(message = "El tipo de destino es obligatorio")
    @Pattern(regexp = "ALMACEN|PROYECTO", message = "El tipoDestino debe ser 'ALMACEN' o 'PROYECTO'")
    private String tipoDestino;

    @NotNull(message = "El id del destino es obligatorio")
    @Min(value = 1, message = "El idDestino debe ser mayor o igual 1")
    private Integer idDestino;

    @NotNull(message = "La fecha es obligatoria")
    private Date fecha;

    @NotBlank(message = "La descripción no puede estar vacía")
    @LengthSQL(tabla = "guia_salida", columna = "descripcion")
    private String descripcion;

    @NotNull(message = "La lista de detalles no puede ser nula")
    private List<RequestDetalleGuiaSalidaInsertDTO> detalles;
}
