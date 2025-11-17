package com.elhidaja.apiselhidaja.util.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoDestino {
    TODOS,
    ALMACEN,
    PROYECTO;

    @JsonCreator
    public static TipoDestino fromString(String key) {
        return key == null ? null : TipoDestino.valueOf(key.toUpperCase());
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}
