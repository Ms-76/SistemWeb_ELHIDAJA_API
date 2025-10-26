package com.elhidaja.apiselhidaja.persistence.repository;

import java.time.ZoneId;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaSalida.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaSalida.Response.*;
import com.elhidaja.apiselhidaja.service.DAO.DetalleGuiaSalidaDAO;

@Repository
public class DetalleGuiaSalidaRepository implements DetalleGuiaSalidaDAO {

    private final JdbcTemplate jdbc;

    public DetalleGuiaSalidaRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseDetalleGuiaSalidaAllDTO getAllD(RequestDetalleGuiaSalidaOptionDTO option) {
        ResponseDetalleGuiaSalidaAllDTO rp = new ResponseDetalleGuiaSalidaAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_detalles_guia_salida");

            Map<String, Object> inParams = Map.of(
                    "status", option.getEstado(),
                    "id_almacen_origen", option.getIdAlmacenOrigen(),
                    "tipo_destino", option.getTipoDestino());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            if (rows != null && !rows.isEmpty()) {
                Map<String, Object> firstRow = rows.get(0);

                if (firstRow.containsKey("id_detalle_guia_salida")) {
                    var detalles = rows.stream().map(row -> {
                        ResponseDetalleGuiaSalidaDTOInner dto = new ResponseDetalleGuiaSalidaDTOInner();
                        dto.setIdDetalleGuiaSalida(((Number) row.get("id_detalle_guia_salida")).longValue());
                        dto.setIdGuiaSalida(((Number) row.get("guia_salida")).longValue());
                        dto.setTipoOperacion((String) row.get("tipo_operacion"));
                        dto.setTipoDocumento((String) row.get("tipo_documento"));
                        dto.setCodigoSunat(((Number) row.get("codigo_sunat")).longValue());
                        dto.setCodigoInterno(((Number) row.get("codigo_interno")).longValue());
                        dto.setSerie((String) row.get("serie"));
                        dto.setUltimoCorrelativo(((Number) row.get("correlativo")).longValue());

                        Object fechaObj = row.get("fecha_vencimiento_producto");
                        if (fechaObj instanceof java.sql.Timestamp) {
                            dto.setFechaVencimientoProducto(((java.sql.Timestamp) fechaObj).toLocalDateTime());
                        } else if (fechaObj instanceof java.util.Date) {
                            dto.setFechaVencimientoProducto(((java.util.Date) fechaObj).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
                        } else {
                            dto.setFechaVencimientoProducto(null);
                        }

                        dto.setProducto((String) row.get("producto"));
                        dto.setCantidad(((Number) row.get("cantidad")).longValue());
                        dto.setUnidadMedida((String) row.get("unidad_medida"));
                        dto.setObservacion((String) row.get("observacion"));
                        dto.setStatus((Boolean) row.get("status"));
                        return dto;
                    }).toList();

                    rp.setDetalleGuiasSalida(detalles);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Consulta exitosa");
                } else {
                    rp.setExito(false);
                    rp.setCodigo("404");
                    rp.setMensaje((String) firstRow.get("mensaje"));
                }

            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontraron detalles de guía de salida para los criterios especificados.");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener los datos: " + e.getMessage());
        }

        return rp;
    }

    @Override
    public ResponseDetalleGuiaSalidaDTO getByIdD(RequestDetalleGuiaSalidaFilterDTO id) {
        ResponseDetalleGuiaSalidaDTO rp = new ResponseDetalleGuiaSalidaDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_detalle_guia_salida_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_detalle_guia_salida", id.getId(),
                    "status", 1  // si necesitas filtrar por estado, aquí puedes parametrizar
            );

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
                    ResponseDetalleGuiaSalidaDTOInner dto = new ResponseDetalleGuiaSalidaDTOInner();
                    dto.setIdDetalleGuiaSalida(((Number) row.get("id_detalle_guia_salida")).longValue());
                    dto.setIdGuiaSalida(((Number) row.get("guia_salida")).longValue());
                    dto.setTipoOperacion((String) row.get("tipo_operacion"));
                    dto.setTipoDocumento((String) row.get("tipo_documento"));
                    dto.setCodigoSunat(((Number) row.get("codigo_sunat")).longValue());
                    dto.setCodigoInterno(((Number) row.get("codigo_interno")).longValue());
                    dto.setSerie((String) row.get("serie"));
                    dto.setUltimoCorrelativo(((Number) row.get("correlativo")).longValue());

                    Object fechaObj = row.get("fecha_vencimiento_producto");
                    if (fechaObj instanceof java.sql.Timestamp) {
                        dto.setFechaVencimientoProducto(((java.sql.Timestamp) fechaObj).toLocalDateTime());
                    } else if (fechaObj instanceof java.util.Date) {
                        dto.setFechaVencimientoProducto(((java.util.Date) fechaObj).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
                    } else {
                        dto.setFechaVencimientoProducto(null);
                    }

                    dto.setProducto((String) row.get("producto"));
                    dto.setCantidad(((Number) row.get("cantidad")).longValue());
                    dto.setUnidadMedida((String) row.get("unidad_medida"));
                    dto.setObservacion((String) row.get("observacion"));
                    dto.setStatus((Boolean) row.get("status"));

                    rp.setDetalleGuiaSalida(dto);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Detalle guía salida encontrado");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró el detalle guía salida");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseDetalleGuiaSalidaMensajeDTO activateD(RequestDetalleGuiaSalidaIdDTO id) {
        ResponseDetalleGuiaSalidaMensajeDTO rp = new ResponseDetalleGuiaSalidaMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_detalle_guia_salida");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_detalle_guia_salida", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar el detalle guía salida.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Detalle guía salida activado correctamente.");
            }
        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar el detalle guía salida: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseDetalleGuiaSalidaMensajeDTO desactivateD(RequestDetalleGuiaSalidaIdDTO id) {
        ResponseDetalleGuiaSalidaMensajeDTO rp = new ResponseDetalleGuiaSalidaMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_detalle_guia_salida");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_detalle_guia_salida", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar el detalle guía salida.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Detalle guía salida desactivado correctamente.");
            }
        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar el detalle guía salida: " + e.getMessage());
        }
        return rp;
    }
}
