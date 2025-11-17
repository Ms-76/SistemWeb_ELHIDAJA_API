package com.elhidaja.apiselhidaja.presentation.dto.detalleInventario.Response;

import lombok.Data;

import java.time.LocalDate;

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
    private LocalDate fechaInicioInventario;
    private LocalDate fechaFinInventario;
    private Boolean nuevo;
    private Boolean editadoManual;
    private String estado;
    private Boolean status;
}
