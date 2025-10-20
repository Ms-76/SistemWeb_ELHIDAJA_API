package com.elhidaja.apiselhidaja.persistence.repository;

import com.elhidaja.apiselhidaja.presentation.dto.distrito.Response.*;
import com.elhidaja.apiselhidaja.service.DAO.DistritoDAO;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.distrito.Request.*;

@Repository
public class DistritoRepository implements DistritoDAO {
    private final JdbcTemplate jdbc;

    public DistritoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseDistritoAllDTO getAllD(RequestDistritoOptionDTO option) {
        ResponseDistritoAllDTO rp = new ResponseDistritoAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_distritos");

            Map<String, Object> inParams = Map.of(
                    "status", option.getEstado());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponseDistritoDTO> distritos = rows.stream().map(row -> {
                ResponseDistritoDTO dto = new ResponseDistritoDTO();
                dto.setId(((Number) row.get("id_distrito")).longValue());
                dto.setDepartamento((String) row.get("departamento"));
                dto.setProvincia((String) row.get("provincia"));
                dto.setNombre((String) row.get("nombre"));
                dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();

            rp.setDistritos(distritos);

            rp.setExito(true);
            rp.setCodigo("200");
            rp.setMensaje("Consulta exitosa");
        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener datos: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseDetalleDistritoDTO getByIdD(RequestDistritoFilterDTO id) {
        ResponseDetalleDistritoDTO rp = new ResponseDetalleDistritoDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_distrito_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_distrito", id.getId());

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
                    ResponseDistritoDTO dto = new ResponseDistritoDTO();
                    dto.setId(((Number) row.get("id_distrito")).longValue());
                    dto.setDepartamento((String) row.get("departamento"));
                    dto.setProvincia((String) row.get("provincia"));
                    dto.setNombre((String) row.get("nombre"));
                    dto.setStatus((Boolean) row.get("status"));

                    rp.setDistrito(dto);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Distrito encontrado");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró el distrito");
            }
        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseDistritoMensajeDTO activateD(RequestDistritoIdDTO id) {
        ResponseDistritoMensajeDTO rp = new ResponseDistritoMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_distrito");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_distrito", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar el distrito.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Distrito activado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar el distrito: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseDistritoMensajeDTO desactivateD(RequestDistritoIdDTO id) {
        ResponseDistritoMensajeDTO rp = new ResponseDistritoMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_distrito");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_distrito", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar el distrito.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Distrito desactivado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar el distrito: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseDistritoMensajeDTO updateD(RequestDistritoUpdateDTO objDistrito) {
        ResponseDistritoMensajeDTO rp = new ResponseDistritoMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_actualizar_distrito");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", objDistrito.getIdLogin(),
                    "id_distrito", objDistrito.getId(),
                    "nombre", objDistrito.getNombre(),
                    "id_provincia", objDistrito.getIdProvincia());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo actualizar el distrito.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Distrito actualizado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al actualizar el distrito: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseDistritoMensajeDTO insertD(RequestDistritoInsertDTO objDistrito) {
        ResponseDistritoMensajeDTO rp = new ResponseDistritoMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_distrito");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", objDistrito.getIdLogin(),
                    "nombre", objDistrito.getNombre(),
                    "id_provincia", objDistrito.getIdProvincia());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo registrar el distrito.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Distrito registrado correctamente.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar distrito: " + e.getMessage());
        }
        return rp;
    }
}
