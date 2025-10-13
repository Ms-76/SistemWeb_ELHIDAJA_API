package com.elhidaja.apiselhidaja.persistence.repository;

import com.elhidaja.apiselhidaja.service.DAO.DetalleInventarioDAO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.detalleInventario.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.detalleInventario.Response.*;

@Repository
public class DetalleInventarioRepository implements DetalleInventarioDAO {
    private final JdbcTemplate jdbc;

    public DetalleInventarioRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseDetalleInventarioAllDTO getAllD(RequestDetalleInventarioOptionDTO option) {
        ResponseDetalleInventarioAllDTO rp = new ResponseDetalleInventarioAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_detalle_inventarios");

            Map<String, Object> inParams = Map.of(
                    "option", option.getEstado());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponseDetalleInventarioInnerDTO> detalles = rows.stream().map(row -> {
                ResponseDetalleInventarioInnerDTO dto = new ResponseDetalleInventarioInnerDTO();
                dto.setIdInventario(((Number) row.get("id_inventario")).longValue());
                dto.setIdDetalleInventario(((Number) row.get("id_detalle_inventario")).longValue());
                dto.setProducto(((String) row.get("producto")));
                dto.setEmpleado(((String) row.get("empleado")));
                dto.setStockSistema((Integer) row.get("stock_sistema"));
                dto.setStockFisico((Integer) row.get("stock_fisico"));
                dto.setDiferencia((Integer) row.get("diferencia"));
                dto.setObservacion((String) row.get("observacion"));
                dto.setFechaInicioInventario((LocalDateTime) row.get("fecha_inicio_inventario"));
                dto.setFechaFinInventario((LocalDateTime) row.get("fecha_fin_inventario"));
                dto.setNuevo((Boolean) row.get("nuevo"));
                dto.setEditadoManual((Boolean) row.get("editato_manual"));
                dto.setEstado((Integer) row.get("estado"));
                dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();

            rp.setDetalleInventarios(detalles);

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
    public ResponseDetalleInventarioDTO getByIdD(RequestDetalleInventarioIdDTO id) {
        ResponseDetalleInventarioDTO rp = new ResponseDetalleInventarioDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_detalle_inventario_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_detalle_inventario", id.getIdDetalleInventario());

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

                    ResponseDetalleInventarioInnerDTO dto = new ResponseDetalleInventarioInnerDTO();
                    dto.setIdInventario(((Number) row.get("id_inventario")).longValue());
                    dto.setIdDetalleInventario(((Number) row.get("id_detalle_inventario")).longValue());
                    dto.setProducto(((String) row.get("producto")));
                    dto.setEmpleado(((String) row.get("empleado")));
                    dto.setStockSistema((Integer) row.get("stock_sistema"));
                    dto.setStockFisico((Integer) row.get("stock_fisico"));
                    dto.setDiferencia((Integer) row.get("diferencia"));
                    dto.setObservacion((String) row.get("observacion"));
                    dto.setFechaInicioInventario((LocalDateTime) row.get("fecha_inicio_inventario"));
                    dto.setFechaFinInventario((LocalDateTime) row.get("fecha_fin_inventario"));
                    dto.setNuevo((Boolean) row.get("nuevo"));
                    dto.setEditadoManual((Boolean) row.get("editato_manual"));
                    dto.setEstado((Integer) row.get("estado"));
                    dto.setStatus((Boolean) row.get("status"));

                    rp.setDetalleInventario(dto);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Detalle de inventario encontrado");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró el detalle de inventario");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseDetalleInventarioMensajeDTO activateD(RequestDetalleInventarioIdDTO id) {
        ResponseDetalleInventarioMensajeDTO rp = new ResponseDetalleInventarioMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_detalle_inventario");

            Map<String, Object> inParams = Map.of(
                    "id_detalle_inventario", id.getIdDetalleInventario());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar el detalle de inventario.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Detalle de inventario activado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar el detalle de inventario: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseDetalleInventarioMensajeDTO desactivateD(RequestDetalleInventarioIdDTO id) {

        ResponseDetalleInventarioMensajeDTO rp = new ResponseDetalleInventarioMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_detalle_inventario");

            Map<String, Object> inParams = Map.of(
                    "id_detalle_inventario", id.getIdDetalleInventario());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar el detalle de inventario.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Detalle de inventario desactivado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar el detalle de inventario: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseDetalleInventarioMensajeDTO updateD(RequestDetalleInventarioUpdateDTO objDetalleInventario) {
        ResponseDetalleInventarioMensajeDTO rp = new ResponseDetalleInventarioMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_actualizar_detalle_inventario");

            Map<String, Object> inParams = Map.of(
                    "id_detalle_inventario", objDetalleInventario.getId(),
                    "stock_fisico", objDetalleInventario.getStockFisico(),
                    "diferencia", objDetalleInventario.getDiferencia(),
                    "observacion", objDetalleInventario.getObservacion(),
                    "fecha_fin", objDetalleInventario.getFechaFinInventario(),
                    "nuevo", objDetalleInventario.getNuevo(),
                    "editado_manual", objDetalleInventario.getEditadoManual(),
                    "estado", objDetalleInventario.getEstado());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo actualizar el detalle de inventario.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Detalle de inventario actualizado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al actualizar el detalle de inventario: " + e.getMessage());
        }
        return rp;

    }

    @Override
    public ResponseDetalleInventarioMensajeDTO insertD(RequestDetalleInventarioInsertDTO objDetalleInventario) {

        ResponseDetalleInventarioMensajeDTO rp = new ResponseDetalleInventarioMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_detalle_inventario");

            Map<String, Object> inParams = Map.of(
                    "id_inventario", objDetalleInventario.getIdInventario(),
                    "id_producto", objDetalleInventario.getIdProducto(),
                    "id_usuario", objDetalleInventario.getIdUsuario());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo registrar el detalle de inventario.");

            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Detalle de inventario registrado correctamente");
            }

        } catch (Exception e) {
            e.printStackTrace();
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar detalle de inventario: " + e.getMessage());
        }
        return rp;
    }
}
