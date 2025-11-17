package com.elhidaja.apiselhidaja.persistence.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.ubicacion.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.ubicacion.Response.*;
import com.elhidaja.apiselhidaja.service.DAO.UbicacionDAO;

@Repository
public class UbicacionRepository implements UbicacionDAO {

    private final JdbcTemplate jdbc;

    public UbicacionRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseUbicacionAllDTO getAllD(RequestUbicacionOptionDTO option) {
        ResponseUbicacionAllDTO rp = new ResponseUbicacionAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_detalle_producto_almacen");

            Map<String, Object> inParams = Map.of(
                    "status", option.getEstado(),
                    "id_almacen", option.getIdAlmacen(),
                    "id_categoria", option.getIdCategoria(),
                    "id_sub_categoria", option.getIdSubcategoria(),
                    "id_unidad_medida", option.getIdUnidadMedida(),
                    "id_estante", option.getIdEstante(),
                    "id_pallet", option.getIdPallet()
                    );

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponseUbicacionDTO> ubicaciones = rows.stream().map(row -> {
                ResponseUbicacionDTO dto = new ResponseUbicacionDTO();
                dto.setId(((Number) row.get("id_producto")).longValue());
                dto.setCategoria((String) row.get("nombre_categoria"));
                dto.setSubCategoria((String) row.get("nombre_sub_categoria"));
                dto.setNombre((String) row.get("nombre_producto"));
                dto.setCodigo((String) row.get("codigo_producto"));
                dto.setCodigoBarras((String) row.get("codigo_barras"));
                dto.setDescripcion((String) row.get("descripcion"));
                dto.setUnidadMedida((String) row.get("unidad_medida"));
                dto.setStock_total(((Number) row.get("stock_total")).longValue());
                dto.setCosto(((BigDecimal) row.get("costo")).doubleValue());
                dto.setStatus((Boolean) row.get("status"));
                dto.setCodigoAlmacen((String) row.get("codigo_almacen"));
                dto.setCodigoEstante((String) row.get("codigo_estante"));
                dto.setCodigoPallet((String) row.get("codigo_pallet"));
                return dto;
            }).toList();

            rp.setUbicaciones(ubicaciones);

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
    public ResponseDetalleUbicacionDTO getByIdD(RequestUbicacionIdDTO id) {
        ResponseDetalleUbicacionDTO rp = new ResponseDetalleUbicacionDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_detalle_producto_almacen_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_detalle_producto_almacen", id.getId(),
                    "id_almacen", id.getIdAlmacen());

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
                    ResponseUbicacionDTO rcd = new ResponseUbicacionDTO();
                    rcd.setId(((Number) row.get("id_producto")).longValue());
                    rcd.setCategoria((String) row.get("nombre_categoria"));
                    rcd.setSubCategoria((String) row.get("nombre_sub_categoria"));
                    rcd.setNombre((String) row.get("nombre_producto"));
                    rcd.setCodigo((String) row.get("codigo_producto"));
                    rcd.setCodigoBarras((String) row.get("codigo_barras"));
                    rcd.setDescripcion((String) row.get("descripcion"));
                    rcd.setUnidadMedida((String) row.get("unidad_medida"));
                    rcd.setStock_total(((Number) row.get("stock_total")).longValue());
                    rcd.setCosto(((BigDecimal) row.get("costo")).doubleValue());
                    rcd.setStatus((Boolean) row.get("status"));
                    rcd.setCodigoAlmacen((String) row.get("codigo_almacen"));
                    rcd.setCodigoEstante((String) row.get("codigo_estante"));
                    rcd.setCodigoPallet((String) row.get("codigo_pallet"));

                    rp.setUbicacion(rcd);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Ubicación encontrada");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró la ubicación");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponserUbicacionMensajeDTO activateD(RequestUbicacionIdDTO id) {
        ResponserUbicacionMensajeDTO rp = new ResponserUbicacionMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_detalle_producto_almacen");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_detalle_producto_almacen", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar la ubicación.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Ubicación activada correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar la ubicación: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponserUbicacionMensajeDTO desactivateD(RequestUbicacionIdDTO id) {
        ResponserUbicacionMensajeDTO rp = new ResponserUbicacionMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_detalle_producto_almacen");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_detalle_producto_almacen", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar la ubicación.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Ubicación desactivada correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar la ubicación: " + e.getMessage());
        }
        return rp;
    }

}
