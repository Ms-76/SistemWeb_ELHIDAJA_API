package com.elhidaja.apiselhidaja.presentation.dto.tipoVehiculo.Response;
import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@JsonPropertyOrder({ "tiposVehiculo", "exito", "mensaje", "codigo" })
public class ResponseTipoVehiculoAllDTO extends GlobalResponse{
        private List<ResponseTipoVehiculoDTO> tiposVehiculo;
}
