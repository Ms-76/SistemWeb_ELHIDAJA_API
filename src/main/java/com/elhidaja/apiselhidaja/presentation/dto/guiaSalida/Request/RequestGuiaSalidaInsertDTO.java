package com.elhidaja.apiselhidaja.presentation.dto.guiaSalida.Request;
import java.util.List;

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
    @LengthSQL(tabla = "guia_salida", columna = "descripcion")
    private String descripcion;
    
    @NotNull(message = "La lista de detalles no puede ser nula")
    private List<RequestDetalleGuiaSalidaInsertDTO> detalles;
}
