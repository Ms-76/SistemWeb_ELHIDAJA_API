package com.elhidaja.apiselhidaja.persistence.repository;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.producto.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.producto.Response.*;
import com.elhidaja.apiselhidaja.service.DAO.ProductoDAO;

@Repository
public class ProductoRepository implements ProductoDAO {
    private final JdbcTemplate jdbc;

    public ProductoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseProductoAllDTO getAllD(RequestProductoOptionDTO option) {
        ResponseProductoAllDTO rp = new ResponseProductoAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_productos");

            Map<String, Object> inParams = Map.of(
                    "option", option.getEstado(),
                    "id_almacen", option.getIdAlmacen());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            // Si la lista de resultados no está vacía, la revisamos
            if (rows != null && !rows.isEmpty()) {
                // Obtenemos la primera (y única) fila para inspeccionarla
                Map<String, Object> firstRow = rows.get(0);

                // Verificamos si la respuesta es de productos encontrados (contiene
                // "id_producto")
                // o si es una respuesta de error (no contiene "id_producto")
                if (firstRow.containsKey("id_producto")) {
                    List<ResponseProductoDTO> productos = rows.stream().map(row -> {
                        ResponseProductoDTO dto = new ResponseProductoDTO();
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
                        return dto;
                    }).toList();

                    rp.setProductos(productos);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Consulta exitosa");
                } else {
                    // Si no tiene "id_producto", es el mensaje de error del SP
                    rp.setExito(false);
                    rp.setCodigo("404");
                    rp.setMensaje((String) firstRow.get("mensaje"));
                }
            } else {
                // El procedimiento no devolvió filas
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontraron productos");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener los productos: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseDetalleProductoDTO getByIdD(RequestProductoIdDTO id) {
        ResponseDetalleProductoDTO rp = new ResponseDetalleProductoDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_producto_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_producto", id.getIdProducto(),
                    "id_almacen", id.getIdAlmacen());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            // Si la lista de resultados no está vacía, la revisamos
            if (rows != null && !rows.isEmpty()) {
                // Obtenemos la primera (y única) fila para inspeccionarla
                Map<String, Object> firstRow = rows.get(0);

                // Verificamos si la respuesta es de un producto encontrado (contiene
                // "id_producto")
                // o si es una respuesta de error (no contiene "id_producto")
                if (firstRow.containsKey("id_producto")) {
                    List<ResponseProductoDTO2> productos = rows.stream().map(row -> {
                        ResponseProductoDTO2 dto = new ResponseProductoDTO2();
                        dto.setId(((Number) row.get("id_producto")).longValue());
                        dto.setCategoria((String) row.get("nombre_categoria"));
                        dto.setSubCategoria((String) row.get("nombre_sub_categoria"));
                        dto.setNombre((String) row.get("nombre_producto"));
                        dto.setCodigo((String) row.get("codigo_producto"));
                        dto.setCodigoBarras((String) row.get("codigo_barras"));
                        dto.setStock((Integer) row.get("stock"));
                        dto.setUnidadMedida((String) row.get("unidad_medida"));
                        dto.setPallet((String) row.get("codigo_pallet"));
                        dto.setEstante((String) row.get("codigo_estante"));
                        dto.setAlmacen((String) row.get("codigo_almacen"));
                        dto.setStatus((Boolean) row.get("status"));
                        return dto;
                    }).toList();

                    rp.setProducto(productos);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Consulta exitosa");
                } else {
                    // Si no tiene "id_producto", es el mensaje de error del SP
                    rp.setExito(false);
                    rp.setCodigo("404");
                    rp.setMensaje((String) firstRow.get("mensaje"));
                }
            } else {
                // El procedimiento no devolvió filas
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontraron resultados para los criterios especificados.");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el producto: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseProductoMensajeDTO insertD(RequestProductoInsertDTO objProducto) {
        ResponseProductoMensajeDTO rp = new ResponseProductoMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_producto")
                    .declareParameters(
                            new SqlParameter("codigo", Types.VARCHAR),
                            new SqlParameter("nombre", Types.VARCHAR),
                            new SqlParameter("imagen", Types.VARCHAR),
                            new SqlParameter("codigo_barras", Types.VARCHAR),
                            new SqlParameter("descripcion", Types.VARCHAR),
                            new SqlParameter("id_subcategoria", Types.INTEGER),
                            new SqlParameter("costo", Types.NUMERIC));

            Map<String, Object> inParams = new HashMap<>();
            inParams.put("codigo", objProducto.getCodigo());
            inParams.put("nombre", objProducto.getNombre());
            inParams.put("imagen", objProducto.getImagen());
            inParams.put("codigo_barras", objProducto.getCodigoBarras());
            inParams.put("descripcion", objProducto.getDescripcion());
            inParams.put("id_subcategoria", objProducto.getIdSubcategoria());
            inParams.put("costo", objProducto.getCosto());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.values().stream()
                    .filter(v -> v instanceof List && !((List<?>) v).isEmpty())
                    .findFirst()
                    .orElse(null);

            if (resultSet != null) {
                String mensaje = (String) resultSet.get(0).get("mensaje");
                rp.setCodigo("400");
                rp.setMensaje(mensaje != null ? mensaje : "No se pudo registrar el producto");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Producto registrado correctamente");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar producto: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseProductoMensajeDTO updateD(RequestProductoUpdateDTO objProducto) {
        ResponseProductoMensajeDTO rp = new ResponseProductoMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_actualizar_producto");

            Map<String, Object> inParams = Map.of(
                    "id_producto", objProducto.getId(),
                    "codigo", objProducto.getCodigo(),
                    "nombre", objProducto.getNombre(),
                    "imagen", objProducto.getImagen(),
                    "codigo_barras", objProducto.getCodigoBarras(),
                    "descripcion", objProducto.getDescripcion(),
                    "id_subcategoria", objProducto.getIdSubcategoria(),
                    "costo", objProducto.getCosto());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.values().stream()
                    .filter(v -> v instanceof List && !((List<?>) v).isEmpty())
                    .findFirst()
                    .orElse(null);

            if (resultSet != null) {
                String mensaje = (String) resultSet.get(0).get("mensaje");
                rp.setCodigo("400");
                rp.setMensaje(mensaje != null ? mensaje : "No se pudo actualizar el producto");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Producto actualizado correctamente");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al actualizar producto: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseProductoMensajeDTO desactivateD(RequestProductoIdDTO id) {
        ResponseProductoMensajeDTO rp = new ResponseProductoMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_producto");

            Map<String, Object> inParams = Map.of("id_producto", id.getIdProducto());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.values().stream()
                    .filter(v -> v instanceof List && !((List<?>) v).isEmpty())
                    .findFirst()
                    .orElse(null);

            if (resultSet != null) {
                String mensaje = (String) resultSet.get(0).get("mensaje");
                rp.setCodigo("400");
                rp.setMensaje(mensaje != null ? mensaje : "No se pudo desactivar el producto");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Producto desactivado correctamente");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar producto: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseProductoMensajeDTO activateD(RequestProductoIdDTO id) {
        ResponseProductoMensajeDTO rp = new ResponseProductoMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_producto");

            Map<String, Object> inParams = Map.of("id_producto", id.getIdProducto());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.values().stream()
                    .filter(v -> v instanceof List && !((List<?>) v).isEmpty())
                    .findFirst()
                    .orElse(null);

            if (resultSet != null) {
                String mensaje = (String) resultSet.get(0).get("mensaje");
                rp.setCodigo("400");
                rp.setMensaje(mensaje != null ? mensaje : "No se pudo activar el producto");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Producto activado correctamente");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar producto: " + e.getMessage());
        }
        return rp;
    }
}
