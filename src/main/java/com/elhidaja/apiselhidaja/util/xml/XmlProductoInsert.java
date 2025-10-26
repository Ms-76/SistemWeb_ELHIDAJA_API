package com.elhidaja.apiselhidaja.util.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("producto")
public class XmlProductoInsert {
    @XStreamAlias("id_login")
    private Long idLogin;

    @XStreamAlias("codigo")
    private String codigo;

    @XStreamAlias("nombre")
    private String nombre;

    @XStreamAlias("imagen")
    private String imagen;

    @XStreamAlias("codigo_barras")
    private String codigoBarras;

    @XStreamAlias("descripcion")
    private String descripcionProd;

    @XStreamAlias("id_subcategoria")
    private Integer idSubcategoria;

    @XStreamAlias("costo")
    private Double costo;
}
