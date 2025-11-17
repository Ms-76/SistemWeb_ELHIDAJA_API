package com.elhidaja.apiselhidaja.presentation.controler.controllersEnums;

import com.elhidaja.apiselhidaja.util.enums.TipoDocumento;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TipoDocumentoControllery {
    @GetMapping("/api/tipo-documento")
    public TipoDocumento[] getTiposDocumento() {
        return TipoDocumento.values();
    }
}
