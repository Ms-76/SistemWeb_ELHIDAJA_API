package com.elhidaja.apiselhidaja.persistence.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.transporte.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.transporte.Request.*;
import com.elhidaja.apiselhidaja.service.DAO.TransporteDAO;
import com.elhidaja.apiselhidaja.util.xml.XmlBuilder;
import com.elhidaja.apiselhidaja.util.xml.XmlDetalleTransporte;

@Repository
public class TransporteRepository implements TransporteDAO {
    private final JdbcTemplate jdbc;

    public TransporteRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseTransporteAllDTO getAllD(RequestTransporteOptionDTO option) {
        ResponseTransporteAllDTO rp = new ResponseTransporteAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_transportes");

            Map<String, Object> inParams = Map.of(
                    "status", option.getEstado(),
                    "id_chofer", option.getIdChofer(),
                    "id_tipo_transporte", option.getIdTipoTransporte(),
                    "id_tipo_origen", option.getIdTipoOrigen(),
                    "id_tipo_destino", option.getIdTipoDestino(),
                    "fecha_inicio", option.getFechaInicio(),
                    "fecha_fin", option.getFechaFin());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponseTransporteDTO> transportes = rows.stream().map(row -> {
                ResponseTransporteDTO dto = new ResponseTransporteDTO();

                dto.setId(((Number) row.get("id_transporte")).longValue());
                dto.setTipoTransporte((String) row.get("tipo_transporte"));
                dto.setTipoOrigen((String) row.get("tipo_origen"));
                dto.setNombreOrigen((String) row.get("origen_nombre"));
                dto.setTipoDestino((String) row.get("tipo_destino"));
                dto.setNombreDestino((String) row.get("destino_nombre"));
                dto.setFechaProgramada(((java.sql.Date) row.get("fecha_programada")).toLocalDate());
                dto.setObservaciones((String) row.get("observaciones"));
                dto.setPlaca((String) row.get("vehiculo_placa"));
                dto.setTipoVehiculo((String) row.get("tipo_vehiculo"));
                dto.setNombre((String) row.get("nombre_chofer"));
                dto.setLicencia((String) row.get("licencia"));
                dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();

            rp.setTransportes(transportes);
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
    public ResponseDetalleTransporteDTO getByIdD(RequestTransporteFilterDTO id) {
        ResponseDetalleTransporteDTO rp = new ResponseDetalleTransporteDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_transporte_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_transporte", id.getId());

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
                    ResponseTransporteDTO rcd = new ResponseTransporteDTO();

                    rcd.setId(((Number) row.get("id_transporte")).longValue());
                    rcd.setTipoTransporte((String) row.get("tipo_transporte"));
                    rcd.setTipoOrigen((String) row.get("tipo_origen"));
                    rcd.setNombreOrigen((String) row.get("origen_nombre"));
                    rcd.setTipoDestino((String) row.get("tipo_destino"));
                    rcd.setNombreDestino((String) row.get("destino_nombre"));
                    rcd.setFechaProgramada(((java.sql.Date) row.get("fecha_programada")).toLocalDate());
                    rcd.setObservaciones((String) row.get("observaciones"));
                    rcd.setPlaca((String) row.get("vehiculo_placa"));
                    rcd.setTipoVehiculo((String) row.get("tipo_vehiculo"));
                    rcd.setNombre((String) row.get("nombre_chofer"));
                    rcd.setLicencia((String) row.get("licencia"));
                    rcd.setStatus((Boolean) row.get("status"));

                    rp.setTransporte(rcd);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Transporte encontrado");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró el transporte");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseTransporteMensajeDTO activateD(RequestTransporteIdDTO id) {
        ResponseTransporteMensajeDTO rp = new ResponseTransporteMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_transporte");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_transporte", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar el transporte.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Transporte activado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar el transporte: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseTransporteMensajeDTO desactivateD(RequestTransporteIdDTO id) {
        ResponseTransporteMensajeDTO rp = new ResponseTransporteMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_transporte");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_transporte", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar el transporte.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Transporte desactivado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar el transporte: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseTransporteMensajeDTO insertD(RequestTransporteInsertDTO objTransporte) {
        ResponseTransporteMensajeDTO rp = new ResponseTransporteMensajeDTO();
        try {
            List<XmlDetalleTransporte> detallesXml = objTransporte.getDetalles().stream()
                    .map(dto -> new XmlDetalleTransporte(
                            dto.getIdTransporte(),
                            dto.getIdPasajero(),
                            dto.getIdTipoPasajero(),
                            dto.getCostoPasaje(),
                            dto.getBulto(),
                            dto.getCostoBulto()))
                    .toList();

            String xmlGenerado = XmlBuilder.toXmlTransporteDetalle(detallesXml);

            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_transporte");

            Map<String, Object> inParams = new HashMap<>();
            inParams.put("id_usuario_sign", objTransporte.getIdLogin());
            inParams.put("id_asignacion", objTransporte.getIdAsignacion());
            inParams.put("id_tipo_transporte", objTransporte.getIdTipoTransporte());
            inParams.put("id_origen", objTransporte.getIdOrigen());
            inParams.put("id_tipo_origen", objTransporte.getIdTipoOrigen());
            inParams.put("id_destino", objTransporte.getIdDestino());
            inParams.put("id_tipo_destino", objTransporte.getIdTipoDestino());
            inParams.put("fecha_programada", objTransporte.getFechaProgramada());
            inParams.put("observaciones", objTransporte.getObservaciones());
            inParams.put("xml_detalles", xmlGenerado);
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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo registrar el transporte.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Transporte registrado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar transporte : " + e.getMessage());
        }
        return rp;
    }
}
