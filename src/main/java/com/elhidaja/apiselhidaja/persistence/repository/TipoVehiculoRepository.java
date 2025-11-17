package com.elhidaja.apiselhidaja.persistence.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.tipoVehiculo.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.tipoVehiculo.Response.*;
import com.elhidaja.apiselhidaja.service.DAO.TipoVehiculoDAO;

@Repository
public class TipoVehiculoRepository implements TipoVehiculoDAO{

      private final JdbcTemplate jdbc;

    public TipoVehiculoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseTipoVehiculoAllDTO getAllD(RequestTipoVehiculoOptionDTO option) {
        ResponseTipoVehiculoAllDTO rp = new ResponseTipoVehiculoAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_tipos_vehiculo");

            Map<String, Object> inParams = Map.of(
                    "status", option.getEstado()
            );

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponseTipoVehiculoDTO> tipos = rows.stream().map(row -> {
                ResponseTipoVehiculoDTO dto = new ResponseTipoVehiculoDTO();
                dto.setId(((Number) row.get("id_tipo_vehiculo")).longValue());
                dto.setNombre((String) row.get("nombre"));
                dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();

            rp.setTiposVehiculo(tipos);
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
    public ResponseDetalleTipoVehiculoDTO getByIdD(RequestTipoVehiculoFilterDTO id) {
        ResponseDetalleTipoVehiculoDTO rp = new ResponseDetalleTipoVehiculoDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_tipo_vehiculo_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_tipo_vehiculo", id.getId()
            );

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
                    ResponseTipoVehiculoDTO rcd = new ResponseTipoVehiculoDTO();
                    rcd.setId(((Number) row.get("id_tipo_vehiculo")).longValue());
                    rcd.setNombre((String) row.get("nombre"));
                    rcd.setStatus((Boolean) row.get("status"));

                    rp.setTipoVehiculo(rcd);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Tipo de vehículo encontrado");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró el tipo de vehículo");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseTipoVehiculoMensajeDTO activateD(RequestTipoVehiculoIdDTO id) {
        ResponseTipoVehiculoMensajeDTO rp = new ResponseTipoVehiculoMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_tipo_vehiculo");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_tipo_vehiculo", id.getId()
            );

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar el tipo de vehículo.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Tipo de vehículo activado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar el tipo de vehículo: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseTipoVehiculoMensajeDTO desactivateD(RequestTipoVehiculoIdDTO id) {
        ResponseTipoVehiculoMensajeDTO rp = new ResponseTipoVehiculoMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_tipo_vehiculo");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_tipo_vehiculo", id.getId()
            );

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar el tipo de vehículo.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Tipo de vehículo desactivado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar el tipo de vehículo: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseTipoVehiculoMensajeDTO updateD(RequestTipoVehiculoUpdateDTO obj) {
        ResponseTipoVehiculoMensajeDTO rp = new ResponseTipoVehiculoMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_actualizar_tipo_vehiculo");

            Map<String, Object> inParams = new HashMap<>();
            inParams.put("id_usuario_sign", obj.getIdLogin());
            inParams.put("id_tipo_vehiculo", obj.getId());
            inParams.put("nombre", obj.getNombre());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo actualizar el tipo de vehículo.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Tipo de vehículo actualizado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al actualizar el tipo de vehículo: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseTipoVehiculoMensajeDTO insertD(RequestTipoVehiculoInsertDTO obj) {
        ResponseTipoVehiculoMensajeDTO rp = new ResponseTipoVehiculoMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_tipo_vehiculo");

            Map<String, Object> inParams = new HashMap<>();
            inParams.put("id_usuario_sign", obj.getIdLogin());
            inParams.put("nombre", obj.getNombre());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo registrar el tipo de vehículo.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Tipo de vehículo registrado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar tipo de vehículo: " + e.getMessage());
        }
        return rp;
    }
}
