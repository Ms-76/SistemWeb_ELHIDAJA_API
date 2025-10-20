package com.elhidaja.apiselhidaja.persistence.repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaEntrada.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaEntrada.Response.*;
import com.elhidaja.apiselhidaja.service.DAO.DetalleGuiaEntradaDAO;

@Repository
public class DetalleGuiaEntradaRepository implements DetalleGuiaEntradaDAO {

    private final JdbcTemplate jdbc;

    public DetalleGuiaEntradaRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseDetalleGuiaEntradaAllDTO getAllD(RequestDetalleGuiaEntradaOptionDTO option) {
        ResponseDetalleGuiaEntradaAllDTO rp = new ResponseDetalleGuiaEntradaAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_detalles_guia_entrada");

            Map<String, Object> inParams = Map.of(
                    "status", option.getEstado(),
                    "id_almacen", option.getIdAlmacen());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            // Si la lista de resultados no está vacía, la revisamos
            if (rows != null && !rows.isEmpty()) {
                // Obtenemos la primera fila para inspeccionarla
                Map<String, Object> firstRow = rows.get(0);

                // Verificamos si contiene los datos esperados ("id_detalle_guia_entrada")
                if (firstRow.containsKey("id_detalle_guia_entrada")) {
                    List<ResponseDetalleGuiaEntradaDTOInner> detalles = rows.stream().map(row -> {
                        ResponseDetalleGuiaEntradaDTOInner dto = new ResponseDetalleGuiaEntradaDTOInner();

                        dto.setIdDetalleGuiaEntrada(((Number) row.get("id_detalle_guia_entrada")).longValue());
                        dto.setIdGuiaEntrada(((Number) row.get("guia_entrada")).longValue());
                        dto.setTipoOperacion((String) row.get("tipo_operacion"));
                        dto.setTipoDocumento((String) row.get("tipo_documento"));
                        dto.setCodigoSunat(((Number) row.get("codigo_sunat")).longValue());
                        dto.setCodigoInterno(((Number) row.get("codigo_interno")).longValue());
                        dto.setSerie((String) row.get("serie"));
                        dto.setUltimoCorrelativo(((Number) row.get("correlativo")).longValue());

                        // Manejo seguro de fecha_vencimiento_producto
                        Object fechaObj = row.get("fecha_vencimiento_producto");
                        if (fechaObj instanceof java.sql.Timestamp) {
                            dto.setFechaVencimientoProducto(((java.sql.Timestamp) fechaObj).toLocalDateTime());
                        } else if (fechaObj instanceof java.util.Date) {
                            dto.setFechaVencimientoProducto(
                                    ((java.util.Date) fechaObj).toInstant().atZone(ZoneId.systemDefault())
                                            .toLocalDateTime());
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

                    rp.setDetalleGuiaEntradas(detalles);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Consulta exitosa");

                } else {
                    // Si no tiene "id_detalle_guia_entrada", es un mensaje de error del SP
                    rp.setExito(false);
                    rp.setCodigo("404");
                    rp.setMensaje((String) firstRow.get("mensaje"));
                }

            } else {
                // El procedimiento no devolvió filas
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontraron detalles de guía de entrada para los criterios especificados.");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener los datos: " + e.getMessage());
        }

        return rp;
    }

    @Override
    public ResponseDetalleGuiaEntradaDTO getByIdD(RequestDetalleGuiaEntradaFilterDTO id) {
        ResponseDetalleGuiaEntradaDTO rp = new ResponseDetalleGuiaEntradaDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_detalle_guia_entrada_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_detalle_guia_entrada", id.getId());

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

                    ResponseDetalleGuiaEntradaDTOInner dto = new ResponseDetalleGuiaEntradaDTOInner();
                    dto.setIdDetalleGuiaEntrada(((Number) row.get("id_detalle_guia_entrada")).longValue());
                    dto.setIdGuiaEntrada(((Number) row.get("guia_entrada")).longValue());
                    dto.setTipoOperacion((String) row.get("tipo_operacion"));
                    dto.setTipoDocumento((String) row.get("tipo_documento"));
                    dto.setCodigoSunat(((Number) row.get("codigo_sunat")).longValue());
                    dto.setCodigoInterno(((Number) row.get("codigo_interno")).longValue());
                    dto.setSerie((String) row.get("serie"));
                    dto.setUltimoCorrelativo((Long) row.get("correlativo"));
                    Object fechaObj = row.get("fecha_vencimiento_producto");
                    if (fechaObj instanceof java.sql.Timestamp) {
                        dto.setFechaVencimientoProducto(((java.sql.Timestamp) fechaObj).toLocalDateTime());
                    } else if (fechaObj instanceof java.util.Date) {
                        dto.setFechaVencimientoProducto(
                                ((java.util.Date) fechaObj).toInstant().atZone(ZoneId.systemDefault())
                                        .toLocalDateTime());
                    } else {
                        dto.setFechaVencimientoProducto(null);
                    }
                    dto.setProducto((String) row.get("producto"));
                    dto.setCantidad(((Number) row.get("cantidad")).longValue());
                    dto.setUnidadMedida((String) row.get("unidad_medida"));
                    dto.setObservacion((String) row.get("observacion"));
                    dto.setStatus((Boolean) row.get("status"));

                    rp.setDetalleGuiaEntrada(dto);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Detalle guía entrada encontrado");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró el detalle guía entrada");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseDetalleGuiaEntradaMensajeDTO activateD(RequestDetalleGuiaEntradaIdDTO id) {
        ResponseDetalleGuiaEntradaMensajeDTO rp = new ResponseDetalleGuiaEntradaMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_detalle_guia_entrada");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_detalle_guia_entrada", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar el detalle guía entrada.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Detalle guía entrada activado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar el detalle guía entrada: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseDetalleGuiaEntradaMensajeDTO desactivateD(RequestDetalleGuiaEntradaIdDTO id) {

        ResponseDetalleGuiaEntradaMensajeDTO rp = new ResponseDetalleGuiaEntradaMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_detalle_guia_entrada");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_detalle_guia_entrada", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar el detalle guía entrada.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Detalle guía entrada desactivado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar el detalle guía entrada: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseDetalleGuiaEntradaMensajeDTO updateD(RequestDetalleGuiaEntradaUpdateDTO objDetalleGuiaEntrada) {
        ResponseDetalleGuiaEntradaMensajeDTO rp = new ResponseDetalleGuiaEntradaMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_actualizar_detalle_guia_entrada");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", objDetalleGuiaEntrada.getIdLogin(),
                    "id_detalle_guia_entrada", objDetalleGuiaEntrada.getIdDetalleGuiaEntrada(),
                    "id_guia_entrada", objDetalleGuiaEntrada.getIdGuiaEntrada(),
                    "fecha_vencimiento_producto", objDetalleGuiaEntrada.getFechaVencimientoProducto(),
                    "id_producto", objDetalleGuiaEntrada.getIdProducto(),
                    "cantidad", objDetalleGuiaEntrada.getCantidad(),
                    "id_unidad_medida", objDetalleGuiaEntrada.getIdUnidadMedida(),
                    "observacion", objDetalleGuiaEntrada.getObservacion());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo actualizar el detalle guía entrada.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Detalle guía entrada actualizado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al actualizar el detalle guía entrada: " + e.getMessage());
        }
        return rp;

    }

    @Override
    public ResponseDetalleGuiaEntradaMensajeDTO insertD(RequestDetalleGuiaEntradaInsertDTO objDetalleGuiaEntrada) {

        ResponseDetalleGuiaEntradaMensajeDTO rp = new ResponseDetalleGuiaEntradaMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_detalle_guia_entrada");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", objDetalleGuiaEntrada.getIdLogin(),
                    "id_guia_entrada", objDetalleGuiaEntrada.getIdGuiaEntrada(),
                    "fecha_vencimiento_producto", objDetalleGuiaEntrada.getFechaVencimientoProducto(),
                    "id_producto", objDetalleGuiaEntrada.getIdProducto(),
                    "cantidad", objDetalleGuiaEntrada.getCantidad(),
                    "id_unidad_medida", objDetalleGuiaEntrada.getIdUnidadMedida(),
                    "observacion", objDetalleGuiaEntrada.getObservacion());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo registrar el detalle guía entrada.");

            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Detalle guía entrada registrado correctamente");
            }

        } catch (Exception e) {
            e.printStackTrace();
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar detalle guía entrada : " + e.getMessage());
        }
        return rp;
    }
}
