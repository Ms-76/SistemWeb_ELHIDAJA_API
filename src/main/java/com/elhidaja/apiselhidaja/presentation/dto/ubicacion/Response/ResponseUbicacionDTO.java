package com.elhidaja.apiselhidaja.presentation.dto.ubicacion.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUbicacionDTO {
    private Long id;
    private String categoria;
    private String subCategoria;
    private String codigo;
    private String nombre;
    private String codigoBarras;
    private String descripcion;
    private Long stock_total;
    private String unidadMedida;
    private Double costo;
    private Boolean status;
    private String codigoAlmacen;
    private String codigoEstante;
    private String codigoPallet;
}
