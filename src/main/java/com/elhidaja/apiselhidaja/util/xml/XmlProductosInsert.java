package com.elhidaja.apiselhidaja.util.xml;

import java.util.List;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("productos")
public class XmlProductosInsert {
    @XStreamImplicit(itemFieldName = "producto")
    private List<XmlProductoInsert> productos;

    public XmlProductosInsert(List<XmlProductoInsert> productos) {
        this.productos = productos;
    }

    public List<XmlProductoInsert> getProductos() {
        return productos;
    }
}
