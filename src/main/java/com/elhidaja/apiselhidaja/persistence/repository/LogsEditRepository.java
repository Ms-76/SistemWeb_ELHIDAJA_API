package com.elhidaja.apiselhidaja.persistence.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.LogsEdit.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.LogsEdit.Response.*;
import com.elhidaja.apiselhidaja.service.DAO.LogsEditDAO;

@Repository
public class LogsEditRepository implements LogsEditDAO {
    private final JdbcTemplate jdbc;

    public LogsEditRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseLogsAllDTO getAllD(RequestLogsOptionDTO option) {
        ResponseLogsAllDTO rp = new ResponseLogsAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_logs");

            // Parámetros de entrada del SP
            Map<String, Object> inParams = new HashMap<>();
            inParams.put("id_usuario", option.getIdUsuario());
            inParams.put("fecha_inicio", option.getFechaInicio() != null ? option.getFechaInicio() : 0);
            inParams.put("fecha_fin", option.getFechaFin() != null ? option.getFechaFin() : 0);
            // Ejecutar el SP
            Map<String, Object> result = call.execute(inParams);

            // Obtener los datos
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            // Convertir cada fila en un ResponseLogDTO
            List<ResponseLogDTO> logs = rows.stream().map(row -> {
                ResponseLogDTO dto = new ResponseLogDTO();
                dto.setId(((Number) row.get("id_logs_edit")).longValue());
                dto.setIdUsuario(((Number) row.get("id_usuario")).longValue());
                dto.setTabla((String) row.get("tabla"));
                dto.setTipoAccion((String) row.get("tipo_accion"));
                dto.setCamposAfectados((String) row.get("campos_afectados"));
                dto.setFechaAccion(row.get("fecha_accion").toString());
                return dto;
            }).toList();

            rp.setLogs(logs);
            rp.setExito(true);
            rp.setCodigo("200");
            rp.setMensaje("Consulta exitosa");
        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener los logs: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseDetalleLogDTO getByIdD(RequestLogIdDTO id) {
        ResponseDetalleLogDTO rp = new ResponseDetalleLogDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_log_edit_por_id");

            // Parámetros de entrada del SP
            Map<String, Object> inParams = Map.of(
                    "id_logs_edit", id.getId());

            // Ejecutar el SP
            Map<String, Object> result = call.execute(inParams);

            // Obtener los datos
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            if (rows != null && !rows.isEmpty()) {
                Map<String, Object> row = rows.get(0);

                if (row.containsKey("exito")) {
                    rp.setExito((Boolean) row.get("exito"));
                    rp.setMensaje((String) row.get("mensaje"));
                    rp.setCodigo("404");
                } else {
                    ResponseLogDTO rld = new ResponseLogDTO();
                    rld.setId(((Number) row.get("id_logs_edit")).longValue());
                    rld.setIdUsuario(((Number) row.get("id_usuario")).longValue());
                    rld.setTabla((String) row.get("tabla"));
                    rld.setTipoAccion((String) row.get("tipo_accion"));
                    rld.setCamposAfectados((String) row.get("campos_afectados"));
                    rld.setFechaAccion(row.get("fecha_accion").toString());

                    rp.setLog(rld);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Log encontrado");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró el log solicitado");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el log: " + e.getMessage());
        }
        return rp;
    }
}
