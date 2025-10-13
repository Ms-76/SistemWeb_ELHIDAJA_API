package com.elhidaja.apiselhidaja.persistence.repository;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;
import com.elhidaja.apiselhidaja.presentation.dto.loginLogs.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.loginLogs.Response.*;
import com.elhidaja.apiselhidaja.service.DAO.LoginLogsDAO;

@Repository
public class LoginLogsRepository implements LoginLogsDAO {
     private final JdbcTemplate jdbc;

    public LoginLogsRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseLoginLogsAllDTO getAll(RequestLoginLogsOptionDTO option) {
        ResponseLoginLogsAllDTO rp = new ResponseLoginLogsAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_login_logs");

            Map<String, Object> inParams = Map.of(
                    "option", option.getEstado());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponseLoginLogDTO> logs = rows.stream().map(row -> {
                ResponseLoginLogDTO dto = new ResponseLoginLogDTO();
                dto.setId(((Number) row.get("id_log")).longValue());
                dto.setEmail((String) row.get("usuario"));
                dto.setFecha(((Timestamp) row.get("fecha")).toLocalDateTime());
                //dto.setFecha((java.time.LocalDateTime) row.get("fecha"));
                dto.setSuccess((Boolean) row.get("success"));
                return dto;
            }).toList();

            rp.setLogs(logs);
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
    public ResponseLoginLogByIdDTO getById(RequestLoginLogIdDTO id) {
          ResponseLoginLogByIdDTO rp = new ResponseLoginLogByIdDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_login_por_id");

           Map<String, Object> inParams = Map.of(
                    "id_usuario", id.getId());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponseLoginLogDTO> logs = rows.stream().map(row -> {
                ResponseLoginLogDTO dto = new ResponseLoginLogDTO();
                dto.setId(((Number) row.get("id_log")).longValue());
                dto.setEmail((String) row.get("usuario"));
                dto.setFecha(((Timestamp) row.get("fecha")).toLocalDateTime());
                //dto.setFecha((java.time.LocalDateTime) row.get("fecha"));
                dto.setSuccess((Boolean) row.get("success"));
                return dto;
            }).toList();

            rp.setUserLogs(logs);
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
    public ResponseLoginLogMessageDTO insert(RequestLoginLogInsertDTO loginLog) {
        ResponseLoginLogMessageDTO rp = new ResponseLoginLogMessageDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_start_login");

            Map<String, Object> inParams = Map.of(
                    "email", loginLog.getEmail(),
                    "contrasena", loginLog.getPassword());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo insertar el log.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Log insertado correctamente");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar log: " + e.getMessage());
        }
        return rp;
    }
}
