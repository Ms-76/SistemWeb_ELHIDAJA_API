package com.elhidaja.apiselhidaja.persistence.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.maestroobra.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.maestroobra.Request.*;
import com.elhidaja.apiselhidaja.service.DAO.MaestroObraDAO;

@Repository
public class MaestroObraRepository implements MaestroObraDAO {
    private final JdbcTemplate jdbc;

    public MaestroObraRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseMaestroObraAllDTO getAllD(RequestMaestroObraOptionDTO option) {
        ResponseMaestroObraAllDTO rp = new ResponseMaestroObraAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_maestros_obra");

            Map<String, Object> inParams = Map.of(
                    "status", option.getEstado());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponseMaestroObraDTO> maestros = rows.stream().map(row -> {
                ResponseMaestroObraDTO dto = new ResponseMaestroObraDTO();
                dto.setId(((Number) row.get("id_maestro_obra")).longValue());
                dto.setDocumento((String) row.get("documento"));
                dto.setNumeroDocumento((String) row.get("numero_documento"));
                dto.setNombre((String) row.get("nombre"));
                dto.setEmail((String) row.get("email"));
                dto.setTelefono((String) row.get("telefono"));
                dto.setDireccion((String) row.get("direcion"));
                dto.setDepartamento((String) row.get("departamento"));
                dto.setProvincia((String) row.get("provincia"));
                dto.setDistrito((String) row.get("distrito"));
                dto.setExperienciaAnios((Integer) row.get("experiencia_anios"));
                dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();

            rp.setMaestrosObra(maestros);
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
    public ResponseDetalleMaestroObraDTO getByIdD(RequestMaestroObraFilterDTO id) {
        ResponseDetalleMaestroObraDTO rp = new ResponseDetalleMaestroObraDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_maestro_obra_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_maestro_obra", id.getId());

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
                    ResponseMaestroObraDTO dto = new ResponseMaestroObraDTO();
                    dto.setId(((Number) row.get("id_maestro_obra")).longValue());
                    dto.setDocumento((String) row.get("documento"));
                    dto.setNumeroDocumento((String) row.get("numero_documento"));
                    dto.setNombre((String) row.get("nombre"));
                    dto.setEmail((String) row.get("email"));
                    dto.setTelefono((String) row.get("telefono"));
                    dto.setDireccion((String) row.get("direcion"));
                    dto.setDepartamento((String) row.get("departamento"));
                    dto.setProvincia((String) row.get("provincia"));
                    dto.setDistrito((String) row.get("distrito"));
                    dto.setExperienciaAnios((Integer) row.get("experiencia_anios"));
                    dto.setStatus((Boolean) row.get("status"));

                    rp.setMaestroObra(dto);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Maestro de obra encontrado");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró el maestro de obra");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseMaestroObraMensajeDTO activateD(RequestMaestroObraIdDTO id) {
        ResponseMaestroObraMensajeDTO rp = new ResponseMaestroObraMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_maestro_obra");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_maestro_obra", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar el maestro de obra.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Maestro de obra activado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar el maestro de obra: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseMaestroObraMensajeDTO desactivateD(RequestMaestroObraIdDTO id) {
        ResponseMaestroObraMensajeDTO rp = new ResponseMaestroObraMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_maestro_obra");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_maestro_obra", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar el maestro de obra.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Maestro de obra desactivado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar el maestro de obra: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseMaestroObraMensajeDTO updateD(RequestMaestroObraUpdateDTO obj) {
        ResponseMaestroObraMensajeDTO rp = new ResponseMaestroObraMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_actualizar_maestro_obra");

            Map<String, Object> params = new HashMap<>();
            params.put("id_usuario_sign", obj.getIdLogin());
            params.put("id_maestro_obra", obj.getId());
            params.put("numero_documento", obj.getNumeroDocumento());
            params.put("nombres", obj.getNombres());
            params.put("id_documento_identidad", obj.getIdDocumentoIdentidad());
            params.put("direccion", obj.getDireccion());
            params.put("telefono", obj.getTelefono());
            params.put("email", obj.getEmail());
            params.put("id_distrito", obj.getIdDistrito());
            params.put("experiencia_anios", obj.getExperienciaAnios());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo actualizar el maestro de obra.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Maestro de obra actualizado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al actualizar el maestro de obra: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseMaestroObraMensajeDTO insertD(RequestMaestroObraInsertDTO obj) {
        ResponseMaestroObraMensajeDTO rp = new ResponseMaestroObraMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_maestro_obra");

            Map<String, Object> params = new HashMap<>();
            params.put("id_usuario_sign", obj.getIdLogin());
            params.put("numero_documento", obj.getNumeroDocumento());
            params.put("nombres", obj.getNombres());
            params.put("id_documento_identidad", obj.getIdDocumentoIdentidad());
            params.put("direccion", obj.getDireccion());
            params.put("telefono", obj.getTelefono());
            params.put("email", obj.getEmail());
            params.put("id_distrito", obj.getIdDistrito());
            params.put("experiencia_anios", obj.getExperienciaAnios());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo registrar el maestro de obra.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Maestro de obra registrado correctamente.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar maestro de obra: " + e.getMessage());
        }
        return rp;
    }
}
