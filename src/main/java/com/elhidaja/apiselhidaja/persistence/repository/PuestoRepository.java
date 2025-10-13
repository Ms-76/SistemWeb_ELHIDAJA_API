package com.elhidaja.apiselhidaja.persistence.repository;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.puesto.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.puesto.Response.*;
import com.elhidaja.apiselhidaja.service.DAO.PuestoDAO;

@Repository
public class PuestoRepository implements PuestoDAO {

    private final JdbcTemplate jdbc;

    public PuestoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponsePuestoAllDTO getAllD(RequestPuestoOptionDTO option) {
        ResponsePuestoAllDTO rp = new ResponsePuestoAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_puestos");

            Map<String, Object> inParams = Map.of(
                    "option", option.getEstado());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponsePuestoDTO> puestos = rows.stream().map(row -> {
                ResponsePuestoDTO dto = new ResponsePuestoDTO();
                dto.setId(((Number) row.get("id_puesto")).longValue());
                dto.setNombre((String) row.get("nombre"));
                dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();

            rp.setPuestos(puestos);

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
    public ResponseDetallePuestoDTO getByIdD(RequestPuestoIdDTO id) {
        ResponseDetallePuestoDTO rp = new ResponseDetallePuestoDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_puesto_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_puesto", id.getId());

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

                    ResponsePuestoDTO rpd = new ResponsePuestoDTO();
                    rpd.setId(((Number) row.get("id_puesto")).longValue());
                    rpd.setNombre((String) row.get("nombre"));
                    rpd.setStatus((Boolean) row.get("status"));

                    rp.setPuesto(rpd);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Puesto encontrado");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró el puesto");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponsePuestoMensajeDTO activateD(RequestPuestoIdDTO id) {
        ResponsePuestoMensajeDTO rp = new ResponsePuestoMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_puesto");

            Map<String, Object> inParams = Map.of(
                    "id_puesto", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar el puesto.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Puesto activado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar el puesto: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponsePuestoMensajeDTO desactivateD(RequestPuestoIdDTO id) {

        ResponsePuestoMensajeDTO rp = new ResponsePuestoMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_puesto");

            Map<String, Object> inParams = Map.of(
                    "id_puesto", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar el puesto.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Puesto desactivado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar el puesto: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponsePuestoMensajeDTO updateD(RequestPuestoUpdateDTO objPuesto) {
        ResponsePuestoMensajeDTO rp = new ResponsePuestoMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_actualizar_puesto");

            Map<String, Object> inParams = Map.of(
                    "id_puesto", objPuesto.getId(),
                    "nombre", objPuesto.getNombre());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo actualizar el puesto.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Puesto actualizado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al actualizar el puesto: " + e.getMessage());
        }
        return rp;

    }

    @Override
    public ResponsePuestoMensajeDTO insertD(RequestPuestoInsertDTO objPuesto) {

        ResponsePuestoMensajeDTO rp = new ResponsePuestoMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_puesto");

            Map<String, Object> inParams = Map.of(
                    "nombre", objPuesto.getNombre());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo registrar el puesto.");

            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Puesto registrado correctamente");
            }

        } catch (Exception e) {
            e.printStackTrace();
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar puesto: " + e.getMessage());
        }
        return rp;
    }

}
