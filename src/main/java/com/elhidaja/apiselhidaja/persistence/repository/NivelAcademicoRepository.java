package com.elhidaja.apiselhidaja.persistence.repository;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.nivelAcademico.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.nivelAcademico.Response.*;
import com.elhidaja.apiselhidaja.service.DAO.NivelAcademicoDAO;

@Repository
public class NivelAcademicoRepository implements NivelAcademicoDAO {

    private final JdbcTemplate jdbc;

    public NivelAcademicoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseNivelAcademicoAllDTO getAllD(RequestNivelAcademicoOptionDTO option) {
        ResponseNivelAcademicoAllDTO rp = new ResponseNivelAcademicoAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_niveles_academicos");

            Map<String, Object> inParams = Map.of(
                    "status", option.getEstado());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponseNivelAcademicoDTO> niveles = rows.stream().map(row -> {
                ResponseNivelAcademicoDTO dto = new ResponseNivelAcademicoDTO();
                dto.setId(((Number) row.get("id_nivel_academico")).longValue());
                dto.setNombre((String) row.get("nombre"));
                dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();

            rp.setNivelesAcademicos(niveles);

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
    public ResponseDetalleNivelAcademicoDTO getByIdD(RequestNivelAcademicoFilterDTO id) {
        ResponseDetalleNivelAcademicoDTO rp = new ResponseDetalleNivelAcademicoDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_nivel_academico_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_nivel_academico", id.getId());

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
                    ResponseNivelAcademicoDTO dto = new ResponseNivelAcademicoDTO();
                    dto.setId(((Number) row.get("id_nivel_academico")).longValue());
                    dto.setNombre((String) row.get("nombre"));
                    dto.setStatus((Boolean) row.get("status"));

                    rp.setNivelAcademico(dto);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Nivel académico encontrado");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró el nivel académico");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseNivelAcademicoMensajeDTO activateD(RequestNivelAcademicoIdDTO id) {
        ResponseNivelAcademicoMensajeDTO rp = new ResponseNivelAcademicoMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_nivel_academico");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_nivel_academico", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar el nivel académico.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Nivel académico activado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar el nivel académico: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseNivelAcademicoMensajeDTO desactivateD(RequestNivelAcademicoIdDTO id) {
        ResponseNivelAcademicoMensajeDTO rp = new ResponseNivelAcademicoMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_nivel_academico");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_nivel_academico", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar el nivel académico.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Nivel académico desactivado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar el nivel académico: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseNivelAcademicoMensajeDTO updateD(RequestNivelAcademicoUpdateDTO objNivel) {
        ResponseNivelAcademicoMensajeDTO rp = new ResponseNivelAcademicoMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_actualizar_nivel_academico");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", objNivel.getIdLogin(),
                    "id_nivel_academico", objNivel.getId(),
                    "nombre", objNivel.getNombre());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo actualizar el nivel académico.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Nivel académico actualizado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al actualizar el nivel académico: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseNivelAcademicoMensajeDTO insertD(RequestNivelAcademicoInsertDTO objNivel) {
        ResponseNivelAcademicoMensajeDTO rp = new ResponseNivelAcademicoMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_nivel_academico");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", objNivel.getIdLogin(),
                    "nombre", objNivel.getNombre());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo registrar el nivel académico.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Nivel académico registrado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar nivel académico: " + e.getMessage());
        }
        return rp;
    }
}
