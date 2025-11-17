package com.elhidaja.apiselhidaja.presentation.controler.controllersEnums;

import com.elhidaja.apiselhidaja.util.enums.EstadoCivil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EstadoCivilController {
    @GetMapping("/api/estado-civil")
    public EstadoCivil[] getEstadosCiviles() {
        return EstadoCivil.values();
    }
}
