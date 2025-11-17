package com.elhidaja.apiselhidaja.presentation.dto.vehiculoChoferAsignacion.Response;
import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "asignacion", "exito", "mensaje", "codigo" })
public class ResponseDetalleVehiculoChoferAsignacionDTO extends GlobalResponse{
        private ResponseVehiculoChoferAsignacionDTO asignacion;
}
