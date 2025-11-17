package com.elhidaja.apiselhidaja.util.xml;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("productos")
public class XmlDetallesGuiaEntrada {

    @XStreamImplicit(itemFieldName = "producto")
    private List<XmlDetalleGuiaEntrada> detalle;

    public XmlDetallesGuiaEntrada(List<XmlDetalleGuiaEntrada> detalle) {
        this.detalle = detalle;
    }

    public List<XmlDetalleGuiaEntrada> getDetalle() {
        return detalle;
    }
}
