package com.elhidaja.apiselhidaja.persistence.repository;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.area.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.area.Response.*;
import com.elhidaja.apiselhidaja.service.DAO.AreaDAO;

@Repository
public class AreaRepository implements AreaDAO {
    private final JdbcTemplate jdbc;

    public AreaRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseAreaAllDTO getAllD(RequestAreaOptionDTO option) {
        ResponseAreaAllDTO rp = new ResponseAreaAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_areas");

            Map<String, Object> inParams = Map.of(
                    "status", option.getEstado());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponseAreaDTO> areas = rows.stream().map(row -> {
                ResponseAreaDTO dto = new ResponseAreaDTO();
                dto.setId(((Number) row.get("id_area")).longValue());
                dto.setNombre((String) row.get("nombre"));
                dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();

            rp.setAreas(areas);

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
    public ResponseDetalleAreaDTO getByIdD(RequestAreaFilterDTO id) {
        ResponseDetalleAreaDTO rp = new ResponseDetalleAreaDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_area_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_area", id.getId());

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

                    ResponseAreaDTO rcd = new ResponseAreaDTO();
                    rcd.setId(((Number) row.get("id_area")).longValue());
                    rcd.setNombre((String) row.get("nombre"));
                    rcd.setStatus((Boolean) row.get("status"));

                    rp.setArea(rcd);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Área encontrada");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró el área");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseAreaMensajeDTO activateD(RequestAreaIdDTO id) {
        ResponseAreaMensajeDTO rp = new ResponseAreaMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_area");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_area", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar el área.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Área activada correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar el área: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseAreaMensajeDTO desactivateD(RequestAreaIdDTO id) {

        ResponseAreaMensajeDTO rp = new ResponseAreaMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_area");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_area", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar el área.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Área desactivada correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar el área: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseAreaMensajeDTO updateD(RequestAreaUpdateDTO objArea) {
        ResponseAreaMensajeDTO rp = new ResponseAreaMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_actualizar_area");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", objArea.getIdLogin(),
                    "id_area", objArea.getId(),
                    "nombre", objArea.getNombre());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo actualizar el área.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Área actualizada correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al actualizar el área: " + e.getMessage());
        }
        return rp;

    }

    @Override
    public ResponseAreaMensajeDTO insertD(RequestAreaInsertDTO objArea) {

        ResponseAreaMensajeDTO rp = new ResponseAreaMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_area");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", objArea.getIdLogin(),
                    "nombre", objArea.getNombre());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo registrar el área.");

            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Área registrada correctamente");
            }

        } catch (Exception e) {
            e.printStackTrace();
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar área : " + e.getMessage());
        }
        return rp;
    }
}
