package com.elhidaja.apiselhidaja.util.xml;

import java.util.List;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("detalles")
public class XmlDetallesInventarioInsert {
    @XStreamImplicit(itemFieldName = "detalle")
    private List<XmlDetalleInventarioInsert> detalles;

    public XmlDetallesInventarioInsert(List<XmlDetalleInventarioInsert> detalles) {
        this.detalles = detalles;
    }

    public List<XmlDetalleInventarioInsert> getDetalles() {
        return detalles;
    }
}
