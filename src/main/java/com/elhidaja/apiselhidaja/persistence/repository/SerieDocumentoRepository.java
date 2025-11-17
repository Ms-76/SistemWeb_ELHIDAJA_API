package com.elhidaja.apiselhidaja.persistence.repository;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import com.elhidaja.apiselhidaja.presentation.dto.serieDocumento.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.serieDocumento.Response.*;
import com.elhidaja.apiselhidaja.service.DAO.SerieDocumentoDAO;

@Repository
public class SerieDocumentoRepository implements SerieDocumentoDAO {
    private final JdbcTemplate jdbc;

    public SerieDocumentoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseSerieDocumentoAllDTO getAllD(RequestSerieDocumentoOptionDTO option) {
        ResponseSerieDocumentoAllDTO rp = new ResponseSerieDocumentoAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_serie_documentos");

            Map<String, Object> inParams = Map.of(
                    "status", option.getEstado(),
                    "id_serie", option.getIdSerie(),
                    "id_documento_operacion", option.getIdDocumentoOperacion());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponseSerieDocumentoDTO> serieDocumentos = rows.stream().map(row -> {
                ResponseSerieDocumentoDTO dto = new ResponseSerieDocumentoDTO();
                dto.setId(((Number) row.get("id_serie_documento")).longValue());
                dto.setSerie((String) row.get("serie"));
                dto.setTipoDocumento((String) row.get("nombre_tipo_documento"));
                dto.setTipoOperacion((String) row.get("nombre_tipo_operacion"));
                dto.setCodigoSunat(((Number) row.get("codigo_sunat")).longValue());
                dto.setCodigoInterno(((Number) row.get("codigo_interno")).longValue());
                dto.setUltimoCorrelativo(((Number) row.get("ultimo_correlativo")).longValue());
                dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();

            rp.setSerieDocumentos(serieDocumentos);
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
    public ResponseDetalleSerieDocumentoDTO getByIdD(RequestSerieDocumentoFilterDTO id) {
        ResponseDetalleSerieDocumentoDTO rp = new ResponseDetalleSerieDocumentoDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_serie_documento_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_serie_documento", id.getId());

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
                    ResponseSerieDocumentoDTO rcd = new ResponseSerieDocumentoDTO();
                    rcd.setId(((Number) row.get("id_serie_documento")).longValue());
                    rcd.setSerie((String) row.get("serie"));
                    rcd.setTipoDocumento((String) row.get("nombre_tipo_documento"));
                    rcd.setTipoOperacion((String) row.get("nombre_tipo_operacion"));
                    rcd.setCodigoSunat(((Number) row.get("codigo_sunat")).longValue());
                    rcd.setCodigoInterno(((Number) row.get("codigo_interno")).longValue());
                    rcd.setUltimoCorrelativo(((Number) row.get("ultimo_correlativo")).longValue());
                    rcd.setStatus((Boolean) row.get("status"));

                    rp.setSerieDocumento(rcd);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Registro encontrado");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró el registro");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseSerieDocumentoMensajeDTO activateD(RequestSerieDocumentoIdDTO id) {
        ResponseSerieDocumentoMensajeDTO rp = new ResponseSerieDocumentoMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_serie_documento");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_serie_documento", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Registro activado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar el registro: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseSerieDocumentoMensajeDTO desactivateD(RequestSerieDocumentoIdDTO id) {
        ResponseSerieDocumentoMensajeDTO rp = new ResponseSerieDocumentoMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_serie_documento");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_serie_documento", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Registro desactivado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar el registro: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseSerieDocumentoMensajeDTO updateD(RequestSerieDocumentoUpdateDTO objSerieDocumento) {
        ResponseSerieDocumentoMensajeDTO rp = new ResponseSerieDocumentoMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_actualizar_serie_documento");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", objSerieDocumento.getIdLogin(),
                    "id_serie_documento", objSerieDocumento.getId(),
                    "id_serie", objSerieDocumento.getIdSerie(),
                    "id_documento_operacion", objSerieDocumento.getIdDocumentoOperacion(),
                    "ultimo_correlativo", objSerieDocumento.getUltimoCorrelativo());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo actualizar.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Registro actualizado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al actualizar el registro: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseSerieDocumentoMensajeDTO insertD(RequestSerieDocumentoInsertDTO objSerieDocumento) {
        ResponseSerieDocumentoMensajeDTO rp = new ResponseSerieDocumentoMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_serie_documento");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", objSerieDocumento.getIdLogin(),
                    "id_serie", objSerieDocumento.getIdSerie(),
                    "id_documento_operacion", objSerieDocumento.getIdDocumentoOperacion(),
                    "ultimo_correlativo", objSerieDocumento.getUltimoCorrelativo());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo registrar.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Registro insertado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar: " + e.getMessage());
        }
        return rp;
    }
}
