package com.elhidaja.apiselhidaja.util.xml;

import java.util.List;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("productos")
public class XmlProductosUpdate {
    @XStreamImplicit(itemFieldName = "producto")
    private List<XmlProductoUpdate> productos;

    public XmlProductosUpdate(List<XmlProductoUpdate> productos) {
        this.productos = productos;
    }

    public List<XmlProductoUpdate> getProductos() {
        return productos;
    }
}
