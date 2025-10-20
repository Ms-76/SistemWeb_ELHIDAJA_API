package com.elhidaja.apiselhidaja.persistence.repository;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.tipoOperacion.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.tipoOperacion.Response.*;
import com.elhidaja.apiselhidaja.service.DAO.TipoOperacionDAO;

@Repository
public class TipoOperacionRepository implements TipoOperacionDAO {
    private final JdbcTemplate jdbc;

    public TipoOperacionRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseTipoOperacionAllDTO getAllD(RequestTipoOperacionOptionDTO option) {
        ResponseTipoOperacionAllDTO rp = new ResponseTipoOperacionAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_tipo_operaciones");

            Map<String, Object> inParams = Map.of(
                    "status", option.getEstado());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponseTipoOperacionDTO> tipoOperaciones = rows.stream().map(row -> {
                ResponseTipoOperacionDTO dto = new ResponseTipoOperacionDTO();
                dto.setId(((Number) row.get("id_tipo_operacion")).longValue());
                dto.setNombre((String) row.get("nombre"));
                dto.setAbreviatura((String) row.get("abreviatura"));
                dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();

            rp.setTipoOperaciones(tipoOperaciones);

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
    public ResponseDetalleTipoOperacionDTO getByIdD(RequestTipoOperacionFilterDTO id) {
        ResponseDetalleTipoOperacionDTO rp = new ResponseDetalleTipoOperacionDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_tipo_operacion_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_tipo_operacion", id.getId());

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
                    ResponseTipoOperacionDTO dto = new ResponseTipoOperacionDTO();
                    dto.setId(((Number) row.get("id_tipo_operacion")).longValue());
                    dto.setNombre((String) row.get("nombre"));
                    dto.setAbreviatura((String) row.get("abreviatura"));
                    dto.setStatus((Boolean) row.get("status"));

                    rp.setTipoOperacion(dto);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Tipo de operación encontrada");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró el tipo de operación");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseTipoOperacionMensajeDTO activateD(RequestTipoOperacionIdDTO id) {
        ResponseTipoOperacionMensajeDTO rp = new ResponseTipoOperacionMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_tipo_operacion");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_tipo_operacion", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar el tipo de operación.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Tipo de operación activado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar el tipo de operación: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseTipoOperacionMensajeDTO desactivateD(RequestTipoOperacionIdDTO id) {
        ResponseTipoOperacionMensajeDTO rp = new ResponseTipoOperacionMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_tipo_operacion");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_tipo_operacion", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar el tipo de operación.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Tipo de operación desactivado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar el tipo de operación: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseTipoOperacionMensajeDTO updateD(RequestTipoOperacionUpdateDTO obj) {
        ResponseTipoOperacionMensajeDTO rp = new ResponseTipoOperacionMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_actualizar_tipo_operacion");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", obj.getIdLogin(),
                    "id_tipo_operacion", obj.getId(),
                    "nombre", obj.getNombre(),
                    "abreviatura", obj.getAbreviatura());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo actualizar el tipo de operación.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Tipo de operación actualizado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al actualizar el tipo de operación: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseTipoOperacionMensajeDTO insertD(RequestTipoOperacionInsertDTO obj) {
        ResponseTipoOperacionMensajeDTO rp = new ResponseTipoOperacionMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_tipo_operacion");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", obj.getIdLogin(),
                    "nombre", obj.getNombre(),
                    "abreviatura", obj.getAbreviatura());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo registrar el tipo de operación.");

            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Tipo de operación registrada correctamente.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar tipo de operación: " + e.getMessage());
        }
        return rp;
    }
}
