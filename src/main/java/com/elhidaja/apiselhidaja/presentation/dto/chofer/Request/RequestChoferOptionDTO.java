package com.elhidaja.apiselhidaja.presentation.dto.chofer.Request;
import lombok.Data;
import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;
import jakarta.validation.constraints.NotNull;

@Data
public class RequestChoferOptionDTO {

    @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado;

}
