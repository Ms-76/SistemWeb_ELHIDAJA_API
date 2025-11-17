package com.elhidaja.apiselhidaja.persistence.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.guiaTransporte.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.guiaTransporte.Request.*;
import com.elhidaja.apiselhidaja.service.DAO.GuiaTransporteDAO;
import com.elhidaja.apiselhidaja.util.xml.XmlBuilder;
import com.elhidaja.apiselhidaja.util.xml.XmlDetalleGuiaTransporte;

@Repository
public class GuiaTransporteRepository implements GuiaTransporteDAO {
    private final JdbcTemplate jdbc;

    public GuiaTransporteRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseGuiaTransporteAllDTO getAllD(RequestGuiaTransporteOptionDTO option) {
        ResponseGuiaTransporteAllDTO rp = new ResponseGuiaTransporteAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_guias_transporte");

            Map<String, Object> inParams = Map.of(
                    "status", option.getEstado(),
                    "id_chofer", option.getIdChofer(),
                    "id_vehiculo", option.getIdVehiculo(),
                    "punto_partida", option.getPuntoPartida(),
                    "punto_llegada", option.getPuntoLlegada(),
                    "fecha_inicio", option.getFechaInicio(),
                    "fecha_fin", option.getFechaFin());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponseGuiaTransporteDTO> guias = rows.stream().map(row -> {
                ResponseGuiaTransporteDTO dto = new ResponseGuiaTransporteDTO();
                dto.setIdGuiaTransporte((Integer) row.get("id_guia_transporte"));
                dto.setIdGuiaSalida((Integer) row.get("id_guia_salida"));
                dto.setVehiculo((String) row.get("vehiculo"));
                dto.setChofer((String) row.get("chofer"));
                dto.setPuntoPartida((String) row.get("punto_partida"));
                dto.setNombrePuntoPartida((String) row.get("nombre_partida"));
                dto.setPuntoLlegada((String) row.get("punto_llegada"));
                dto.setNombrePuntoLlegada((String) row.get("nombre_llegada"));
                dto.setFechaTranslado((java.time.LocalDate) row.get("fecha_translado"));
                dto.setObservacion((String) row.get("observacion"));
                dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();

            rp.setGuiasTransporte(guias);
            rp.setExito(true);
            rp.setCodigo("200");
            rp.setMensaje("Consulta exitosa");
        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener datos " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseDetalleGuiaTransporteDTO getByIdD(RequestGuiaTransporteFilterDTO id) {
        ResponseDetalleGuiaTransporteDTO rp = new ResponseDetalleGuiaTransporteDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_guia_transporte_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_guia_transporte", id.getId());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            if (rows != null && !rows.isEmpty()) {
                Map<String, Object> row = rows.get(0);

                if (row.containsKey("exito")) {
                    rp.setExito((Boolean) row.get("exito"));
                    rp.setMensaje((String) row.get("mensaje"));
                    rp.setCodigo("404");
                } else {
                    ResponseGuiaTransporteDTO rcd = new ResponseGuiaTransporteDTO();
                    rcd.setIdGuiaTransporte((Integer) row.get("id_guia_transporte"));
                    rcd.setIdGuiaSalida((Integer) row.get("id_guia_salida"));
                    rcd.setVehiculo((String) row.get("vehiculo"));
                    rcd.setChofer((String) row.get("chofer"));
                    rcd.setPuntoPartida((String) row.get("punto_partida"));
                    rcd.setNombrePuntoPartida((String) row.get("nombre_partida"));
                    rcd.setPuntoLlegada((String) row.get("punto_llegada"));
                    rcd.setNombrePuntoLlegada((String) row.get("nombre_llegada"));
                    rcd.setFechaTranslado((java.time.LocalDate) row.get("fecha_translado"));
                    rcd.setObservacion((String) row.get("observacion"));
                    rcd.setStatus((Boolean) row.get("status"));

                    rp.setGuiaTransporte(rcd);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Guía de transporte encontrada");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró la guía de transporte");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseGuiaTransporteMensajeDTO activateD(RequestGuiaTransporteIdDTO id) {
        ResponseGuiaTransporteMensajeDTO rp = new ResponseGuiaTransporteMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_guia_transporte");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_guia_transporte", id.getId());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.values().stream()
                    .filter(v -> v instanceof List && !((List<?>) v).isEmpty())
                    .findFirst()
                    .orElse(null);

            if (resultSet != null) {
                Map<String, Object> errorRow = resultSet.get(0);
                String mensajeError = (String) errorRow.get("mensaje");
                rp.setExito(false);
                rp.setCodigo("400");
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar la guía de transporte.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Guía de transporte activada correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar la guía de transporte: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseGuiaTransporteMensajeDTO desactivateD(RequestGuiaTransporteIdDTO id) {
        ResponseGuiaTransporteMensajeDTO rp = new ResponseGuiaTransporteMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_guia_transporte");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_guia_transporte", id.getId());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.values().stream()
                    .filter(v -> v instanceof List && !((List<?>) v).isEmpty())
                    .findFirst()
                    .orElse(null);

            if (resultSet != null) {
                Map<String, Object> errorRow = resultSet.get(0);
                String mensajeError = (String) errorRow.get("mensaje");
                rp.setExito(false);
                rp.setCodigo("400");
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar la guía de transporte.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Guía de transporte desactivada correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar la guía de transporte: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseGuiaTransporteMensajeDTO insertD(RequestGuiaTransporteInsertDTO obj) {
        ResponseGuiaTransporteMensajeDTO rp = new ResponseGuiaTransporteMensajeDTO();
        try {
            List<XmlDetalleGuiaTransporte> detallesXml = obj.getDetalles().stream()
                    .map(dto -> new XmlDetalleGuiaTransporte(
                            dto.getIdProducto(),
                            dto.getCantidad(),
                            dto.getIdUnidadMedida(),
                            dto.getObservacion()))
                    .toList();

            String xmlGenerado = XmlBuilder.toXmlDetallesGuiaTransporte(detallesXml);

            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_guia_transporte");

            Map<String, Object> inParams = new HashMap<>();
            inParams.put("id_usuario_sign", obj.getIdLogin());
            inParams.put("id_guia_salida", obj.getIdGuiaSalida());
            inParams.put("id_asignacion", obj.getIdAsignacion());
            inParams.put("id_tipo_operacion", obj.getIdTipoOperacion());
            inParams.put("id_tipo_documento", obj.getIdTipoDocumento());
            inParams.put("codigo_sunat", obj.getCodigoSunat());
            inParams.put("codigo_interno", obj.getCodigoInterno());
            inParams.put("id_serie", obj.getIdSerie());
            inParams.put("id_almacen_salida", obj.getIdAlmacenSalida());
            inParams.put("punto_llegada", obj.getPuntoLlegada());
            inParams.put("fecha_translado", obj.getFechaTranslado());
            inParams.put("observacion", obj.getObservacion());
            inParams.put("xmlDetalles", xmlGenerado);
            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.values().stream()
                    .filter(v -> v instanceof List && !((List<?>) v).isEmpty())
                    .findFirst()
                    .orElse(null);

            if (resultSet != null) {
                Map<String, Object> errorRow = resultSet.get(0);
                String mensajeError = (String) errorRow.get("mensaje");
                rp.setCodigo("400");
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo registrar la guía de transporte.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Guía de transporte registrada correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar guía de transporte: " + e.getMessage());
        }
        return rp;
    }
}
