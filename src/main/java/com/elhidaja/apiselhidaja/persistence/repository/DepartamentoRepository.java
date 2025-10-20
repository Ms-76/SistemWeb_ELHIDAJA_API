package com.elhidaja.apiselhidaja.persistence.repository;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.departamento.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.departamento.Response.*;
import com.elhidaja.apiselhidaja.service.DAO.DepartamentoDAO;

@Repository
public class DepartamentoRepository implements DepartamentoDAO {
    private final JdbcTemplate jdbc;

    public DepartamentoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseDepartamentoAllDTO getAll(RequestDepartamentoOptionDTO option) {
        ResponseDepartamentoAllDTO rp = new ResponseDepartamentoAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_departamentos");

            Map<String, Object> inParams = Map.of("status", option.getEstado());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponseDepartamentoDTO> departamentos = rows.stream().map(row -> {
                ResponseDepartamentoDTO dto = new ResponseDepartamentoDTO();
                dto.setId(((Number) row.get("id_departamento")).longValue());
                dto.setNombre((String) row.get("nombre"));
                dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();

            rp.setDepartamentos(departamentos);
            rp.setExito(true);
            rp.setCodigo("200");
            rp.setMensaje("Consulta exitosa");

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener departamentos: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseDetalleDepartamentoDTO getById(RequestDepartamentoFilterDTO id) {
        ResponseDetalleDepartamentoDTO rp = new ResponseDetalleDepartamentoDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_departamento_por_id");

            Map<String, Object> inParams = Map.of("id_departamento", id.getId());

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
                    ResponseDepartamentoDTO dto = new ResponseDepartamentoDTO();
                    dto.setId(((Number) row.get("id_departamento")).longValue());
                    dto.setNombre((String) row.get("nombre"));
                    dto.setStatus((Boolean) row.get("status"));

                    rp.setDepartamento(dto);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Departamento encontrado");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró el departamento");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el departamento: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseDepartamentoMensajeDTO activate(RequestDepartamentoIdDTO id) {
        ResponseDepartamentoMensajeDTO rp = new ResponseDepartamentoMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_departamento");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_departamento", id.getId());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.values().stream()
                    .filter(v -> v instanceof List && !((List<?>) v).isEmpty())
                    .findFirst()
                    .orElse(null);

            if (resultSet != null) {
                Map<String, Object> errorRow = resultSet.get(0);
                rp.setExito(false);
                rp.setCodigo("400");
                rp.setMensaje((String) errorRow.get("mensaje"));
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Departamento activado correctamente");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar el departamento: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseDepartamentoMensajeDTO desactivate(RequestDepartamentoIdDTO id) {
        ResponseDepartamentoMensajeDTO rp = new ResponseDepartamentoMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_departamento");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_departamento", id.getId());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.values().stream()
                    .filter(v -> v instanceof List && !((List<?>) v).isEmpty())
                    .findFirst()
                    .orElse(null);

            if (resultSet != null) {
                Map<String, Object> errorRow = resultSet.get(0);
                rp.setExito(false);
                rp.setCodigo("400");
                rp.setMensaje((String) errorRow.get("mensaje"));
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Departamento desactivado correctamente");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar el departamento: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseDepartamentoMensajeDTO insert(RequestDepartamentoInsertDTO objDepartamento) {
        ResponseDepartamentoMensajeDTO rp = new ResponseDepartamentoMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_departamento");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", objDepartamento.getIdLogin(),
                    "nombre", objDepartamento.getNombre());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.values().stream()
                    .filter(v -> v instanceof List && !((List<?>) v).isEmpty())
                    .findFirst()
                    .orElse(null);

            if (resultSet != null) {
                Map<String, Object> errorRow = resultSet.get(0);
                rp.setCodigo("400");
                rp.setMensaje((String) errorRow.get("mensaje"));
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Departamento registrado correctamente");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar el departamento: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseDepartamentoMensajeDTO update(RequestDepartamentoUpdateDTO objDepartamento) {
        ResponseDepartamentoMensajeDTO rp = new ResponseDepartamentoMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_actualizar_departamento");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", objDepartamento.getIdLogin(),
                    "id_departamento", objDepartamento.getId(),
                    "nombre", objDepartamento.getNombre());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.values().stream()
                    .filter(v -> v instanceof List && !((List<?>) v).isEmpty())
                    .findFirst()
                    .orElse(null);

            if (resultSet != null) {
                Map<String, Object> errorRow = resultSet.get(0);
                rp.setCodigo("400");
                rp.setMensaje((String) errorRow.get("mensaje"));
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Departamento actualizado correctamente");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al actualizar el departamento: " + e.getMessage());
        }
        return rp;
    }
}
