package com.elhidaja.apiselhidaja.presentation.dto.area.Response;
import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "area", "exito", "mensaje", "codigo" })
public class ResponseDetalleAreaDTO  extends GlobalResponse{
     private ResponseAreaDTO area;
}
