package com.elhidaja.apiselhidaja.presentation.dto.vehiculo.Response;
import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "vehiculo", "exito", "mensaje", "codigo" })
public class ResponseDetalleVehiculoDTO  extends GlobalResponse {
        private ResponseVehiculoDTO vehiculo;
}
