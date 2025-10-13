package com.elhidaja.apiselhidaja.persistence.repository;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.rol.Request.RequestRolIdDTO;
import com.elhidaja.apiselhidaja.presentation.dto.rol.Request.RequestRolInsertDTO;
import com.elhidaja.apiselhidaja.presentation.dto.rol.Request.RequestRolOptionDTO;
import com.elhidaja.apiselhidaja.presentation.dto.rol.Request.RequestRolUpdateDTO;
import com.elhidaja.apiselhidaja.presentation.dto.rol.Response.ResponseDetalleRolDTO;
import com.elhidaja.apiselhidaja.presentation.dto.rol.Response.ResponseRolAllDTO;
import com.elhidaja.apiselhidaja.presentation.dto.rol.Response.ResponseRolDTO;
import com.elhidaja.apiselhidaja.presentation.dto.rol.Response.ResponseRolMensajeDTO;
import com.elhidaja.apiselhidaja.service.DAO.RolDAO;

@Repository
public class RolRepository implements RolDAO {

    private final JdbcTemplate jdbc;

    public RolRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseRolAllDTO getAllD(RequestRolOptionDTO option) {
        ResponseRolAllDTO rp = new ResponseRolAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_roles");

            Map<String, Object> inParams = Map.of(
                    "option", option.getEstado());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponseRolDTO> roles = rows.stream().map(row -> {
                ResponseRolDTO dto = new ResponseRolDTO();
                dto.setId(((Number) row.get("id_rol")).longValue());
                dto.setNombre((String) row.get("nombre"));
                dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();

            rp.setRoles(roles);
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
    public ResponseDetalleRolDTO getByIdD(RequestRolIdDTO id) {
        ResponseDetalleRolDTO rp = new ResponseDetalleRolDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_rol_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_rol", id.getId());

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
                    ResponseRolDTO rol = new ResponseRolDTO();
                    rol.setId(((Number) row.get("id_rol")).longValue());
                    rol.setNombre((String) row.get("nombre"));
                    rol.setStatus((Boolean) row.get("status"));

                    rp.setRol(rol);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Rol encontrado");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró el rol");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseRolMensajeDTO activateD(RequestRolIdDTO id) {
        ResponseRolMensajeDTO rp = new ResponseRolMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_rol");

            Map<String, Object> inParams = Map.of(
                    "id_rol", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar el rol.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Rol activado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar el rol: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseRolMensajeDTO deactivateD(RequestRolIdDTO id) {
        ResponseRolMensajeDTO rp = new ResponseRolMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_rol");

            Map<String, Object> inParams = Map.of(
                    "id_rol", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar el rol.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Rol desactivado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar el rol: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseRolMensajeDTO updateD(RequestRolUpdateDTO objRol) {
        ResponseRolMensajeDTO rp = new ResponseRolMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_actualizar_rol");

            Map<String, Object> inParams = Map.of(
                    "id_rol", objRol.getId(),
                    "nombre", objRol.getNombre());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo actualizar el rol.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Rol actualizado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al actualizar el rol: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseRolMensajeDTO insertD(RequestRolInsertDTO objRol) {
        ResponseRolMensajeDTO rp = new ResponseRolMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_rol");

            Map<String, Object> inParams = Map.of(
                    "nombre", objRol.getNombre());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo registrar el rol.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Rol registrado correctamente.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar rol: " + e.getMessage());
        }
        return rp;
    }
}
