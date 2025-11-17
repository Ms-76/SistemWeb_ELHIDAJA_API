package com.elhidaja.apiselhidaja.persistence.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.arquitecto.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.arquitecto.Request.*;
import com.elhidaja.apiselhidaja.service.DAO.ArquitectoDAO;

@Repository
public class ArquitectoRepository implements ArquitectoDAO {
    private final JdbcTemplate jdbc;

    public ArquitectoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseArquitectoAllDTO getAllD(RequestArquitectoOptionDTO option) {
        ResponseArquitectoAllDTO rp = new ResponseArquitectoAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_arquitectos");

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

            List<ResponseArquitectoDTO> arquitectos = rows.stream().map(row -> {
                ResponseArquitectoDTO dto = new ResponseArquitectoDTO();
                dto.setId(((Number) row.get("id_arquitecto")).longValue());
                dto.setDocumento((String) row.get("documento"));
                dto.setNumeroDocumento((String) row.get("numero_documento"));
                dto.setNombre((String) row.get("nombre"));
                dto.setEmail((String) row.get("email"));
                dto.setTelefono((String) row.get("telefono"));
                dto.setDireccion((String) row.get("direccion"));
                dto.setDepartamento((String) row.get("departamento"));
                dto.setProvincia((String) row.get("provincia"));
                dto.setDistrito((String) row.get("distrito"));
                dto.setEspecialidad((String) row.get("especialidad"));
                dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();

            rp.setArquitectos(arquitectos);
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
    public ResponseDetalleArquitectoDTO getByIdD(RequestArquitectoFilterDTO id) {
        ResponseDetalleArquitectoDTO rp = new ResponseDetalleArquitectoDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_arquitecto_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_arquitecto", id.getId());

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
                    ResponseArquitectoDTO dto = new ResponseArquitectoDTO();
                    dto.setId(((Number) row.get("id_arquitecto")).longValue());
                    dto.setDocumento((String) row.get("documento"));
                    dto.setNumeroDocumento((String) row.get("numero_documento"));
                    dto.setNombre((String) row.get("nombre"));
                    dto.setEmail((String) row.get("email"));
                    dto.setTelefono((String) row.get("telefono"));
                    dto.setDireccion((String) row.get("direccion"));
                    dto.setDepartamento((String) row.get("departamento"));
                    dto.setProvincia((String) row.get("provincia"));
                    dto.setDistrito((String) row.get("distrito"));
                    dto.setEspecialidad((String) row.get("especialidad"));
                    dto.setStatus((Boolean) row.get("status"));

                    rp.setArquitecto(dto);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Arquitecto encontrado");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró el arquitecto");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseArquitectoMensajeDTO activateD(RequestArquitectoIdDTO id) {
        ResponseArquitectoMensajeDTO rp = new ResponseArquitectoMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_arquitecto");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_arquitecto", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar el arquitecto.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Arquitecto activado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar el arquitecto: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseArquitectoMensajeDTO desactivateD(RequestArquitectoIdDTO id) {
        ResponseArquitectoMensajeDTO rp = new ResponseArquitectoMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_arquitecto");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_arquitecto", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar el arquitecto.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Arquitecto desactivado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar el arquitecto: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseArquitectoMensajeDTO updateD(RequestArquitectoUpdateDTO obj) {
        ResponseArquitectoMensajeDTO rp = new ResponseArquitectoMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_actualizar_arquitecto");

            Map<String, Object> params = new HashMap<>();
            params.put("id_usuario_sign", obj.getIdLogin());
            params.put("id_arquitecto", obj.getId());
            params.put("numero_documento", obj.getNumeroDocumento());
            params.put("nombres", obj.getNombres());
            params.put("id_documento_identidad", obj.getIdDocumentoIdentidad());
            params.put("direccion", obj.getDireccion());
            params.put("telefono", obj.getTelefono());
            params.put("email", obj.getEmail());
            params.put("id_distrito", obj.getIdDistrito());
            params.put("especialidad", obj.getEspecialidad());

            Map<String, Object> result = call.execute(params);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.values().stream()
                    .filter(v -> v instanceof List && !((List<?>) v).isEmpty())
                    .findFirst()
                    .orElse(null);

            if (resultSet != null) {
                Map<String, Object> errorRow = resultSet.get(0);
                String mensajeError = (String) errorRow.get("mensaje");
                rp.setCodigo("400");
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo actualizar el arquitecto.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Arquitecto actualizado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al actualizar el arquitecto: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseArquitectoMensajeDTO insertD(RequestArquitectoInsertDTO obj) {
        ResponseArquitectoMensajeDTO rp = new ResponseArquitectoMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_arquitecto");

            Map<String, Object> params = new HashMap<>();
            params.put("id_usuario_sign", obj.getIdLogin());
            params.put("numero_documento", obj.getNumeroDocumento());
            params.put("nombres", obj.getNombres());
            params.put("id_documento_identidad", obj.getIdDocumentoIdentidad());
            params.put("direccion", obj.getDireccion());
            params.put("telefono", obj.getTelefono());
            params.put("email", obj.getEmail());
            params.put("id_distrito", obj.getIdDistrito());
            params.put("especialidad", obj.getEspecialidad());

            Map<String, Object> result = call.execute(params);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.values().stream()
                    .filter(v -> v instanceof List && !((List<?>) v).isEmpty())
                    .findFirst()
                    .orElse(null);

            if (resultSet != null) {
                Map<String, Object> errorRow = resultSet.get(0);
                String mensajeError = (String) errorRow.get("mensaje");
                rp.setCodigo("400");
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo registrar el arquitecto.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Arquitecto registrado correctamente.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar arquitecto: " + e.getMessage());
        }
        return rp;
    }
}
