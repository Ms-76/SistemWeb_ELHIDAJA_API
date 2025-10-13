package com.elhidaja.apiselhidaja.presentation.dto.departamento.Request;
import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data

public class RequestDepartamentoOptionDTO {
    
    @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado;
}
