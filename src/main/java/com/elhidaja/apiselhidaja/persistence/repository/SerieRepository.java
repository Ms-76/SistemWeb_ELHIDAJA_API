package com.elhidaja.apiselhidaja.persistence.repository;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.serie.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.serie.Response.*;
import com.elhidaja.apiselhidaja.service.DAO.SerieDAO;

@Repository
public class SerieRepository implements SerieDAO {
    private final JdbcTemplate jdbc;

    public SerieRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseSerieAllDTO getAllD(RequestSerieOptionDTO option) {
        ResponseSerieAllDTO rp = new ResponseSerieAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_series");

            Map<String, Object> inParams = Map.of(
                    "status", option.getEstado());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponseSerieDTO> series = rows.stream().map(row -> {
                ResponseSerieDTO dto = new ResponseSerieDTO();
                dto.setId(((Number) row.get("id_serie")).longValue());
                dto.setSerie((String) row.get("serie"));
                dto.setDescripcion((String) row.get("descripcion"));
                dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();

            rp.setSeries(series);
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
    public ResponseDetalleSerieDTO getByIdD(RequestSerieFilterDTO id) {
        ResponseDetalleSerieDTO rp = new ResponseDetalleSerieDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_serie_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_serie", id.getId());

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
                    ResponseSerieDTO rcd = new ResponseSerieDTO();
                    rcd.setId(((Number) row.get("id_serie")).longValue());
                    rcd.setSerie((String) row.get("serie"));
                    rcd.setDescripcion((String) row.get("descripcion"));
                    rcd.setStatus((Boolean) row.get("status"));

                    rp.setSerie(rcd);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Serie encontrada");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró la serie");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseSerieMensajeDTO activateD(RequestSerieIdDTO id) {
        ResponseSerieMensajeDTO rp = new ResponseSerieMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_serie");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_serie", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar la serie.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Serie activada correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar la serie: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseSerieMensajeDTO deactivateD(RequestSerieIdDTO id) {
        ResponseSerieMensajeDTO rp = new ResponseSerieMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_serie");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_serie", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar la serie.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Serie desactivada correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar la serie: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseSerieMensajeDTO updateD(RequestSerieUpdateDTO objSerie) {
        ResponseSerieMensajeDTO rp = new ResponseSerieMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_actualizar_serie");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", objSerie.getIdLogin(),
                    "id_serie", objSerie.getId(),
                    "serie", objSerie.getSerie(),
                    "descripcion", objSerie.getDescripcion());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo actualizar la serie.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Serie actualizada correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al actualizar la serie: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseSerieMensajeDTO insertD(RequestSerieInsertDTO objSerie) {
        ResponseSerieMensajeDTO rp = new ResponseSerieMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_serie");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", objSerie.getIdLogin(),
                    "serie", objSerie.getSerie(),
                    "descripcion", objSerie.getDescripcion());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo registrar la serie.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Serie registrada correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar serie: " + e.getMessage());
        }
        return rp;
    }
}
