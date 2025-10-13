package com.elhidaja.apiselhidaja.presentation.dto.detalleInventario.Response;

import lombok.Data;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDetalleInventarioInnerDTO {
    private Long idInventario;
    private Long idDetalleInventario;
    private String producto;
    private String empleado;
    private Integer stockSistema;
    private Integer stockFisico;
    private Integer diferencia;
    private String observacion;
    private LocalDateTime fechaInicioInventario;
    private LocalDateTime fechaFinInventario;
    private Boolean nuevo;
    private Boolean editadoManual;
    private Integer estado;
    private Boolean status;
}
