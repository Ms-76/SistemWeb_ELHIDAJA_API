package com.elhidaja.apiselhidaja.persistence.repository;

import com.elhidaja.apiselhidaja.service.DAO.ChoferDAO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.chofer.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.chofer.Request.*;

@Repository
public class ChoferRepository implements ChoferDAO {

    private final JdbcTemplate jdbc;

    public ChoferRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseChoferAllDTO getAllD(RequestChoferOptionDTO option) {
        ResponseChoferAllDTO rp = new ResponseChoferAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_choferes");

            Map<String, Object> inParams = Map.of(
                    "status", option.getEstado());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponseChoferDTO> choferes = rows.stream().map(row -> {
                ResponseChoferDTO dto = new ResponseChoferDTO();
                    dto.setId(((Number) row.get("id_chofer")).longValue());
                    dto.setDocumento((String) row.get("documento"));
                    dto.setNumeroDocumento((String) row.get("numero_documento"));
                    dto.setNombre((String) row.get("nombre"));
                    dto.setEmail((String) row.get("email"));
                    dto.setTelefono((String) row.get("telefono"));
                    dto.setDireccion((String) row.get("direcion"));
                    dto.setDepartamento((String) row.get("departamento"));
                    dto.setProvincia((String) row.get("provincia"));
                    dto.setDistrito((String) row.get("distrito"));
                    dto.setLicenciaConducir((String) row.get("licencia_conducir"));
                    dto.setCategoriaLicencia((String) row.get("categoria_licencia"));
                    dto.setFechaEmisionLicencia((java.time.LocalDate) row.get("fecha_emision_licencia"));
                    dto.setFechaVencimientoLicencia((java.time.LocalDate) row.get("fecha_vencimiento_licencia"));
                    dto.setExperienciaAnios((Integer) row.get("experiencia_anios"));
                    dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();

            rp.setChoferes(choferes);
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
    public ResponseDetalleChoferDTO getByIdD(RequestChoferFilterDTO id) {
        ResponseDetalleChoferDTO rp = new ResponseDetalleChoferDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_chofer_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_chofer", id.getId());

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
                    ResponseChoferDTO rcd = new ResponseChoferDTO();
                    rcd.setId(((Number) row.get("id_chofer")).longValue());
                    rcd.setDocumento((String) row.get("documento"));
                    rcd.setNumeroDocumento((String) row.get("numero_documento"));
                    rcd.setNombre((String) row.get("nombre"));
                    rcd.setEmail((String) row.get("email"));
                    rcd.setTelefono((String) row.get("telefono"));
                    rcd.setDireccion((String) row.get("direcion"));
                    rcd.setDepartamento((String) row.get("departamento"));
                    rcd.setProvincia((String) row.get("provincia"));
                    rcd.setDistrito((String) row.get("distrito"));
                    rcd.setLicenciaConducir((String) row.get("licencia_conducir"));
                    rcd.setCategoriaLicencia((String) row.get("categoria_licencia"));
                    rcd.setFechaEmisionLicencia((java.time.LocalDate) row.get("fecha_emision_licencia"));
                    rcd.setFechaVencimientoLicencia((java.time.LocalDate) row.get("fecha_vencimiento_licencia"));
                    rcd.setExperienciaAnios((Integer) row.get("experiencia_anios"));
                    rcd.setStatus((Boolean) row.get("status"));

                    rp.setChofer(rcd);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Chofer encontrado");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró el chofer");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseChoferMensajeDTO activateD(RequestChoferIdDTO id) {
        ResponseChoferMensajeDTO rp = new ResponseChoferMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_chofer");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_chofer", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar el chofer.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Chofer activado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar el chofer: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseChoferMensajeDTO desactivateD(RequestChoferIdDTO id) {
        ResponseChoferMensajeDTO rp = new ResponseChoferMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_chofer");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_chofer", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar el chofer.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Chofer desactivado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar el chofer: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseChoferMensajeDTO updateD(RequestChoferUpdateDTO objChofer) {
        ResponseChoferMensajeDTO rp = new ResponseChoferMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_actualizar_chofer");

            Map<String, Object> inParams = new HashMap<>();
            inParams.put("id_usuario_sign", objChofer.getIdLogin());
            inParams.put("id_chofer", objChofer.getIdChofer());
            inParams.put("numero_documento", objChofer.getNumeroDocumento());
            inParams.put("nombres", objChofer.getNombres());
            inParams.put("id_documento_identidad", objChofer.getIdDocumentoIdentidad());
            inParams.put("direccion", objChofer.getDireccion());
            inParams.put("telefono", objChofer.getTelefono());
            inParams.put("email", objChofer.getEmail());
            inParams.put("id_distrito", objChofer.getIdDistrito());
            inParams.put("licencia_conducir", objChofer.getLicenciaConducir());
            inParams.put("categoria_licencia", objChofer.getCategoriaLicencia());
            inParams.put("fecha_emision_licencia", objChofer.getFechaEmisionLicencia());
            inParams.put("fecha_vencimiento_licencia", objChofer.getFechaVencimientoLicencia());
            inParams.put("experiencia_anios", objChofer.getExperienciaAnios());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo actualizar el chofer.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Chofer actualizado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al actualizar el chofer: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseChoferMensajeDTO insertD(RequestChoferInsertDTO objChofer) {
        ResponseChoferMensajeDTO rp = new ResponseChoferMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_chofer");

            Map<String, Object> inParams = new HashMap<>();
            inParams.put("id_usuario_sign", objChofer.getIdLogin());
            inParams.put("numero_documento", objChofer.getNumeroDocumento());
            inParams.put("nombres", objChofer.getNombres());
            inParams.put("id_documento_identidad", objChofer.getIdDocumentoIdentidad());
            inParams.put("direccion", objChofer.getDireccion());
            inParams.put("telefono", objChofer.getTelefono());
            inParams.put("email", objChofer.getEmail());
            inParams.put("id_distrito", objChofer.getIdDistrito());
            inParams.put("licencia_conducir", objChofer.getLicenciaConducir());
            inParams.put("categoria_licencia", objChofer.getCategoriaLicencia());
            inParams.put("fecha_emision_licencia", objChofer.getFechaEmisionLicencia());
            inParams.put("fecha_vencimiento_licencia", objChofer.getFechaVencimientoLicencia());
            inParams.put("experiencia_anios", objChofer.getExperienciaAnios());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo registrar el chofer.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Chofer registrado correctamente.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar chofer: " + e.getMessage());
        }
        return rp;
    }
}
