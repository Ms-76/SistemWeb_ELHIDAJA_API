package com.elhidaja.apiselhidaja.persistence.repository;

import com.elhidaja.apiselhidaja.presentation.dto.ingeniero.Response.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.ingeniero.Request.*;
import com.elhidaja.apiselhidaja.service.DAO.IngenieroDAO;

@Repository
public class IngenieroRepository implements IngenieroDAO {
    private final JdbcTemplate jdbc;

    public IngenieroRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseIngenieroAllDTO getAllD(RequestIngenieroOptionDTO option) {
        ResponseIngenieroAllDTO rp = new ResponseIngenieroAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_ingenieros");

            Map<String, Object> inParams = Map.of(
                    "status", option.getEstado(),
                    "id_distrito", option.getIdDistrito(),
                    "id_provincia", option.getIdProvincia(),
                    "id_departamento", option.getIdDepartamento(),
                    "id_documento_identidad", option.getIdDocumentoIdentidad()
            );

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponseIngenieroDTO> ingenieros = rows.stream().map(row -> {
                ResponseIngenieroDTO dto = new ResponseIngenieroDTO();
                dto.setId(((Number) row.get("id_ingeniero")).longValue());
                dto.setDocumento((String) row.get("documento"));
                dto.setNumeroDocumento((String) row.get("numero_documento"));
                dto.setNombre((String) row.get("nombre"));
                dto.setEmail((String) row.get("email"));
                dto.setTelefono((String) row.get("telefono"));
                dto.setDireccion((String) row.get("direcion"));
                dto.setDepartamento((String) row.get("departamento"));
                dto.setProvincia((String) row.get("provincia"));
                dto.setDistrito((String) row.get("distrito"));
                dto.setEspecialidad((String) row.get("especialidad"));
                dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();

            rp.setIngenieros(ingenieros);
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
    public ResponseDetalleIngenieroDTO getByIdD(RequestIngenieroFilterDTO id) {
        ResponseDetalleIngenieroDTO rp = new ResponseDetalleIngenieroDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_ingeniero_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_ingeniero", id.getId());

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
                    ResponseIngenieroDTO rcd = new ResponseIngenieroDTO();
                    rcd.setId(((Number) row.get("id_ingeniero")).longValue());
                    rcd.setDocumento((String) row.get("documento"));
                    rcd.setNumeroDocumento((String) row.get("numero_documento"));
                    rcd.setNombre((String) row.get("nombre"));
                    rcd.setEmail((String) row.get("email"));
                    rcd.setTelefono((String) row.get("telefono"));
                    rcd.setDireccion((String) row.get("direcion"));
                    rcd.setDepartamento((String) row.get("departamento"));
                    rcd.setProvincia((String) row.get("provincia"));
                    rcd.setDistrito((String) row.get("distrito"));
                    rcd.setStatus((Boolean) row.get("status"));
                    rcd.setEspecialidad((String) row.get("especialidad"));
                    rcd.setStatus((Boolean) row.get("status"));

                    rp.setIngeniero(rcd);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Ingeniero encontrado");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró el ingeniero");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseIngenieroMensajeDTO activateD(RequestIngenieroIdDTO id) {
        ResponseIngenieroMensajeDTO rp = new ResponseIngenieroMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_ingeniero");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_ingeniero", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar el ingeniero.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Ingeniero activado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar el ingeniero: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseIngenieroMensajeDTO desactivateD(RequestIngenieroIdDTO id) {
        ResponseIngenieroMensajeDTO rp = new ResponseIngenieroMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_ingeniero");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_ingeniero", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar el ingeniero.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Ingeniero desactivado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar el ingeniero: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseIngenieroMensajeDTO updateD(RequestIngenieroUpdateDTO objIngeniero) {
        ResponseIngenieroMensajeDTO rp = new ResponseIngenieroMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_actualizar_ingeniero");

            Map<String, Object> inParams = new HashMap<>();
            inParams.put("id_usuario_sign", objIngeniero.getIdLogin());
            inParams.put("id_ingeniero", objIngeniero.getId());
            inParams.put("numero_documento", objIngeniero.getNumeroDocumento());
            inParams.put("nombres", objIngeniero.getNombres());
            inParams.put("id_documento_identidad", objIngeniero.getIdDocumentoIdentidad());
            inParams.put("direccion", objIngeniero.getDireccion());
            inParams.put("telefono", objIngeniero.getTelefono());
            inParams.put("email", objIngeniero.getEmail());
            inParams.put("id_distrito", objIngeniero.getIdDistrito());
            inParams.put("especialidad", objIngeniero.getEspecialidad());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo actualizar el ingeniero.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Ingeniero actualizado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al actualizar el ingeniero: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseIngenieroMensajeDTO insertD(RequestIngenieroInsertDTO objIngeniero) {
        ResponseIngenieroMensajeDTO rp = new ResponseIngenieroMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_ingeniero");

            Map<String, Object> inParams = new HashMap<>();
            inParams.put("id_usuario_sign", objIngeniero.getIdLogin());
            inParams.put("numero_documento", objIngeniero.getNumeroDocumento());
            inParams.put("nombres", objIngeniero.getNombres());
            inParams.put("id_documento_identidad", objIngeniero.getIdDocumentoIdentidad());
            inParams.put("direccion", objIngeniero.getDireccion());
            inParams.put("telefono", objIngeniero.getTelefono());
            inParams.put("email", objIngeniero.getEmail());
            inParams.put("id_distrito", objIngeniero.getIdDistrito());
            inParams.put("especialidad", objIngeniero.getEspecialidad());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo registrar el ingeniero.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Ingeniero registrado correctamente.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar ingeniero : " + e.getMessage());
        }
        return rp;
    }
}
