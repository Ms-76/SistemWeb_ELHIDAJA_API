package com.elhidaja.apiselhidaja.persistence.repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.solicitudMaterial.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.solicitudMaterial.Response.*;
import com.elhidaja.apiselhidaja.service.DAO.SolicitudMaterialDAO;
import com.elhidaja.apiselhidaja.util.xml.XmlBuilder;
import com.elhidaja.apiselhidaja.util.xml.XmlDetalleSolicitudMaterialInsert;

@Repository
public class SolicitudMaterialRepository implements SolicitudMaterialDAO {
    private final JdbcTemplate jdbc;

    public SolicitudMaterialRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseSolicitudMaterialAllDTO getAllD(RequestSolicitudMaterialOptionDTO option) {
        ResponseSolicitudMaterialAllDTO rp = new ResponseSolicitudMaterialAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_solicitudes_material");

            Map<String, Object> inParams = Map.of(
                    "id_proyecto", option.getIdProyecto(),
                    "status", option.getEstado());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponseSolicitudMaterialDTO> solicitudes = rows.stream().map(row -> {
                ResponseSolicitudMaterialDTO dto = new ResponseSolicitudMaterialDTO();
                dto.setId(((Number) row.get("id_solicitud_material")).longValue());
                dto.setNombreProyecto((String) row.get("proyecto"));
                dto.setNombreSupervisor((String) row.get("supervisor"));
                dto.setNombreSolicitud((String) row.get("nombre_solicitud"));
                dto.setDescripcion((String) row.get("descripcion"));
                dto.setEstado((Integer) row.get("estado"));
                dto.setFechaSolicitud((LocalDateTime) row.get("fecha_solicitud"));
                dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();

            rp.setSolicitudesMaterial(solicitudes);

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
    public ResponseDetalleSolicitudMaterialDTO getByIdD(RequestSolicitudMaterialFilterDTO id) {
        ResponseDetalleSolicitudMaterialDTO rp = new ResponseDetalleSolicitudMaterialDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_solicitud_material_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_solicitud_material", id.getId());

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

                    ResponseSolicitudMaterialDTO dto = new ResponseSolicitudMaterialDTO();
                    dto.setId(((Number) row.get("id_solicitud_material")).longValue());
                    dto.setNombreProyecto((String) row.get("proyecto"));
                    dto.setNombreSupervisor((String) row.get("supervisor"));
                    dto.setNombreSolicitud((String) row.get("nombre_solicitud"));
                    dto.setDescripcion((String) row.get("descripcion"));
                    dto.setEstado((Integer) row.get("estado"));
                    dto.setFechaSolicitud((LocalDateTime) row.get("fecha_solicitud"));
                    dto.setStatus((Boolean) row.get("status"));

                    rp.setSolicitudMaterial(dto);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Solicitud encontrada");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró la solicitud de material");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseSolicitudMaterialMensajeDTO activateD(RequestSolicitudMaterialIdDTO id) {
        ResponseSolicitudMaterialMensajeDTO rp = new ResponseSolicitudMaterialMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_solicitud_material");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_solicitud_material", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar la solicitud de material.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Solicitud de material activada correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar la solicitud de material: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseSolicitudMaterialMensajeDTO desactivateD(RequestSolicitudMaterialIdDTO id) {
        ResponseSolicitudMaterialMensajeDTO rp = new ResponseSolicitudMaterialMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_solicitud_material");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_solicitud_material", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar la solicitud de material.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Solicitud de material desactivada correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar la solicitud de material: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseSolicitudMaterialMensajeDTO insertD(RequestSolicitudMaterialInsertDTO objSolicitud) {
        ResponseSolicitudMaterialMensajeDTO rp = new ResponseSolicitudMaterialMensajeDTO();
        try {

            List<XmlDetalleSolicitudMaterialInsert> xmlDetallesList = objSolicitud.getDetalles().stream()
                    .map(d -> new XmlDetalleSolicitudMaterialInsert(
                            d.getIdProducto(),
                            d.getCantidad(),
                            d.getObservacion()))
                    .toList();

            String xmlGenerado = XmlBuilder.toXmlDetallesSolicitudMaterialInsert(xmlDetallesList);

            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_solicitud_material_con_detalle_xml");

            Map<String, Object> inParams = new HashMap<>();
            inParams.put("id_usuario_sign", objSolicitud.getIdLogin());
            inParams.put("id_proyecto", objSolicitud.getIdProyecto());
            inParams.put("id_supervisor", objSolicitud.getIdSupervisor());
            inParams.put("nombre", objSolicitud.getNombre());
            inParams.put("descripcion", objSolicitud.getDescripcion());
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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo registrar la solicitud de material.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Solicitud de material registrada correctamente.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar la solicitud de material: " + e.getMessage());
        }
        return rp;
    }
}
