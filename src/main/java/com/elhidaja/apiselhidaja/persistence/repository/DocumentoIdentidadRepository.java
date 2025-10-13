package com.elhidaja.apiselhidaja.persistence.repository;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.documentoIdentidad.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.documentoIdentidad.Request.*;
import com.elhidaja.apiselhidaja.service.DAO.DocumentoIdentidadDAO;
@Repository
public class DocumentoIdentidadRepository  implements DocumentoIdentidadDAO{
        private final JdbcTemplate jdbc;

    public DocumentoIdentidadRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseDocumentoIdentidadAllDTO getAllD(RequestDocumentoIdentidadOptionDTO option) {

        ResponseDocumentoIdentidadAllDTO rp = new ResponseDocumentoIdentidadAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_documentos_identidad");

            Map<String, Object> inParams = Map.of("option", option.getEstado());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponseDocumentoIdentidadDTO> documentos = rows.stream().map(row -> {
                ResponseDocumentoIdentidadDTO dto = new ResponseDocumentoIdentidadDTO();
                dto.setId(((Number) row.get("id")).longValue());
                System.out.println("Option: " + dto.getId());
                dto.setNombre((String) row.get("nombre"));
                dto.setDescripcion((String) row.get("descripcion"));
                dto.setLongitud((Integer) row.get("longitud"));
                dto.setTipoDocumento((String) row.get("tipo_documento"));
                dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();

            rp.setDocumentosIdentidad(documentos);
            rp.setExito(true);
            rp.setCodigo("200");
            rp.setMensaje("Consulta exitosa");

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener documentos: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseDetalleDocumentoIdentidadDTO getByIdD(RequestDocumentoIdentidadIdDTO id) {
        ResponseDetalleDocumentoIdentidadDTO rp = new ResponseDetalleDocumentoIdentidadDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_documento_identidad_por_id");

            Map<String, Object> inParams = Map.of("id_documento_identidad", id.getId());

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
                    ResponseDocumentoIdentidadDTO dto = new ResponseDocumentoIdentidadDTO();
                    dto.setId(((Number) row.get("id")).longValue());
                    dto.setNombre((String) row.get("nombre"));
                    dto.setDescripcion((String) row.get("descripcion"));
                    dto.setLongitud((Integer) row.get("longitud"));
                    dto.setTipoDocumento((String) row.get("tipo_documento"));
                    dto.setStatus((Boolean) row.get("status"));

                    rp.setDocumentoIdentidad(dto);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Documento encontrado");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró el documento de identidad");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener documento: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseDocumentoIdentidadMensajeDTO insertD(RequestDocumentoIdentidadInsertDTO doc) {
        ResponseDocumentoIdentidadMensajeDTO rp = new ResponseDocumentoIdentidadMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_documento_identidad");

            Map<String, Object> inParams = Map.of(
                    "nombre", doc.getNombre(),
                    "descripcion", doc.getDescripcion(),
                    "longitud", doc.getLongitud(),
                    "tipo_documento", doc.getTipoDocumento()
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

                rp.setCodigo("400");
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo registrar el documento.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Documento registrado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar documento: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseDocumentoIdentidadMensajeDTO updateD(RequestDocumentoIdentidadUpdateDTO doc) {
        ResponseDocumentoIdentidadMensajeDTO rp = new ResponseDocumentoIdentidadMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_actualizar_documento_identidad");

            Map<String, Object> inParams = Map.of(
                    "id_documento_identidad", doc.getId(),
                    "nombre", doc.getNombre(),
                    "descripcion", doc.getDescripcion(),
                    "longitud", doc.getLongitud(),
                    "tipo_documento", doc.getTipoDocumento()
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

                rp.setCodigo("400");
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo actualizar el documento.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Documento actualizado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al actualizar documento: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseDocumentoIdentidadMensajeDTO activateD(RequestDocumentoIdentidadIdDTO id) {
        ResponseDocumentoIdentidadMensajeDTO rp = new ResponseDocumentoIdentidadMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_documento_identidad");

            Map<String, Object> inParams = Map.of("id_documento_identidad", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar el documento.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Documento activado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar documento: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseDocumentoIdentidadMensajeDTO desactivateD(RequestDocumentoIdentidadIdDTO id) {
        ResponseDocumentoIdentidadMensajeDTO rp = new ResponseDocumentoIdentidadMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_documento_identidad");

            Map<String, Object> inParams = Map.of("id_documento_identidad", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar el documento.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Documento desactivado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar documento: " + e.getMessage());
        }
        return rp;
    }
}
