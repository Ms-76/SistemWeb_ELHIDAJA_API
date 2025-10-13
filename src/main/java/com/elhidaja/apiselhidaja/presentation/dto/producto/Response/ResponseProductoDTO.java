package com.elhidaja.apiselhidaja.presentation.dto.producto.Response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProductoDTO {

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
}
