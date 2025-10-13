package com.elhidaja.apiselhidaja.persistence.repository;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.tipoDocumento.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.tipoDocumento.Response.*;
import com.elhidaja.apiselhidaja.service.DAO.TipoDocumentoDAO;

@Repository
public class TipoDocumentoRepository implements TipoDocumentoDAO {
    private final JdbcTemplate jdbc;

    public TipoDocumentoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseTipoDocumentoAllDTO getAllD(RequestTipoDocumentoOptionDTO option) {
        ResponseTipoDocumentoAllDTO rp = new ResponseTipoDocumentoAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_tipo_documentos");

            Map<String, Object> inParams = Map.of(
                    "option", option.getEstado());
            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponseTipoDocumentoDTO> tiposDocumento = rows.stream().map(row -> {
                ResponseTipoDocumentoDTO dto = new ResponseTipoDocumentoDTO();
                dto.setId(((Number) row.get("id_tipo_documento")).longValue());
                dto.setNombre((String) row.get("nombre"));
                dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();

            rp.setTiposDocumento(tiposDocumento);

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
    public ResponseDetalleTipoDocumentoDTO getByIdD(RequestTipoDocumentoIdDTO id) {
        ResponseDetalleTipoDocumentoDTO rp = new ResponseDetalleTipoDocumentoDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_tipo_documento_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_tipo_documento", id.getId());

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
                    ResponseTipoDocumentoDTO rtd = new ResponseTipoDocumentoDTO();
                    rtd.setId(((Number) row.get("id_tipo_documento")).longValue());
                    rtd.setNombre((String) row.get("nombre"));
                    rtd.setStatus((Boolean) row.get("status"));

                    rp.setTipoDocumento(rtd);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Tipo de documento encontrado");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró el tipo de documento");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseTipoDocumentoMensajeDTO activateD(RequestTipoDocumentoIdDTO id) {
        ResponseTipoDocumentoMensajeDTO rp = new ResponseTipoDocumentoMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_tipo_documento");

            Map<String, Object> inParams = Map.of(
                    "id_tipo_documento", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar el tipo de documento.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Tipo de documento activado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar el tipo de documento: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseTipoDocumentoMensajeDTO desactivateD(RequestTipoDocumentoIdDTO id) {
        ResponseTipoDocumentoMensajeDTO rp = new ResponseTipoDocumentoMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_tipo_documento");

            Map<String, Object> inParams = Map.of(
                    "id_tipo_documento", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar el tipo de documento.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Tipo de documento desactivado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar el tipo de documento: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseTipoDocumentoMensajeDTO updateD(RequestTipoDocumentoUpdateDTO objTipoDocumento) {
        ResponseTipoDocumentoMensajeDTO rp = new ResponseTipoDocumentoMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_actualizar_tipo_documento");

            Map<String, Object> inParams = Map.of(
                    "id_tipo_documento", objTipoDocumento.getId(),
                    "nombre", objTipoDocumento.getNombre());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo actualizar el tipo de documento.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Tipo de documento actualizado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al actualizar el tipo de documento: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseTipoDocumentoMensajeDTO insertD(RequestTipoDocumentoInsertDTO objTipoDocumento) {
        ResponseTipoDocumentoMensajeDTO rp = new ResponseTipoDocumentoMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_tipo_documento");

            Map<String, Object> inParams = Map.of(
                    "nombre", objTipoDocumento.getNombre());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo registrar el tipo de documento.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Tipo de documento registrado correctamente.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar tipo de documento: " + e.getMessage());
        }
        return rp;
    }
}
