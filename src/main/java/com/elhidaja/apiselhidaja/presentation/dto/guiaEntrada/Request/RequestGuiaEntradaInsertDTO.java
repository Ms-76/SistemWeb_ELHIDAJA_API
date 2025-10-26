package com.elhidaja.apiselhidaja.presentation.dto.guiaEntrada.Request;

import java.util.List;

import com.elhidaja.apiselhidaja.presentation.dto.detalleSolicitudMaterial.Request.DetalleGuiaEntradaRequestDTO;
import com.elhidaja.apiselhidaja.util.validationsPersonalisate.LengthSQL;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "idLogin", "idProveedor", "idUsuario", "descripcion" })
public class RequestGuiaEntradaInsertDTO {

    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotNull(message = "El id del TipoOperacion es obligatorio")
    private Integer idTipoOperacion;

    @NotNull(message = "El id del TipoDocumento es obligatorio")
    private Integer idTipoDocumento;

    @NotNull(message = "El codigo sunat es obligatorio")
    private Integer codigoSunat;

    @NotNull(message = "El codigo interno es obligatorio")
    private Integer codigoInterno;

    @NotNull(message = "El idSerie es obligatorio")
    private Integer idSerie;

    @NotNull(message = "El id del Proveedor es obligatorio")
    private Integer idProveedor;

    @NotNull(message = "El id del Usuario es obligatorio")
    private Integer idUsuario;

    @NotNull(message = "El id del Almacen es obligatorio")
    private Integer idAlmacen;

    @NotBlank(message = "La descripción no puede estar vacía")
    @LengthSQL(tabla = "guia_entrada", columna = "descripcion")
    private String descripcion;

    @NotEmpty(message = "Debe incluir al menos un detalle")
    private List<DetalleGuiaEntradaRequestDTO> detalles;
}
