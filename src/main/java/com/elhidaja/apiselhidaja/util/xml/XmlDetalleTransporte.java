package com.elhidaja.apiselhidaja.util.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("detalle")
public class XmlDetalleTransporte {
    @XStreamAlias("id_transporte")
    private Long idTransporte;

    @XStreamAlias("id_pasajero")
    private Long idPasajero;

    @XStreamAlias("id_tipo_pasajero")
    private Long idTipoPasajero;

    @XStreamAlias("costo_pasaje")
    private Double costoPasaje;

    @XStreamAlias("bulto")
    private Boolean bulto;

    @XStreamAlias("costo_bulto")
    private Double costoBulto;
}
