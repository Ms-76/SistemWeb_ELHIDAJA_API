package com.elhidaja.apiselhidaja.util.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("detalle")
public class XmlDetalleSolicitudMaterialInsert {
    @XStreamAlias("id_producto")
    private Integer idProducto;

    @XStreamAlias("cantidad")
    private Integer cantidad;

    @XStreamAlias("observacion")
    private String observacion;
}
