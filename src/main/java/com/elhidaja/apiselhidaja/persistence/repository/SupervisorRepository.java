package com.elhidaja.apiselhidaja.persistence.repository;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.supervisor.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.supervisor.Response.*;
import com.elhidaja.apiselhidaja.service.DAO.SupervisorDAO;

@Repository
public class SupervisorRepository implements SupervisorDAO {
    private final JdbcTemplate jdbc;

    public SupervisorRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseSupervisorAllDTO getAllD(RequestSupervisorOptionDTO option) {
        ResponseSupervisorAllDTO rp = new ResponseSupervisorAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_supervisores");

            Map<String, Object> inParams = Map.of(
                    "status", option.getEstado(),
                    "tipo_supervisor", option.getTipoSupervisor());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponseSupervisorDTO> supervisores = rows.stream().map(row -> {
                ResponseSupervisorDTO dto = new ResponseSupervisorDTO();
                dto.setId(((Number) row.get("id_supervisor")).longValue());
                dto.setTipoSupervisor((String) row.get("tipo_supervisor"));
                dto.setDocumento((String) row.get("documento"));
                dto.setNumeroDocumento((String) row.get("numero_documento"));
                dto.setNombre((String) row.get("nombre"));
                dto.setEspecialidad((String) row.get("especialidad"));
                dto.setEmail((String) row.get("email"));
                dto.setTelefono((String) row.get("telefono"));
                dto.setDireccion((String) row.get("direccion"));
                dto.setDepartamento((String) row.get("departamento"));
                dto.setProvincia((String) row.get("provincia"));
                dto.setDistrito((String) row.get("distrito"));
                dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();

            rp.setSupervisores(supervisores);
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
    public ResponseDetalleSupervisorDTO getByIdD(RequestSupervisorFilterDTO id) {
        ResponseDetalleSupervisorDTO rp = new ResponseDetalleSupervisorDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_supervisor_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_supervisor", id.getId());

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
                    ResponseSupervisorDTO dto = new ResponseSupervisorDTO();
                    dto.setId(((Number) row.get("id_supervisor")).longValue());
                    dto.setTipoSupervisor((String) row.get("tipo_supervisor"));
                    dto.setDocumento((String) row.get("documento"));
                    dto.setNumeroDocumento((String) row.get("numero_documento"));
                    dto.setNombre((String) row.get("nombre"));
                    dto.setEspecialidad((String) row.get("especialidad"));
                    dto.setEmail((String) row.get("email"));
                    dto.setTelefono((String) row.get("telefono"));
                    dto.setDireccion((String) row.get("direccion"));
                    dto.setDepartamento((String) row.get("departamento"));
                    dto.setProvincia((String) row.get("provincia"));
                    dto.setDistrito((String) row.get("distrito"));
                    dto.setStatus((Boolean) row.get("status"));

                    rp.setSupervisor(dto);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Supervisor encontrado");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró el supervisor");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseSupervisorMensajeDTO desactivateD(RequestSupervisorIdDTO id) {
        ResponseSupervisorMensajeDTO rp = new ResponseSupervisorMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_supervisor");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_supervisor", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar el supervisor.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Supervisor desactivado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar el supervisor: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseSupervisorMensajeDTO activateD(RequestSupervisorIdDTO id) {
        ResponseSupervisorMensajeDTO rp = new ResponseSupervisorMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_supervisor");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_supervisor", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar el supervisor.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Supervisor activado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar el supervisor: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseSupervisorMensajeDTO insertD(RequestSupervisorInsertDTO objSupervisor) {
        ResponseSupervisorMensajeDTO rp = new ResponseSupervisorMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_supervisor");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", objSupervisor.getIdLogin(),
                    "id_entidad", objSupervisor.getIdEntidad(),
                    "tipo_supervisor", objSupervisor.getTipoSupervisor());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo registrar el supervisor.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Supervisor registrado correctamente.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar supervisor: " + e.getMessage());
        }
        return rp;
    }
}
