package com.elhidaja.apiselhidaja.persistence.repository;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.transporteDetalle.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.transporteDetalle.Response.*;
import com.elhidaja.apiselhidaja.service.DAO.TransporteDetalleDAO;

@Repository
public class TransporteDetalleRepository implements TransporteDetalleDAO {

    private final JdbcTemplate jdbc;

    public TransporteDetalleRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseTransporteDetalleAllDTO getAllD(RequestTransporteDetalleOptionDTO option) {
        ResponseTransporteDetalleAllDTO rp = new ResponseTransporteDetalleAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_transporte_detalles");

            Map<String, Object> inParams = Map.of(
                    "status", option.getEstado(),
                    "id_tipo_pasajero", option.getIdTipoPasajero(),
                    "fecha_inicio", option.getFechaInicio(),
                    "fecha_fin", option.getFechaFin());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponseTransporteDetalleDTO> detalles = rows.stream().map(row -> {
                ResponseTransporteDetalleDTO dto = new ResponseTransporteDetalleDTO();
                dto.setIdDetalle(((Number) row.get("id_detalle")).longValue());
                dto.setIdTransporte(((Number) row.get("id_transporte")).longValue());
                dto.setTipoTransporte((String) row.get("tipo_transporte"));
                dto.setTipoPasajero((String) row.get("tipo_pasajero"));
                dto.setIdPasajero(row.get("id_pasajero").toString());
                dto.setNombre((String) row.get("nombre_pasajero"));
                dto.setCostoPasaje(((Number) row.get("costo_pasaje")).doubleValue());
                dto.setBulto((Boolean) row.get("bulto"));
                dto.setCostoBulto(((Number) row.get("costo_bulto")).doubleValue());
                dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();

            rp.setDetalles(detalles);
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
    public ResponseDetalleTransporteDetalleDTO getByIdD(RequestTransporteDetalleFilterDTO id) {
        ResponseDetalleTransporteDetalleDTO rp = new ResponseDetalleTransporteDetalleDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_transporte_detalle_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_detalle", id.getId());

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
                    ResponseTransporteDetalleDTO rcd = new ResponseTransporteDetalleDTO();
                    rcd.setIdDetalle(((Number) row.get("id_detalle")).longValue());
                    rcd.setIdTransporte(((Number) row.get("id_transporte")).longValue());
                    rcd.setTipoTransporte((String) row.get("tipo_transporte"));
                    rcd.setTipoPasajero((String) row.get("tipo_pasajero"));
                    rcd.setIdPasajero(row.get("id_pasajero").toString());
                    rcd.setNombre((String) row.get("nombre_pasajero"));
                    rcd.setCostoPasaje(((Number) row.get("costo_pasaje")).doubleValue());
                    rcd.setBulto((Boolean) row.get("bulto"));
                    rcd.setCostoBulto(((Number) row.get("costo_bulto")).doubleValue());
                    rcd.setStatus((Boolean) row.get("status"));

                    rp.setDetalle(rcd);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Detalle encontrado");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró el detalle");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseTransporteDetalleMensajeDTO activateD(RequestTransporteDetalleIdDTO id) {
        ResponseTransporteDetalleMensajeDTO rp = new ResponseTransporteDetalleMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_transporte_detalle");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_detalle", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar el detalle.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Detalle activado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar el detalle: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseTransporteDetalleMensajeDTO desactivateD(RequestTransporteDetalleIdDTO id) {
        ResponseTransporteDetalleMensajeDTO rp = new ResponseTransporteDetalleMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_transporte_detalle");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_detalle", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar el detalle.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Detalle desactivado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar el detalle: " + e.getMessage());
        }
        return rp;
    }

}
