package com.elhidaja.apiselhidaja.persistence.repository;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.documentoOperacion.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.documentoOperacion.Response.*;
import com.elhidaja.apiselhidaja.service.DAO.DocumentoOperacionDAO;

@Repository
public class DocumentoOperacionRepository implements DocumentoOperacionDAO {
    private final JdbcTemplate jdbc;

    public DocumentoOperacionRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseDocumentoOperacionAllDTO getAllD(RequestDocumentoOperacionOptionDTO option) {
        ResponseDocumentoOperacionAllDTO rp = new ResponseDocumentoOperacionAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_documentos_operacion");

            Map<String, Object> inParams = Map.of("status", option.getEstado());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponseDocumentoOperacionDTO> documentos = rows.stream().map(row -> {
                ResponseDocumentoOperacionDTO dto = new ResponseDocumentoOperacionDTO();
                dto.setId((Integer) row.get("id_documento_operacion"));
                dto.setNombre_tipo_documento((String) row.get("nombre_tipo_documento"));
                dto.setNombre_tipo_operacion((String) row.get("nombre_tipo_operacion"));
                dto.setCodigo_sunat((Integer) row.get("codigo_sunat"));
                dto.setCodigo_interno((Integer) row.get("codigo_interno"));
                dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();

            rp.setDocumentos_operacion(documentos);
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
    public ResponseDetalleDocumentoOperacionDTO getByIdD(RequestDocumentoOperacionFilterDTO id) {
        ResponseDetalleDocumentoOperacionDTO rp = new ResponseDetalleDocumentoOperacionDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_documento_operacion_por_id");

            Map<String, Object> inParams = Map.of("id_documento_operacion", id.getId());

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
                    ResponseDocumentoOperacionDTO dto = new ResponseDocumentoOperacionDTO();
                    dto.setId((Integer) row.get("id_documento_operacion"));
                    dto.setNombre_tipo_documento((String) row.get("nombre_tipo_documento"));
                    dto.setNombre_tipo_operacion((String) row.get("nombre_tipo_operacion"));
                    dto.setCodigo_sunat((Integer) row.get("codigo_sunat"));
                    dto.setCodigo_interno((Integer) row.get("codigo_interno"));
                    dto.setStatus((Boolean) row.get("status"));

                    rp.setDocumento_operacion(dto);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Documento operación encontrado");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró el documento operación");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseDocumentoOperacionMensajeDTO activateD(RequestDocumentoOperacionIdDTO id) {
        ResponseDocumentoOperacionMensajeDTO rp = new ResponseDocumentoOperacionMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_documento_operacion");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_documento_operacion", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar el documento operación.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Documento operación activado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar el documento operación: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseDocumentoOperacionMensajeDTO desactivateD(RequestDocumentoOperacionIdDTO id) {
        ResponseDocumentoOperacionMensajeDTO rp = new ResponseDocumentoOperacionMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_documento_operacion");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_documento_operacion", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar el documento operación.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Documento operación desactivado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar el documento operación: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseDocumentoOperacionMensajeDTO updateD(RequestDocumentoOperacionUpdateDTO objDocumentoOperacion) {
        ResponseDocumentoOperacionMensajeDTO rp = new ResponseDocumentoOperacionMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_actualizar_documento_operacion");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", objDocumentoOperacion.getIdLogin(),
                    "id_documento_operacion", objDocumentoOperacion.getId(),
                    "id_tipo_documento", objDocumentoOperacion.getId_tipo_documento(),
                    "id_tipo_operacion", objDocumentoOperacion.getId_tipo_operacion(),
                    "codigo_sunat", objDocumentoOperacion.getCodigo_sunat(),
                    "codigo_interno", objDocumentoOperacion.getCodigo_interno());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo actualizar el documento operación.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Documento operación actualizado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al actualizar el documento operación: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseDocumentoOperacionMensajeDTO insertD(RequestDocumentoOperacionInsertDTO objDocumentoOperacion) {
        ResponseDocumentoOperacionMensajeDTO rp = new ResponseDocumentoOperacionMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_documento_operacion");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", objDocumentoOperacion.getIdLogin(),
                    "id_tipo_documento", objDocumentoOperacion.getId_tipo_documento(),
                    "id_tipo_operacion", objDocumentoOperacion.getId_tipo_operacion(),
                    "codigo_sunat", objDocumentoOperacion.getCodigo_sunat(),
                    "codigo_interno", objDocumentoOperacion.getCodigo_interno());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo registrar el documento operación.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Documento operación registrado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar documento operación: " + e.getMessage());
        }
        return rp;
    }
}
