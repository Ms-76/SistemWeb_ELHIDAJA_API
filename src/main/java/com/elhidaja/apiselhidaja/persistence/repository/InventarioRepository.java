package com.elhidaja.apiselhidaja.persistence.repository;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.inventario.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.inventario.Response.*;
import com.elhidaja.apiselhidaja.service.DAO.InventarioDAO;

@Repository
public class InventarioRepository implements InventarioDAO {

    private final JdbcTemplate jdbc;

    public InventarioRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseInventarioAllDTO getAllD(RequestInventarioOptionDTO option) {
        ResponseInventarioAllDTO rp = new ResponseInventarioAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_inventarios");

            Map<String, Object> inParams = Map.of(
                    "status", option.getEstado());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponseInventarioDTO> inventarios = rows.stream().map(row -> {
                ResponseInventarioDTO dto = new ResponseInventarioDTO();
                dto.setId(((Number) row.get("id_inventario")).longValue());
                dto.setFecha(((java.sql.Date) row.get("fecha")).toLocalDate());
                dto.setDescripcion((String) row.get("descripcion"));
                dto.setSupervisor(((String) row.get("supervisor")));
                dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();

            rp.setInventarios(inventarios);
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
    public ResponseDetalleInventarioDTO getByIdD(RequestInventarioFilterDTO id) {
        ResponseDetalleInventarioDTO rp = new ResponseDetalleInventarioDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_inventario_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_inventario", id.getId());

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
                    ResponseInventarioDTO dto = new ResponseInventarioDTO();
                    dto.setId(((Number) row.get("id_inventario")).longValue());
                    dto.setFecha(((java.sql.Date) row.get("fecha")).toLocalDate());
                    dto.setDescripcion((String) row.get("descripcion"));
                    dto.setSupervisor(((String) row.get("supervisor")));
                    dto.setStatus((Boolean) row.get("status"));

                    rp.setInventario(dto);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Inventario encontrado");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró el inventario");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseInventarioMensajeDTO desactivateD(RequestInventarioIdDTO id) {
        ResponseInventarioMensajeDTO rp = new ResponseInventarioMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_inventario");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_inventario", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar el inventario.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Inventario desactivado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar el inventario: " + e.getMessage());
        }

        return rp;
    }

    @Override
    public ResponseInventarioMensajeDTO activateD(RequestInventarioIdDTO id) {
        ResponseInventarioMensajeDTO rp = new ResponseInventarioMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_inventario");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_inventario", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar el inventario.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Inventario activado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar el inventario: " + e.getMessage());
        }

        return rp;
    }

    @Override
    public ResponseInventarioMensajeDTO insertD(RequestInventarioInsertDTO objInventario) {
        ResponseInventarioMensajeDTO rp = new ResponseInventarioMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_inventario");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", objInventario.getIdLogin(),
                    "fecha", objInventario.getFecha(),
                    "descripcion", objInventario.getDescripcion(),
                    "id_usuario", objInventario.getIdUsuario());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo registrar el inventario.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Inventario registrado correctamente");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar inventario: " + e.getMessage());
        }

        return rp;
    }

    @Override
    public ResponseInventarioMensajeDTO updateD(RequestInventarioUpdateDTO objInventario) {
        ResponseInventarioMensajeDTO rp = new ResponseInventarioMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_actualizar_inventario");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", objInventario.getIdLogin(),
                    "id_inventario", objInventario.getId(),
                    "fecha", objInventario.getFecha(),
                    "descripcion", objInventario.getDescripcion(),
                    "id_usuario", objInventario.getIdUsuario());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo actualizar el inventario.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Inventario actualizado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al actualizar inventario: " + e.getMessage());
        }

        return rp;
    }
}
