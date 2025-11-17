package com.elhidaja.apiselhidaja.persistence.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.detalleSolicitudMaterial.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.detalleSolicitudMaterial.Response.*;
import com.elhidaja.apiselhidaja.service.DAO.DetalleSolicitudMaterialDAO;

@Repository
public class DetalleSolicitudMaterialRepository implements DetalleSolicitudMaterialDAO {
    private final JdbcTemplate jdbc;

    public DetalleSolicitudMaterialRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseDetalleSolicitudMaterialAllDTO getAllD(RequestDetalleSolicitudMaterialOptionDTO option) {

        ResponseDetalleSolicitudMaterialAllDTO rp = new ResponseDetalleSolicitudMaterialAllDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
            .withProcedureName("SP_obtener_detalles_solicitud_material");

            Map<String, Object> result = call.execute(Map.of(
                "status", option.getEstado(),
                "id_producto", option.getIdProducto(),
                "id_proyecto", option.getIdProyecto()
                ));

            @SuppressWarnings("unchecked")
            
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");
            List<ResponseDetalleSolicitudMaterialItemDTO> detalles = rows.stream().map(row -> {
                ResponseDetalleSolicitudMaterialItemDTO dto = new ResponseDetalleSolicitudMaterialItemDTO();
                dto.setId(((Number) row.get("id_detalle_solicitud_material")).longValue());
                dto.setIdSolicitudMaterial(((Number) row.get("solicitud_material")).longValue());
                dto.setProducto((String) row.get("producto"));
                dto.setCantidad((Integer) row.get("cantidad"));
                dto.setObservacion((String) row.get("observacion"));
                dto.setNombreSolicitud((String) row.get("nombre_solicitud"));
                dto.setFechaSolicitud(
                        ((Timestamp) row.get("fecha_solicitud")).toLocalDateTime());
                dto.setEstado((String) row.get("estado"));
                dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();
            rp.setDetalleSolicitudesMaterial(detalles);
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
    public ResponseDetalleSolicitudMaterialDTO getByIdD(RequestDetalleSolicitudMaterialFilterDTO id) {
        ResponseDetalleSolicitudMaterialDTO rp = new ResponseDetalleSolicitudMaterialDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_detalle_solicitud_material_por_id");
            Map<String, Object> result = call.execute(Map.of("id_detalle_solicitud_material", id.getId()));
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");
            if (rows != null && !rows.isEmpty()) {
                Map<String, Object> row = rows.get(0);
                ResponseDetalleSolicitudMaterialItemDTO dto = new ResponseDetalleSolicitudMaterialItemDTO();
                dto.setId(((Number) row.get("id_detalle_solicitud_material")).longValue());
                dto.setIdSolicitudMaterial(((Number) row.get("solicitud_material")).longValue());
                dto.setProducto((String) row.get("producto"));
                dto.setCantidad((Integer) row.get("cantidad"));
                dto.setObservacion((String) row.get("observacion"));
                dto.setNombreSolicitud((String) row.get("nombre_solicitud"));
                dto.setFechaSolicitud(
                        ((Timestamp) row.get("fecha_solicitud")).toLocalDateTime());
                dto.setEstado((String) row.get("estado"));
                dto.setStatus((Boolean) row.get("status"));

                rp.setDetalleSolicitudMaterial(dto);
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Detalle encontrado");
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró el detalle");
            }
        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponserDetalleSolicitudMaterialMensajeDTO activateD(RequestDetalleSolicitudMaterialIdDTO id) {
        ResponserDetalleSolicitudMaterialMensajeDTO rp = new ResponserDetalleSolicitudMaterialMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_detalle_solicitud_material");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_detalle_solicitud_material", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar el detalle.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Detalle activado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar el detalle: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponserDetalleSolicitudMaterialMensajeDTO desactivateD(RequestDetalleSolicitudMaterialIdDTO id) {
        ResponserDetalleSolicitudMaterialMensajeDTO rp = new ResponserDetalleSolicitudMaterialMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_detalle_solicitud_material");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_detalle_solicitud_material", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar el detalle.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Detalle desactivado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar el detalle: " + e.getMessage());
        }
        return rp;
    }

}
