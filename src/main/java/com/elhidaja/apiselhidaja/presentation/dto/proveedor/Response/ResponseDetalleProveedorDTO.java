package com.elhidaja.apiselhidaja.presentation.dto.proveedor.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "proveedor", "exito", "mensaje",  "codigo" })
public class ResponseDetalleProveedorDTO extends GlobalResponse  {
    private ResponseProveedorDTO proveedor;
}
