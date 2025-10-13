package com.elhidaja.apiselhidaja.persistence.repository;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.oficio.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.oficio.Response.*;
import com.elhidaja.apiselhidaja.service.DAO.OficioDAO;

@Repository
public class OficioRepository implements OficioDAO {

    private final JdbcTemplate jdbc;

    public OficioRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseOficioAllDTO getAllD(RequestOficioOptionDTO option) {
        ResponseOficioAllDTO rp = new ResponseOficioAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_oficios");

            Map<String, Object> inParams = Map.of(
                    "option", option.getEstado());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponseOficioDTO> oficios = rows.stream().map(row -> {
                ResponseOficioDTO dto = new ResponseOficioDTO();
                dto.setId(((Number) row.get("id_oficio")).longValue());
                dto.setNombre((String) row.get("nombre"));
                dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();

            rp.setOficios(oficios);

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
    public ResponseDetalleOficioDTO getByIdD(RequestOficioIdDTO id) {
        ResponseDetalleOficioDTO rp = new ResponseDetalleOficioDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_oficio_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_oficio", id.getId());

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
                    ResponseOficioDTO rcd = new ResponseOficioDTO();
                    rcd.setId(((Number) row.get("id_oficio")).longValue());
                    rcd.setNombre((String) row.get("nombre"));
                    rcd.setStatus((Boolean) row.get("status"));

                    rp.setOficio(rcd);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Oficio encontrado");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró el oficio");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseOficioMensajeDTO activateD(RequestOficioIdDTO id) {
        ResponseOficioMensajeDTO rp = new ResponseOficioMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_oficio");

            Map<String, Object> inParams = Map.of(
                    "id_oficio", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar el oficio.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Oficio activado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar el oficio: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseOficioMensajeDTO deactivateD(RequestOficioIdDTO id) {

        ResponseOficioMensajeDTO rp = new ResponseOficioMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_oficio");

            Map<String, Object> inParams = Map.of(
                    "id_oficio", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar el oficio.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Oficio desactivado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar el oficio: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseOficioMensajeDTO insertD(RequestOficioInsertDTO objOficio) {

        ResponseOficioMensajeDTO rp = new ResponseOficioMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_oficio");

            Map<String, Object> inParams = Map.of(
                    "nombre", objOficio.getNombre());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo registrar el oficio.");

            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Oficio registrado correctamente");
            }

        } catch (Exception e) {
            e.printStackTrace();
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar oficio : " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseOficioMensajeDTO updateD(RequestOficioUpdateDTO objOficio) {
        ResponseOficioMensajeDTO rp = new ResponseOficioMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_actualizar_oficio");

            Map<String, Object> inParams = Map.of(
                    "id_oficio", objOficio.getId(),
                    "nombre", objOficio.getNombre());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo actualizar el oficio.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Oficio actualizado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al actualizar el oficio: " + e.getMessage());
        }
        return rp;

    }

}
