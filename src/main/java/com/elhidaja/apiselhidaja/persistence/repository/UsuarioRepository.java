package com.elhidaja.apiselhidaja.persistence.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.usuario.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.usuario.Request.*;
import com.elhidaja.apiselhidaja.service.DAO.UsuarioDAO;

@Repository
public class UsuarioRepository implements UsuarioDAO {
    private final JdbcTemplate jdbc;

    public UsuarioRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseUsuarioAllDTO getAllD(RequestUsuarioOptionDTO option) {
        ResponseUsuarioAllDTO rp = new ResponseUsuarioAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_usuarios");

            Map<String, Object> inParams = Map.of(
                    "option", option.getEstado());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponseUsuarioDTO> usuarios = rows.stream().map(row -> {
                ResponseUsuarioDTO dto = new ResponseUsuarioDTO();
                dto.setId(((Number) row.get("id_usuario")).longValue());
                dto.setDocumento((String) row.get("documento"));
                dto.setNumeroDocumento((String) row.get("numero_documento"));
                dto.setNombre((String) row.get("nombre"));
                dto.setEmail((String) row.get("email"));
                dto.setTelefono((String) row.get("telefono"));
                dto.setArea((String) row.get("area"));
                dto.setPuesto((String) row.get("puesto"));
                dto.setRol((String) row.get("rol"));
                dto.setDireccion((String) row.get("direcion"));
                dto.setDepartamento((String) row.get("departamento"));
                dto.setProvincia((String) row.get("provincia"));
                dto.setDistrito((String) row.get("distrito"));
                dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();

            rp.setUsuarios(usuarios);
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
    public ResponseDetalleUsuarioDTO getByIdD(RequestUsuarioIdDTO id) {
        ResponseDetalleUsuarioDTO rp = new ResponseDetalleUsuarioDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_usuario_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_usuario", id.getId());

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
                    ResponseUsuarioDTO rcd = new ResponseUsuarioDTO();
                    rcd.setId(((Number) row.get("id_usuario")).longValue());
                    rcd.setDocumento((String) row.get("documento"));
                    rcd.setNumeroDocumento((String) row.get("numero_documento"));
                    rcd.setNombre((String) row.get("nombre"));
                    rcd.setEmail((String) row.get("email"));
                    rcd.setTelefono((String) row.get("telefono"));
                    rcd.setArea((String) row.get("area"));
                    rcd.setPuesto((String) row.get("puesto"));
                    rcd.setRol((String) row.get("rol"));
                    rcd.setDireccion((String) row.get("direcion"));
                    rcd.setDepartamento((String) row.get("departamento"));
                    rcd.setProvincia((String) row.get("provincia"));
                    rcd.setDistrito((String) row.get("distrito"));
                    rcd.setStatus((Boolean) row.get("status"));

                    rp.setUsuario(rcd);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Usuario encontrado");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró el usuario");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseUsuarioMensajeDTO activateD(RequestUsuarioIdDTO id) {
        ResponseUsuarioMensajeDTO rp = new ResponseUsuarioMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_usuario");

            Map<String, Object> inParams = Map.of(
                    "id_usuario", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar el usuario.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Usuario activado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar el usuario: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseUsuarioMensajeDTO desactivateD(RequestUsuarioIdDTO id) {
        ResponseUsuarioMensajeDTO rp = new ResponseUsuarioMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_usuario");

            Map<String, Object> inParams = Map.of(
                    "id_usuario", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar el usuario.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Usuario desactivado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar el usuario: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseUsuarioMensajeDTO updateD(RequestUsuarioUpdateDTO objUsuario) {
        ResponseUsuarioMensajeDTO rp = new ResponseUsuarioMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_actualizar_usuario");

            Map<String, Object> inParams = new HashMap<>();
            inParams.put("numero_documento", objUsuario.getNumeroDocumento());
            inParams.put("nombres", objUsuario.getNombres());
            inParams.put("id_documento_identidad", objUsuario.getIdDocumentoIdentidad());
            inParams.put("direccion", objUsuario.getDireccion());
            inParams.put("telefono", objUsuario.getTelefono());
            inParams.put("email", objUsuario.getEmail());
            inParams.put("id_distrito", objUsuario.getIdDistrito());
            inParams.put("id_estado_civil", objUsuario.getIdEstadoCivil());
            inParams.put("fecha_nacimiento", objUsuario.getFechaNacimiento());
            inParams.put("id_genero", objUsuario.getIdGenero());
            inParams.put("password", objUsuario.getPassword());
            inParams.put("id_area", objUsuario.getIdArea());
            inParams.put("id_nivel_academico", objUsuario.getIdNivelAcademico());
            inParams.put("id_oficio", objUsuario.getIdOficio());
            inParams.put("id_puesto", objUsuario.getIdPuesto());
            inParams.put("id_rol", objUsuario.getIdRol());
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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo actualizar el usuario.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Usuario actualizado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al actualizar el usuario: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseUsuarioMensajeDTO insertD(RequestUsuarioInsertDTO objUsuario) {
        ResponseUsuarioMensajeDTO rp = new ResponseUsuarioMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_usuario");

            Map<String, Object> inParams = new HashMap<>();
            inParams.put("numero_documento", objUsuario.getNumeroDocumento());
            inParams.put("nombres", objUsuario.getNombres());
            inParams.put("id_documento_identidad", objUsuario.getIdDocumentoIdentidad());
            inParams.put("direccion", objUsuario.getDireccion());
            inParams.put("telefono", objUsuario.getTelefono());
            inParams.put("email", objUsuario.getEmail());
            inParams.put("id_distrito", objUsuario.getIdDistrito());
            inParams.put("id_estado_civil", objUsuario.getIdEstadoCivil());
            inParams.put("fecha_nacimiento", objUsuario.getFechaNacimiento());
            inParams.put("id_genero", objUsuario.getIdGenero());
            inParams.put("password", objUsuario.getPassword());
            inParams.put("id_area", objUsuario.getIdArea());
            inParams.put("id_nivel_academico", objUsuario.getIdNivelAcademico());
            inParams.put("id_oficio", objUsuario.getIdOficio());
            inParams.put("id_puesto", objUsuario.getIdPuesto());
            inParams.put("id_rol", objUsuario.getIdRol());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo registrar el usuario.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Usuario registrado correctamente.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar usuario : " + e.getMessage());
        }
        return rp;
    }
}
