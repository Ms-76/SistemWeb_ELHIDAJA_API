package com.elhidaja.apiselhidaja.persistence.repository;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.elhidaja.apiselhidaja.presentation.dto.provincia.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.provincia.Response.*;
import com.elhidaja.apiselhidaja.service.DAO.ProvinciaDAO;
import org.springframework.stereotype.Repository;

@Repository
public class ProvinciaRepository implements ProvinciaDAO {

    private final JdbcTemplate jdbc;

    public ProvinciaRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseProvinciaAllDTO getAllD(RequestProvinciaOptionDTO option) {
        ResponseProvinciaAllDTO rp = new ResponseProvinciaAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_provincias");

            Map<String, Object> inParams = Map.of(
                    "status", option.getEstado());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponseProvinciaDTO> provincias = rows.stream().map(row -> {
                ResponseProvinciaDTO dto = new ResponseProvinciaDTO();
                dto.setId(((Number) row.get("id_provincia")).longValue());
                dto.setDepartamento((String) row.get("departamento"));
                dto.setNombre((String) row.get("nombre"));
                dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();

            rp.setProvincias(provincias);
            rp.setExito(true);
            rp.setCodigo("200");
            rp.setMensaje("Consulta exitosa");
        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener provincias: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseDetalleProvinciaDTO getByIdD(RequestProvinciaFilterDTO id) {
        ResponseDetalleProvinciaDTO rp = new ResponseDetalleProvinciaDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_provincia_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_provincia", id.getId());

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
                    ResponseProvinciaDTO dto = new ResponseProvinciaDTO();
                    dto.setId(((Number) row.get("id_provincia")).longValue());
                    dto.setDepartamento((String) row.get("departamento"));
                    dto.setNombre((String) row.get("nombre"));
                    dto.setStatus((Boolean) row.get("status"));

                    rp.setProvincia(dto);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Provincia encontrada");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró la provincia");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener la provincia: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseProvinciaMensajeDTO activateD(RequestProvinciaIdDTO id) {
        ResponseProvinciaMensajeDTO rp = new ResponseProvinciaMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_provincia");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_provincia", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar la provincia.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Provincia activada correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar la provincia: " + e.getMessage());
        }

        return rp;
    }

    @Override
    public ResponseProvinciaMensajeDTO desactivateD(RequestProvinciaIdDTO id) {
        ResponseProvinciaMensajeDTO rp = new ResponseProvinciaMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_provincia");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_provincia", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar la provincia.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Provincia desactivada correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar la provincia: " + e.getMessage());
        }

        return rp;
    }

    @Override
    public ResponseProvinciaMensajeDTO insertD(RequestProvinciaInsertDTO objProvincia) {
        ResponseProvinciaMensajeDTO rp = new ResponseProvinciaMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_provincia");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", objProvincia.getIdLogin(),
                    "nombre", objProvincia.getNombre(),
                    "id_departamento", objProvincia.getIdDepartamento());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo registrar la provincia.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Provincia registrada correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar la provincia: " + e.getMessage());
        }

        return rp;
    }

    @Override
    public ResponseProvinciaMensajeDTO updateD(RequestProvinciaUpdateDTO objProvincia) {
        ResponseProvinciaMensajeDTO rp = new ResponseProvinciaMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_actualizar_provincia");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", objProvincia.getIdLogin(),
                    "id_provincia", objProvincia.getId(),
                    "nombre", objProvincia.getNombre(),
                    "id_departamento", objProvincia.getIdDepartamento());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo actualizar la provincia.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Provincia actualizada correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al actualizar la provincia: " + e.getMessage());
        }

        return rp;
    }
}
