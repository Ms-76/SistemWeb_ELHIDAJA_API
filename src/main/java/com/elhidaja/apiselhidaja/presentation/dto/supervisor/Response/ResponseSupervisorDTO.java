package com.elhidaja.apiselhidaja.presentation.dto.supervisor.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSupervisorDTO {
    private Long id;
    private Long idEntidad;
    private String tipoSupervisor;
    private String fechaCreacion;
    private Boolean status;
}
