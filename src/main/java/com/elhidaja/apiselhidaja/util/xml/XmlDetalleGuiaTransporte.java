package com.elhidaja.apiselhidaja.util.xml;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("detalle")
public class XmlDetalleGuiaTransporte {
    @XStreamAlias("id_producto")
    private Long idProducto;

    @XStreamAlias("cantidad")
    private Integer cantidad;

    @XStreamAlias("id_unidad_medida")
    private Integer idUnidadMedida;

    @XStreamAlias("observacion")
    private String observacion;
}
