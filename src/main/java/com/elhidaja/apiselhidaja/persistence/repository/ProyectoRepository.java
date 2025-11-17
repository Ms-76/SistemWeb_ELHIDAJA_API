package com.elhidaja.apiselhidaja.persistence.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.proyecto.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.proyecto.Response.*;
import com.elhidaja.apiselhidaja.service.DAO.ProyectoDAO;

@Repository
public class ProyectoRepository implements ProyectoDAO {

    private final JdbcTemplate jdbc;

    public ProyectoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseProyectoAllDTO getAllD(RequestProyectoOptionDTO option) {
        ResponseProyectoAllDTO rp = new ResponseProyectoAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_proyectos");

            Map<String, Object> inParams = Map.of(
                    "status", option.getEstado(),
                    "id_arquitecto", option.getIdArquitecto(),
                    "id_ingeniero", option.getIdIngeniero(),
                    "id_maestro_obra", option.getIdMaestroObra(),
                    "id_supervisor", option.getIdSupervisor());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponseProyectoDTO> proyectos = rows.stream().map(row -> {
                ResponseProyectoDTO dto = new ResponseProyectoDTO();

                dto.setId(((Number) row.get("id_proyecto")).longValue());
                dto.setNombre((String) row.get("nombre"));
                dto.setDescripcion((String) row.get("descripcion"));
                dto.setUbicacion((String) row.get("ubicacion"));
                dto.setFechaInicio(((java.sql.Date) row.get("fecha_inicio")).toLocalDate());
                dto.setFechaFin(((java.sql.Date) row.get("fecha_fin")).toLocalDate());
                dto.setNombreArquitecto((String) row.get("arquitecto"));
                dto.setNombreIngeniero((String) row.get("ingeniero"));
                dto.setNombreMaestroObra((String) row.get("maestro_obra"));
                dto.setNombreSupervisor((String) row.get("supervisor"));
                dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();

            rp.setProyectos(proyectos);
            rp.setExito(true);
            rp.setCodigo("200");
            rp.setMensaje("Consulta exitosa");
        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener los datos: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseDetalleProyectoDTO getByIdD(RequestProyectoFilterDTO id) {
        ResponseDetalleProyectoDTO rp = new ResponseDetalleProyectoDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_proyecto_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_proyecto", id.getId());

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
                    ResponseProyectoDTO dto = new ResponseProyectoDTO();
                    dto.setId(((Number) row.get("id_proyecto")).longValue());
                    dto.setNombre((String) row.get("nombre"));
                    dto.setDescripcion((String) row.get("descripcion"));
                    dto.setUbicacion((String) row.get("ubicacion"));
                    dto.setFechaInicio(((java.sql.Date) row.get("fecha_inicio")).toLocalDate());
                    dto.setFechaFin(((java.sql.Date) row.get("fecha_fin")).toLocalDate());
                    dto.setNombreArquitecto((String) row.get("arquitecto"));
                    dto.setNombreIngeniero((String) row.get("ingeniero"));
                    dto.setNombreMaestroObra((String) row.get("maestro_obra"));
                    dto.setNombreSupervisor((String) row.get("supervisor"));
                    dto.setStatus((Boolean) row.get("status"));

                    rp.setProyecto(dto);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Proyecto encontrado");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró el proyecto");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el proyecto: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseProyectoMensajeDTO activateD(RequestProyectoIdDTO id) {
        ResponseProyectoMensajeDTO rp = new ResponseProyectoMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_proyecto");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_proyecto", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar el proyecto.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Proyecto activado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar el proyecto: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseProyectoMensajeDTO desactivateD(RequestProyectoIdDTO id) {
        ResponseProyectoMensajeDTO rp = new ResponseProyectoMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_proyecto");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_proyecto", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar el proyecto.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Proyecto desactivado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar el proyecto: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseProyectoMensajeDTO insertD(RequestProyectoInsertDTO objProyecto) {
        ResponseProyectoMensajeDTO rp = new ResponseProyectoMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_proyecto");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", objProyecto.getIdLogin(),
                    "nombre", objProyecto.getNombre(),
                    "descripcion", objProyecto.getDescripcion(),
                    "ubicacion", objProyecto.getUbicacion(),
                    "fecha_inicio", objProyecto.getFechaInicio(),
                    "fecha_fin", objProyecto.getFechaFin(),
                    "id_arquitecto", objProyecto.getIdArquitecto(),
                    "id_ingeniero", objProyecto.getIdIngeniero(),
                    "id_maestro_obra", objProyecto.getIdMaestroObra(),
                    "id_supervisor", objProyecto.getIdSupervisor());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo registrar el proyecto.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Proyecto registrado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar el proyecto: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseProyectoMensajeDTO updateD(RequestProyectoUpdateDTO objProyecto) {
        ResponseProyectoMensajeDTO rp = new ResponseProyectoMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_actualizar_proyecto");

            Map<String, Object> inParams = new HashMap<>();
            inParams.put("id_usuario_sign", objProyecto.getIdLogin());
            inParams.put("id_proyecto", objProyecto.getId());
            inParams.put("nombre", objProyecto.getNombre());
            inParams.put("descripcion", objProyecto.getDescripcion());
            inParams.put("ubicacion", objProyecto.getUbicacion());
            inParams.put("fecha_inicio", objProyecto.getFechaInicio());
            inParams.put("fecha_fin", objProyecto.getFechaFin());
            inParams.put("id_arquitecto", objProyecto.getIdArquitecto());
            inParams.put("id_ingeniero", objProyecto.getIdIngeniero());
            inParams.put("id_maestro_obra", objProyecto.getIdMaestroObra());
            inParams.put("id_supervisor", objProyecto.getIdSupervisor());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo actualizar el proyecto.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Proyecto actualizado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al actualizar el proyecto: " + e.getMessage());
        }
        return rp;
    }
}
