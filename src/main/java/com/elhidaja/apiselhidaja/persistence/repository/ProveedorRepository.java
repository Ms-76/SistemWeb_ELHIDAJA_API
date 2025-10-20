package com.elhidaja.apiselhidaja.persistence.repository;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.proveedor.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.proveedor.Response.*;
import com.elhidaja.apiselhidaja.service.DAO.ProveedorDAO;

@Repository
public class ProveedorRepository implements ProveedorDAO {
    private final JdbcTemplate jdbc;

    public ProveedorRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseProveedorAllDTO getAllD(RequestProveedorOptionDTO option) {
        ResponseProveedorAllDTO rp = new ResponseProveedorAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_proveedores");

            Map<String, Object> inParams = Map.of(
                    "status", option.getEstado());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponseProveedorDTO> proveedores = rows.stream().map(row -> {
                ResponseProveedorDTO dto = new ResponseProveedorDTO();
                dto.setId(((Number) row.get("id_proveedor")).longValue());
                dto.setDocumento((String) row.get("documento"));
                dto.setNumeroDocumento((String) row.get("numero_documento"));
                dto.setNombre((String) row.get("nombre"));
                dto.setEmail((String) row.get("correo"));
                dto.setTelefono((String) row.get("telefono"));
                dto.setDireccion((String) row.get("direccion"));
                dto.setDepartamento((String) row.get("departamento"));
                dto.setProvincia((String) row.get("provincia"));
                dto.setDistrito((String) row.get("distrito"));
                dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();

            rp.setProveedores(proveedores);
            rp.setExito(true);
            rp.setCodigo("200");
            rp.setMensaje("Consulta exitosa");

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener datos: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseDetalleProveedorDTO getByIdD(RequestProveedorFilterDTO id) {
        ResponseDetalleProveedorDTO rp = new ResponseDetalleProveedorDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_proveedor_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_proveedor", id.getId());

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
                    ResponseProveedorDTO dto = new ResponseProveedorDTO();
                    dto.setId(((Number) row.get("id_proveedor")).longValue());
                    dto.setDocumento((String) row.get("documento"));
                    dto.setNumeroDocumento((String) row.get("numero_documento"));
                    dto.setNombre((String) row.get("nombre"));
                    dto.setEmail((String) row.get("correo"));
                    dto.setTelefono((String) row.get("telefono"));
                    dto.setDireccion((String) row.get("direccion"));
                    dto.setDepartamento((String) row.get("departamento"));
                    dto.setProvincia((String) row.get("provincia"));
                    dto.setDistrito((String) row.get("distrito"));
                    dto.setStatus((Boolean) row.get("status"));

                    rp.setProveedor(dto);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Proveedor encontrado");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("Proveedor no encontrado");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener proveedor: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseProveedorMensajeDTO activateD(RequestProveedorIdDTO id) {
        ResponseProveedorMensajeDTO rp = new ResponseProveedorMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_proveedor");

            Map<String, Object> inParams = Map.of(
                "id_usuario_sign", id.getIdLogin(),  
                    "id_proveedor", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar el proveedor.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Proveedor activado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar el proveedor: " + e.getMessage());
        }

        return rp;
    }

    @Override
    public ResponseProveedorMensajeDTO desactivateD(RequestProveedorIdDTO id) {
        ResponseProveedorMensajeDTO rp = new ResponseProveedorMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_proveedor");

            Map<String, Object> inParams = Map.of(
                "id_usuario_sign", id.getIdLogin(),  
                    "id_proveedor", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar el proveedor.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Proveedor desactivado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar el proveedor: " + e.getMessage());
        }

        return rp;
    }

    @Override
    public ResponseProveedorMensajeDTO insertD(RequestProveedorInsertDTO objProveedor) {
        ResponseProveedorMensajeDTO rp = new ResponseProveedorMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_proveedor");

            Map<String, Object> inParams = Map.of(
                "id_usuario_sign", objProveedor.getIdLogin(),  
                    "numero_documento", objProveedor.getNumeroDocumento(),
                    "nombres", objProveedor.getNombres(),
                    "id_documento_identidad", objProveedor.getIdDocumentoIdentidad(),
                    "direccion", objProveedor.getDireccion(),
                    "telefono", objProveedor.getTelefono(),
                    "email", objProveedor.getEmail(),
                    "id_distrito", objProveedor.getIdDistrito()
                    );

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.values().stream()
                    .filter(v -> v instanceof List && !((List<?>) v).isEmpty())
                    .findFirst()
                    .orElse(null);

            if (resultSet != null) {
                String mensaje = (String) resultSet.get(0).get("mensaje");
                rp.setCodigo("400");
                rp.setMensaje(mensaje != null ? mensaje : "No se pudo registrar el proveedor.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Proveedor registrado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar proveedor: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseProveedorMensajeDTO updateD(RequestProveedorUpdateDTO objProveedor) {
        ResponseProveedorMensajeDTO rp = new ResponseProveedorMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_actualizar_proveedor");

            Map<String, Object> inParams = Map.of(
                    "id_proveedor", objProveedor.getId(),
                    "numero_documento", objProveedor.getNumeroDocumento(),
                    "nombres", objProveedor.getNombres(),
                    "id_documento_identidad", objProveedor.getIdDocumentoIdentidad(),
                    "direccion", objProveedor.getDireccion(),
                    "telefono", objProveedor.getTelefono(),
                    "email", objProveedor.getEmail(),
                    "id_distrito", objProveedor.getIdDistrito()
                    );

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.values().stream()
                    .filter(v -> v instanceof List && !((List<?>) v).isEmpty())
                    .findFirst()
                    .orElse(null);

            if (resultSet != null) {
                String mensaje = (String) resultSet.get(0).get("mensaje");
                rp.setCodigo("400");
                rp.setMensaje(mensaje != null ? mensaje : "No se pudo actualizar el proveedor.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Proveedor actualizado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al actualizar proveedor: " + e.getMessage());
        }
        return rp;
    }
}
