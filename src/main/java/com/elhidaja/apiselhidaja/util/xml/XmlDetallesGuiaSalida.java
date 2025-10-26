package com.elhidaja.apiselhidaja.util.xml;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("detalles")
public class XmlDetallesGuiaSalida {
    @XStreamImplicit(itemFieldName = "detalle")
    private List<XmlDetalleGuiaSalida> detalles;

    public XmlDetallesGuiaSalida(List<XmlDetalleGuiaSalida> detalles) {
        this.detalles = detalles;
    }

    public List<XmlDetalleGuiaSalida> getDetalles() {
        return detalles;
    }
}
