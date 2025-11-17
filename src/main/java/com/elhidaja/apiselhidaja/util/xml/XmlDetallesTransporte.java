package com.elhidaja.apiselhidaja.util.xml;

import java.util.List;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("detalles")
public class XmlDetallesTransporte {
    @XStreamImplicit(itemFieldName = "detalle")
    private List<XmlDetalleTransporte> detalles;

    public XmlDetallesTransporte(List<XmlDetalleTransporte> detalles) {
        this.detalles = detalles;
    }

    public List<XmlDetalleTransporte> getDetalles() {
        return detalles;
    }
}
