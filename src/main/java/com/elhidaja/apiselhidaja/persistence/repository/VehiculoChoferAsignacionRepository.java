package com.elhidaja.apiselhidaja.persistence.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.vehiculoChoferAsignacion.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.vehiculoChoferAsignacion.Response.*;

import com.elhidaja.apiselhidaja.service.DAO.VehiculoChoferAsignacionDAO;

@Repository
public class VehiculoChoferAsignacionRepository implements VehiculoChoferAsignacionDAO {
    private final JdbcTemplate jdbc;

    public VehiculoChoferAsignacionRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseVehiculoChoferAsignacionAllDTO getAllD(RequestVehiculoChoferAsignacionOptionDTO option) {
        ResponseVehiculoChoferAsignacionAllDTO rp = new ResponseVehiculoChoferAsignacionAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_vehiculo_chofer_asignaciones");

            Map<String, Object> inParams = Map.of(
                    "status", option.getEstado(),
                    "id_chofer", option.getIdChofer());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponseVehiculoChoferAsignacionDTO> asignaciones = rows.stream().map(row -> {
                ResponseVehiculoChoferAsignacionDTO dto = new ResponseVehiculoChoferAsignacionDTO();
                dto.setId(((Number) row.get("id_asignacion")).longValue());
                dto.setIdVehiculo(((Number) row.get("id_vehiculo")).longValue());
                dto.setPlaca((String) row.get("placa"));
                dto.setIdChofer(((Number) row.get("id_chofer")).longValue());
                dto.setDocumento((String) row.get("documento_chofer"));
                dto.setNumeroDocumento((String) row.get("numero_documento_chofer"));
                dto.setNombre((String) row.get("nombre_chofer"));
                dto.setLicencia((String) row.get("licencia"));
                dto.setEmail((String) row.get("email_chofer"));
                dto.setTelefono((String) row.get("telefono_chofer"));
                dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();

            rp.setAsignaciones(asignaciones);
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
    public ResponseDetalleVehiculoChoferAsignacionDTO getByIdD(RequestVehiculoChoferAsignacionFilterDTO id) {
        ResponseDetalleVehiculoChoferAsignacionDTO rp = new ResponseDetalleVehiculoChoferAsignacionDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_vehiculo_chofer_asignacion_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_asignacion", id.getId());

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
                    ResponseVehiculoChoferAsignacionDTO rcd = new ResponseVehiculoChoferAsignacionDTO();
                    rcd.setId(((Number) row.get("id_asignacion")).longValue());
                    rcd.setIdVehiculo(((Number) row.get("id_vehiculo")).longValue());
                    rcd.setPlaca((String) row.get("placa"));
                    rcd.setIdChofer(((Number) row.get("id_chofer")).longValue());
                    rcd.setDocumento((String) row.get("documento_chofer"));
                    rcd.setNumeroDocumento((String) row.get("numero_documento_chofer"));
                    rcd.setNombre((String) row.get("nombre_chofer"));
                    rcd.setLicencia((String) row.get("licencia"));
                    rcd.setEmail((String) row.get("email_chofer"));
                    rcd.setTelefono((String) row.get("telefono_chofer"));
                    rcd.setStatus((Boolean) row.get("status"));

                    rp.setAsignacion(rcd);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Asignación encontrada");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró la asignación");
            }
        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseVehiculoChoferAsignacionMensajeDTO activateD(RequestVehiculoChoferAsignacionIdDTO id) {
        ResponseVehiculoChoferAsignacionMensajeDTO rp = new ResponseVehiculoChoferAsignacionMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_vehiculo_chofer_asignacion");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_asignacion", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar la asignación.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Asignación activada correctamente.");
            }
        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar la asignación: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseVehiculoChoferAsignacionMensajeDTO desactivateD(RequestVehiculoChoferAsignacionIdDTO id) {
        ResponseVehiculoChoferAsignacionMensajeDTO rp = new ResponseVehiculoChoferAsignacionMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_vehiculo_chofer_asignacion");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_asignacion", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar la asignación.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Asignación desactivada correctamente.");
            }
        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar la asignación: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseVehiculoChoferAsignacionMensajeDTO updateD(RequestVehiculoChoferAsignacionUpdateDTO objAsignacion) {
        ResponseVehiculoChoferAsignacionMensajeDTO rp = new ResponseVehiculoChoferAsignacionMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_actualizar_vehiculo_chofer_asignacion");

            Map<String, Object> inParams = new HashMap<>();
            inParams.put("id_usuario_sign", objAsignacion.getIdLogin());
            inParams.put("id_asignacion", objAsignacion.getIdAsignacion());
            inParams.put("id_vehiculo", objAsignacion.getIdVehiculo());
            inParams.put("id_chofer", objAsignacion.getIdChofer());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo actualizar la asignación.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Asignación actualizada correctamente.");
            }
        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al actualizar la asignación: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseVehiculoChoferAsignacionMensajeDTO insertD(RequestVehiculoChoferAsignacionInsertDTO objAsignacion) {
        ResponseVehiculoChoferAsignacionMensajeDTO rp = new ResponseVehiculoChoferAsignacionMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_vehiculo_chofer_asignacion");

            Map<String, Object> inParams = new HashMap<>();
            inParams.put("id_usuario_sign", objAsignacion.getIdLogin());
            inParams.put("id_vehiculo", objAsignacion.getIdVehiculo());
            inParams.put("id_chofer", objAsignacion.getIdChofer());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo registrar la asignación.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Asignación registrada correctamente.");
            }
        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar la asignación: " + e.getMessage());
        }
        return rp;
    }
}
