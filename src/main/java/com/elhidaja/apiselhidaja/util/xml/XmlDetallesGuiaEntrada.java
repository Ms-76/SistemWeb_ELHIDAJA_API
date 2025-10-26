package com.elhidaja.apiselhidaja.util.xml;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("detalles")
public class XmlDetallesGuiaEntrada {

    @XStreamImplicit(itemFieldName = "detalle")
    private List<XmlDetalleGuiaEntrada> detalle;

    public XmlDetallesGuiaEntrada(List<XmlDetalleGuiaEntrada> detalle) {
        this.detalle = detalle;
    }

    public List<XmlDetalleGuiaEntrada> getDetalle() {
        return detalle;
    }
}
