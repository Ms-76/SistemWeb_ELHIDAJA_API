package com.elhidaja.apiselhidaja.util.xml;

import java.util.List;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("detalles")
public class XmlDetallesGuiaTransporte {
    @XStreamImplicit(itemFieldName = "detalle")
    private List<XmlDetalleGuiaTransporte> detalles;

    public XmlDetallesGuiaTransporte(List<XmlDetalleGuiaTransporte> detalles) {
        this.detalles = detalles;
    }

    public List<XmlDetalleGuiaTransporte> getDetalles() {
        return detalles;
    }
}
