package com.elhidaja.apiselhidaja.persistence.repository;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaTransporte.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaTransporte.Request.*;
import com.elhidaja.apiselhidaja.service.DAO.DetalleGuiaTransporteDAO;

@Repository
public class DetalleGuiaTransporteRepository implements DetalleGuiaTransporteDAO {
    private final JdbcTemplate jdbc;

    public DetalleGuiaTransporteRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseDetalleGuiaTransporteAllDTO getAllD(RequestDetalleGuiaTransporteOptionDTO option) {
        ResponseDetalleGuiaTransporteAllDTO rp = new ResponseDetalleGuiaTransporteAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_detalle_guia_transporte");

            Map<String, Object> inParams = Map.of(
                    "status", option.getEstado(),
                    "id_guia_transporte", option.getIdGuiaTransporte(),
                    "id_producto", option.getIdProducto(),
                    "id_almacen", option.getIdAlmacen(),
                    "id_unidad_medida", option.getIdUnidadMedida());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponseDetalleGuiaTransporteDTO> detalles = rows.stream().map(row -> {
                ResponseDetalleGuiaTransporteDetalleDTO dto = new ResponseDetalleGuiaTransporteDetalleDTO();
                dto.setId(((Number) row.get("id_detalle_guia_transporte")).longValue());
                dto.setIdGuiaTransporte(((Number) row.get("id_guia_transporte")).longValue());
                dto.setCodigoProducto((String) row.get("producto_codigo"));
                dto.setNombreProducto((String) row.get("producto_nombre"));
                dto.setCantidad((Integer) row.get("cantidad"));
                dto.setUnidadMedida((String) row.get("unidad_medida"));
                dto.setObservacion((String) row.get("observacion"));
                dto.setStatus((Boolean) row.get("status"));
                ResponseDetalleGuiaTransporteDTO wrapper = new ResponseDetalleGuiaTransporteDTO();
                wrapper.setDetalleGuia(dto);
                return wrapper;
            }).toList();

            rp.setDetallesGuia(detalles);
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
    public ResponseDetalleGuiaTransporteDTO getByIdD(RequestDetalleGuiaTransporteFilterDTO id) {
        ResponseDetalleGuiaTransporteDTO rp = new ResponseDetalleGuiaTransporteDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_detalle_guia_transporte_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_detalle_guia_transporte", id.getId());

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
                    ResponseDetalleGuiaTransporteDetalleDTO dto = new ResponseDetalleGuiaTransporteDetalleDTO();
                    dto.setId(((Number) row.get("id_detalle_guia_transporte")).longValue());
                    dto.setIdGuiaTransporte(((Number) row.get("id_guia_transporte")).longValue());
                    dto.setCodigoProducto((String) row.get("producto_codigo"));
                    dto.setNombreProducto((String) row.get("producto_nombre"));
                    dto.setCantidad((Integer) row.get("cantidad"));
                    dto.setUnidadMedida((String) row.get("unidad_medida"));
                    dto.setObservacion((String) row.get("observacion"));
                    dto.setStatus((Boolean) row.get("status"));

                    rp.setDetalleGuia(dto);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Detalle encontrado");
                }
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
    public ResponseDetalleGuiaTransporteMensajeDTO activateD(RequestDetalleGuiaTransporteIdDTO id) {
        ResponseDetalleGuiaTransporteMensajeDTO rp = new ResponseDetalleGuiaTransporteMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_detalle_guia_transporte");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_detalle_guia_transporte", id.getId());

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
    public ResponseDetalleGuiaTransporteMensajeDTO desactivateD(RequestDetalleGuiaTransporteIdDTO id) {
        ResponseDetalleGuiaTransporteMensajeDTO rp = new ResponseDetalleGuiaTransporteMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_detalle_guia_transporte");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_detalle_guia_transporte", id.getId());

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
