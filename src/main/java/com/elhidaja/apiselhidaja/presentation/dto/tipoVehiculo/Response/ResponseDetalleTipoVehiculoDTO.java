package com.elhidaja.apiselhidaja.presentation.dto.tipoVehiculo.Response;
import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "tipoVehiculo", "exito", "mensaje", "codigo" })
public class ResponseDetalleTipoVehiculoDTO extends GlobalResponse {
     private ResponseTipoVehiculoDTO tipoVehiculo;
}
