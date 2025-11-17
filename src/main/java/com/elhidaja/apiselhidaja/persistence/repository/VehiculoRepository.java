package com.elhidaja.apiselhidaja.persistence.repository;

import com.elhidaja.apiselhidaja.presentation.dto.vehiculo.Response.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.vehiculo.Request.*;
import com.elhidaja.apiselhidaja.service.DAO.VehiculoDAO;

@Repository
public class VehiculoRepository implements VehiculoDAO {
    private final JdbcTemplate jdbc;

    public VehiculoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseVehiculoAllDTO getAllD(RequestVehiculoOptionDTO option) {
        ResponseVehiculoAllDTO rp = new ResponseVehiculoAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_vehiculos");

            Map<String, Object> inParams = Map.of(
                    "status", option.getEstado(),
                    "id_tipo_vehiculo", option.getIdTipoVehiculo());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponseVehiculoDTO> vehiculos = rows.stream().map(row -> {
                ResponseVehiculoDTO dto = new ResponseVehiculoDTO();
                dto.setId(((Number) row.get("id_vehiculo")).longValue());
                dto.setPlaca((String) row.get("placa"));
                dto.setColor((String) row.get("color"));
                dto.setCantidadAsientos((Integer) row.get("cantidad_asientos"));
                dto.setAnio((Integer) row.get("anio"));
                dto.setTipoVehiculo((String) row.get("tipo_vehiculo"));
                dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();

            rp.setVehiculos(vehiculos);
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
    public ResponseDetalleVehiculoDTO getByIdD(RequestVehiculoFilterDTO id) {
        ResponseDetalleVehiculoDTO rp = new ResponseDetalleVehiculoDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_vehiculo_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_vehiculo", id.getId());

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
                    ResponseVehiculoDTO rcd = new ResponseVehiculoDTO();
                    rcd.setId(((Number) row.get("id_vehiculo")).longValue());
                    rcd.setPlaca((String) row.get("placa"));
                    rcd.setColor((String) row.get("color"));
                    rcd.setCantidadAsientos((Integer) row.get("cantidad_asientos"));
                    rcd.setAnio((Integer) row.get("anio"));
                    rcd.setTipoVehiculo((String) row.get("tipo_vehiculo"));
                    rcd.setStatus((Boolean) row.get("status"));

                    rp.setVehiculo(rcd);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Vehículo encontrado");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró el vehículo");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseVehiculoMensajeDTO activateD(RequestVehiculoIdDTO id) {
        ResponseVehiculoMensajeDTO rp = new ResponseVehiculoMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_vehiculo");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_vehiculo", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar el vehículo.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Vehículo activado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar el vehículo: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseVehiculoMensajeDTO desactivateD(RequestVehiculoIdDTO id) {
        ResponseVehiculoMensajeDTO rp = new ResponseVehiculoMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_vehiculo");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_vehiculo", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar el vehículo.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Vehículo desactivado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar el vehículo: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseVehiculoMensajeDTO updateD(RequestVehiculoUpdateDTO objVehiculo) {
        ResponseVehiculoMensajeDTO rp = new ResponseVehiculoMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_actualizar_vehiculo");

            Map<String, Object> inParams = new HashMap<>();
            inParams.put("id_usuario_sign", objVehiculo.getIdLogin());
            inParams.put("id_vehiculo", objVehiculo.getIdVehiculo());
            inParams.put("placa", objVehiculo.getPlaca());
            inParams.put("color", objVehiculo.getColor());
            inParams.put("cantidad_asientos", objVehiculo.getCantidadAsientos());
            inParams.put("anio", objVehiculo.getAnio());
            inParams.put("id_tipo_vehiculo", objVehiculo.getIdTipoVehiculo());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo actualizar el vehículo.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Vehículo actualizado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al actualizar el vehículo: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseVehiculoMensajeDTO insertD(RequestVehiculoInsertDTO objVehiculo) {
        ResponseVehiculoMensajeDTO rp = new ResponseVehiculoMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_vehiculo");

            Map<String, Object> inParams = new HashMap<>();
            inParams.put("id_usuario_sign", objVehiculo.getIdLogin());
            inParams.put("placa", objVehiculo.getPlaca());
            inParams.put("color", objVehiculo.getColor());
            inParams.put("cantidad_asientos", objVehiculo.getCantidadAsientos());
            inParams.put("anio", objVehiculo.getAnio());
            inParams.put("id_tipo_vehiculo", objVehiculo.getIdTipoVehiculo());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo registrar el vehículo.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Vehículo registrado correctamente.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar vehículo : " + e.getMessage());
        }
        return rp;
    }
}
