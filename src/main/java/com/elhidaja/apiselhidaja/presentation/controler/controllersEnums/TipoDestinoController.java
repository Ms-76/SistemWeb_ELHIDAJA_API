package com.elhidaja.apiselhidaja.presentation.controler.controllersEnums;

import com.elhidaja.apiselhidaja.util.enums.TipoDestino;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TipoDestinoController {
    @GetMapping("/api/tipo-destino")
    public TipoDestino[] getTiposDestino() {
        return TipoDestino.values();
    }
}
