package com.elhidaja.apiselhidaja.util.xml;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("detalles")
public class XmlDetallesSolicitudMaterialInsert {
    @XStreamImplicit(itemFieldName = "detalle")
    private List<XmlDetalleSolicitudMaterialInsert> detalles;

    public XmlDetallesSolicitudMaterialInsert(List<XmlDetalleSolicitudMaterialInsert> detalles) {
        this.detalles = detalles;
    }

    public List<XmlDetalleSolicitudMaterialInsert> getDetalles() {
        return detalles;
    }
}
