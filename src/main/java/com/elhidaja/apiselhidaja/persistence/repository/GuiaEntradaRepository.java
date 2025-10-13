package com.elhidaja.apiselhidaja.persistence.repository;

import java.time.ZoneId;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.guiaEntrada.Request.RequestGuiaEntradaIdDTO;
import com.elhidaja.apiselhidaja.presentation.dto.guiaEntrada.Request.RequestGuiaEntradaInsertDTO;
import com.elhidaja.apiselhidaja.presentation.dto.guiaEntrada.Request.RequestGuiaEntradaOptionDTO;
import com.elhidaja.apiselhidaja.presentation.dto.guiaEntrada.Request.RequestGuiaEntradaUpdateDTO;
import com.elhidaja.apiselhidaja.presentation.dto.guiaEntrada.Response.ResponseDetalleGuiaEntradaDTO;
import com.elhidaja.apiselhidaja.presentation.dto.guiaEntrada.Response.ResponseGuiaEntradaAllDTO;
import com.elhidaja.apiselhidaja.presentation.dto.guiaEntrada.Response.ResponseGuiaEntradaDTO;
import com.elhidaja.apiselhidaja.presentation.dto.guiaEntrada.Response.ResponseGuiaEntradaMensajeDTO;
import com.elhidaja.apiselhidaja.service.DAO.GuiaEntradaDAO;

@Repository
public class GuiaEntradaRepository implements GuiaEntradaDAO {
    private final JdbcTemplate jdbc;

    public GuiaEntradaRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseGuiaEntradaAllDTO getAllD(RequestGuiaEntradaOptionDTO option) {
        ResponseGuiaEntradaAllDTO rp = new ResponseGuiaEntradaAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_guias_entrada");

            Map<String, Object> inParams = Map.of(
                    "option", option.getEstado(),
                    "id_almacen", option.getIdAlmacen());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            // Si la lista de resultados no está vacía, la revisamos
            if (rows != null && !rows.isEmpty()) {
                // Obtenemos la primera (y única) fila para inspeccionarla
                Map<String, Object> firstRow = rows.get(0);

                // Verificamos si la respuesta contiene los datos esperados ("id_guia_entrada")
                if (firstRow.containsKey("id_guia_entrada")) {
                    List<ResponseGuiaEntradaDTO> guiasEntrada = rows.stream().map(row -> {
                        ResponseGuiaEntradaDTO dto = new ResponseGuiaEntradaDTO();
                        dto.setId(((Number) row.get("id_guia_entrada")).longValue());
                        dto.setProveedor((String) row.get("proveedor"));
                        dto.setTrabajador((String) row.get("trabajador")); // corregido "trajabador"
                        dto.setDescripcion((String) row.get("descripcion"));

                        Object fechaObj = row.get("fecha_creacion");
                        if (fechaObj instanceof java.sql.Timestamp) {
                            java.sql.Timestamp timestamp = (java.sql.Timestamp) fechaObj;
                            dto.setFechaEntrada(timestamp.toLocalDateTime().toLocalDate());
                        } else if (fechaObj instanceof java.util.Date) {
                            java.util.Date fecha = (java.util.Date) fechaObj;
                            dto.setFechaEntrada(fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        } else {
                            dto.setFechaEntrada(null);
                        }
                        return dto;
                    }).toList();

                    rp.setGuiasEntrada(guiasEntrada);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Consulta exitosa");
                } else {
                    // Si no tiene "id_guia_entrada", es el mensaje de error del SP
                    rp.setExito(false);
                    rp.setCodigo("404");
                    rp.setMensaje((String) firstRow.get("mensaje"));
                }
            } else {
                // El procedimiento no devolvió filas
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontraron guías de entrada para los criterios especificados.");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener los datos: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseDetalleGuiaEntradaDTO getByIdD(RequestGuiaEntradaIdDTO id) {
        ResponseDetalleGuiaEntradaDTO rp = new ResponseDetalleGuiaEntradaDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_guia_entrada_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_guia_entrada", id.getId());

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

                    ResponseGuiaEntradaDTO rcd = new ResponseGuiaEntradaDTO();
                    rcd.setId(((Number) row.get("id_guia_entrada")).longValue());
                    rcd.setProveedor(((String) row.get("proveedor")));
                    rcd.setTrabajador(((String) row.get("trajabador")));
                    rcd.setDescripcion((String) row.get("descripcion"));
                    Object fechaObj = row.get("fecha_creacion");
                    if (fechaObj instanceof java.sql.Timestamp) {
                        java.sql.Timestamp timestamp = (java.sql.Timestamp) fechaObj;
                        rcd.setFechaEntrada(timestamp.toLocalDateTime().toLocalDate());
                    } else if (fechaObj instanceof java.util.Date) {
                        java.util.Date fecha = (java.util.Date) fechaObj;
                        rcd.setFechaEntrada(fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                    } else {
                        rcd.setFechaEntrada(null);
                    }

                    rp.setGuiaEntrada(rcd);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Guía encontrada");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró la guía de entrada");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseGuiaEntradaMensajeDTO activateD(RequestGuiaEntradaIdDTO id) {
        ResponseGuiaEntradaMensajeDTO rp = new ResponseGuiaEntradaMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_guia_entrada");

            Map<String, Object> inParams = Map.of(
                    "id_guia_entrada", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar la guía de entrada.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Guía activada correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar la guía: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseGuiaEntradaMensajeDTO desactivateD(RequestGuiaEntradaIdDTO id) {

        ResponseGuiaEntradaMensajeDTO rp = new ResponseGuiaEntradaMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_guia_entrada");

            Map<String, Object> inParams = Map.of(
                    "id_guia_entrada", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar la guía de entrada.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Guía desactivada correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar la guía: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseGuiaEntradaMensajeDTO updateD(RequestGuiaEntradaUpdateDTO objGuiaEntrada) {
        ResponseGuiaEntradaMensajeDTO rp = new ResponseGuiaEntradaMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_actualizar_guia_entrada");

            Map<String, Object> inParams = Map.of(
                    "id_guia_entrada", objGuiaEntrada.getId(),
                    "descripcion", objGuiaEntrada.getDescripcion());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo actualizar la guía de entrada.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Guía actualizada correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al actualizar la guía: " + e.getMessage());
        }
        return rp;

    }

    @Override
    public ResponseGuiaEntradaMensajeDTO insertD(RequestGuiaEntradaInsertDTO objGuiaEntrada) {

        ResponseGuiaEntradaMensajeDTO rp = new ResponseGuiaEntradaMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_guia_entrada");

            Map<String, Object> inParams = Map.of(
                    "id_proveedor", objGuiaEntrada.getIdProveedor(),
                    "id_usuario", objGuiaEntrada.getIdUsuario(),
                    "descripcion", objGuiaEntrada.getDescripcion());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo registrar la guía de entrada.");

            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Guía registrada correctamente");
            }

        } catch (Exception e) {
            e.printStackTrace();
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar guía de entrada : " + e.getMessage());
        }
        return rp;
    }

}
