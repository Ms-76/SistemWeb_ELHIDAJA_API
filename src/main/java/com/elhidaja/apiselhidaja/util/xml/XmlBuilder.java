package com.elhidaja.apiselhidaja.util.xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

import java.util.List;

public class XmlBuilder {
    // guia entrada
    public static String toXmlGuiaEntrada(List<XmlDetalleGuiaEntrada> detalles) {
        XStream xstream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("_-", "_")));
        xstream.autodetectAnnotations(true);
        xstream.ignoreUnknownElements();
        xstream.aliasSystemAttribute(null, "class");
        xstream.setMode(XStream.NO_REFERENCES);
        xstream.useAttributeFor(String.class);
        xstream.omitField(Object.class, "class");

        xstream.processAnnotations(XmlDetalleGuiaEntrada.class);
        xstream.processAnnotations(XmlDetallesGuiaEntrada.class);

        xstream.alias("detalles", XmlDetallesGuiaEntrada.class);
        xstream.alias("detalle", XmlDetalleGuiaEntrada.class);

        xstream.allowTypesByWildcard(new String[] {
                "com.elhidaja.apiselhidaja.**"
        });
        XmlDetallesGuiaEntrada wrapper = new XmlDetallesGuiaEntrada(detalles);
        return xstream.toXML(wrapper);
    }

    // productos insert
    public static String toXmlProductosInsert(List<XmlProductoInsert> productos) {
        XStream xstream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("_-", "_")));
        xstream.autodetectAnnotations(true);
        xstream.ignoreUnknownElements();
        xstream.aliasSystemAttribute(null, "class");
        xstream.setMode(XStream.NO_REFERENCES);
        xstream.useAttributeFor(String.class);
        xstream.omitField(Object.class, "class");

        xstream.processAnnotations(XmlProductoInsert.class);
        xstream.processAnnotations(XmlProductosInsert.class);

        xstream.alias("productos", XmlProductosInsert.class);
        xstream.alias("producto", XmlProductoInsert.class);

        xstream.allowTypesByWildcard(new String[] {
                "com.elhidaja.apiselhidaja.**"
        });

        XmlProductosInsert wrapper = new XmlProductosInsert(productos);
        return xstream.toXML(wrapper);
    }

    // Productos update
    public static String toXmlProductosUpdate(List<XmlProductoUpdate> productos) {
        XStream xstream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("_-", "_")));
        xstream.autodetectAnnotations(true);
        xstream.ignoreUnknownElements();
        xstream.aliasSystemAttribute(null, "class");
        xstream.setMode(XStream.NO_REFERENCES);
        xstream.useAttributeFor(String.class);
        xstream.omitField(Object.class, "class");

        xstream.processAnnotations(XmlProductoUpdate.class);
        xstream.processAnnotations(XmlProductosUpdate.class);

        xstream.alias("productos", XmlProductosUpdate.class);
        xstream.alias("producto", XmlProductoUpdate.class);

        xstream.allowTypesByWildcard(new String[] {
                "com.elhidaja.apiselhidaja.**"
        });

        XmlProductosUpdate wrapper = new XmlProductosUpdate(productos);
        return xstream.toXML(wrapper);
    }

    // Detalles inventario insert
    public static String toXmlDetallesInventarioInsert(List<XmlDetalleInventarioInsert> detalles) {
        XStream xstream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("_-", "_")));
        xstream.autodetectAnnotations(true);
        xstream.ignoreUnknownElements();
        xstream.aliasSystemAttribute(null, "class");
        xstream.setMode(XStream.NO_REFERENCES);
        xstream.useAttributeFor(String.class);
        xstream.omitField(Object.class, "class");

        xstream.processAnnotations(XmlDetalleInventarioInsert.class);
        xstream.processAnnotations(XmlDetallesInventarioInsert.class);

        xstream.alias("detalles", XmlDetallesInventarioInsert.class);
        xstream.alias("detalle", XmlDetalleInventarioInsert.class);

        xstream.allowTypesByWildcard(new String[] {
                "com.elhidaja.apiselhidaja.**"
        });

        XmlDetallesInventarioInsert wrapper = new XmlDetallesInventarioInsert(detalles);
        return xstream.toXML(wrapper);
    }

    // Detalles solicitud material insert
    public static String toXmlDetallesSolicitudMaterialInsert(List<XmlDetalleSolicitudMaterialInsert> detalles) {
        XStream xstream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("_-", "_")));
        xstream.autodetectAnnotations(true);
        xstream.ignoreUnknownElements();
        xstream.aliasSystemAttribute(null, "class");
        xstream.setMode(XStream.NO_REFERENCES);
        xstream.useAttributeFor(String.class);
        xstream.omitField(Object.class, "class");

        xstream.processAnnotations(XmlDetalleSolicitudMaterialInsert.class);
        xstream.processAnnotations(XmlDetallesSolicitudMaterialInsert.class);

        xstream.alias("detalles", XmlDetallesSolicitudMaterialInsert.class);
        xstream.alias("detalle", XmlDetalleSolicitudMaterialInsert.class);

        xstream.allowTypesByWildcard(new String[] {
                "com.elhidaja.apiselhidaja.**"
        });

        XmlDetallesSolicitudMaterialInsert wrapper = new XmlDetallesSolicitudMaterialInsert(detalles);
        return xstream.toXML(wrapper);
    }

    // guia salida
    public static String toXmlGuiaSalida(List<XmlDetalleGuiaSalida> detalles) {
        XStream xstream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("_-", "_")));
        xstream.autodetectAnnotations(true);
        xstream.ignoreUnknownElements();
        xstream.aliasSystemAttribute(null, "class");
        xstream.setMode(XStream.NO_REFERENCES);
        xstream.useAttributeFor(String.class);
        xstream.omitField(Object.class, "class");

        xstream.processAnnotations(XmlDetalleGuiaSalida.class);
        xstream.processAnnotations(XmlDetallesGuiaSalida.class);

        xstream.alias("detalles", XmlDetallesGuiaSalida.class);
        xstream.alias("detalle", XmlDetalleGuiaSalida.class);

        xstream.allowTypesByWildcard(new String[] {
                "com.elhidaja.apiselhidaja.**"
        });

        XmlDetallesGuiaSalida wrapper = new XmlDetallesGuiaSalida(detalles);
        return xstream.toXML(wrapper);
    }
}
