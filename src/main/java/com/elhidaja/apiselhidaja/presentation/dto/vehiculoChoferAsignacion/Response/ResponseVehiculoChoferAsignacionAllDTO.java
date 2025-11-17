package com.elhidaja.apiselhidaja.presentation.dto.vehiculoChoferAsignacion.Response;
import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@JsonPropertyOrder({ "asignaciones", "exito", "mensaje", "codigo" })
public class ResponseVehiculoChoferAsignacionAllDTO extends GlobalResponse{
        private List<ResponseVehiculoChoferAsignacionDTO> asignaciones;
}
