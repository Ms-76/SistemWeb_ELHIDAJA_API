package com.elhidaja.apiselhidaja.persistence.repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.guiaEntrada.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.guiaEntrada.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.guiaSalida.Request.RequestRecibirDesdeGuiaSalidaDTO;
import com.elhidaja.apiselhidaja.presentation.dto.guiaSalida.Response.ResponseGuiaSalidaMensajeDTO;
import com.elhidaja.apiselhidaja.service.DAO.GuiaEntradaDAO;
import com.elhidaja.apiselhidaja.util.xml.XmlBuilder;
import com.elhidaja.apiselhidaja.util.xml.XmlDetalleGuiaEntrada;

@Repository
public class GuiaEntradaRepository implements GuiaEntradaDAO {
    private final JdbcTemplate jdbc;

    public GuiaEntradaRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseGuiaEntradaAllDTO getAllD(RequestGuiaEntradaOptionDTO option) {
        ResponseGuiaEntradaAllDTO rp = new ResponseGuiaEntradaAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_guias_entrada");

            Map<String, Object> inParams = Map.of(
                    "status", option.getEstado(),
                    "id_almacen", option.getIdAlmacen(),
                    "id_proveedor", option.getIdProveedor(),
                    "id_serie_documento", option.getIdSerieDocumento(),
                    "fecha_inicio", option.getFechaInicio(),
                    "fecha_fin", option.getFechaFin(),
                    "id_usuario", option.getIdUsuario());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            // Si la lista de resultados no está vacía, la revisamos
            if (rows != null && !rows.isEmpty()) {
                // Obtenemos la primera (y única) fila para inspeccionarla
                Map<String, Object> firstRow = rows.get(0);

                // Verificamos si la respuesta contiene los datos esperados ("id_guia_entrada")
                if (firstRow.containsKey("id_guia_entrada")) {
                    List<ResponseGuiaEntradaDTO> guiasEntrada = rows.stream().map(row -> {
                        ResponseGuiaEntradaDTO dto = new ResponseGuiaEntradaDTO();
                        dto.setId(((Number) row.get("id_guia_entrada")).longValue());
                        dto.setTipoOperacion((String) row.get("tipo_operacion"));
                        dto.setTipoDocumento((String) row.get("tipo_documento"));
                        dto.setCodigoSunat(((Number) row.get("codigo_sunat")).longValue());
                        dto.setCodigoInterno(((Number) row.get("codigo_interno")).longValue());
                        dto.setSerie((String) row.get("serie"));
                        dto.setUltimoCorrelativo(((Number) row.get("correlativo")).longValue());
                        dto.setProveedor((String) row.get("proveedor"));
                        dto.setTrabajador((String) row.get("trajabador"));
                        dto.setDescripcion((String) row.get("descripcion"));
                        Timestamp ts = (Timestamp) row.get("fecha_entrada");
                        dto.setFechaEntrada(ts.toLocalDateTime());
                        return dto;
                    }).toList();

                    rp.setGuiasEntrada(guiasEntrada);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Consulta exitosa");
                } else {
                    // Si no tiene "id_guia_entrada", es el mensaje de error del SP
                    rp.setExito(false);
                    rp.setCodigo("404");
                    rp.setMensaje((String) firstRow.get("mensaje"));
                }
            } else {
                // El procedimiento no devolvió filas
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontraron guías de entrada para los criterios especificados.");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener los datos: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseDetalleGuiaEntradaDTO getByIdD(RequestGuiaEntradaFilterDTO id) {
        ResponseDetalleGuiaEntradaDTO rp = new ResponseDetalleGuiaEntradaDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_guia_entrada_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_guia_entrada", id.getId());

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

                    ResponseGuiaEntradaDTO rcd = new ResponseGuiaEntradaDTO();
                    rcd.setId(((Number) row.get("id_guia_entrada")).longValue());
                    rcd.setTipoOperacion((String) row.get("tipo_operacion"));
                    rcd.setTipoDocumento((String) row.get("tipo_documento"));
                    rcd.setCodigoSunat(((Number) row.get("codigo_sunat")).longValue());
                    rcd.setCodigoInterno(((Number) row.get("codigo_interno")).longValue());
                    rcd.setSerie((String) row.get("serie"));
                    rcd.setUltimoCorrelativo(((Number) row.get("correlativo")).longValue());
                    rcd.setProveedor((String) row.get("proveedor"));
                    rcd.setTrabajador((String) row.get("trabajador"));
                    rcd.setDescripcion((String) row.get("descripcion"));
                    rcd.setFechaEntrada((LocalDateTime) row.get("fecha_entrada"));

                    rp.setGuiaEntrada(rcd);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Guía encontrada");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró la guía de entrada");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseGuiaEntradaMensajeDTO activateD(RequestGuiaEntradaIdDTO id) {
        ResponseGuiaEntradaMensajeDTO rp = new ResponseGuiaEntradaMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_guia_entrada");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_guia_entrada", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar la guía de entrada.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Guía activada correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar la guía: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseGuiaEntradaMensajeDTO desactivateD(RequestGuiaEntradaIdDTO id) {

        ResponseGuiaEntradaMensajeDTO rp = new ResponseGuiaEntradaMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_guia_entrada");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_guia_entrada", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar la guía de entrada.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Guía desactivada correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar la guía: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseGuiaEntradaMensajeDTO updateD(RequestGuiaEntradaUpdateDTO objGuiaEntrada) {
        ResponseGuiaEntradaMensajeDTO rp = new ResponseGuiaEntradaMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_actualizar_guia_entrada");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", objGuiaEntrada.getIdLogin(),
                    "id_guia_entrada", objGuiaEntrada.getId(),
                    "descripcion", objGuiaEntrada.getDescripcion());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo actualizar la guía de entrada.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Guía actualizada correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al actualizar la guía: " + e.getMessage());
        }
        return rp;

    }

    @Override
    public ResponseGuiaEntradaMensajeDTO insertD(RequestGuiaEntradaInsertDTO objGuiaEntrada) {

        ResponseGuiaEntradaMensajeDTO rp = new ResponseGuiaEntradaMensajeDTO();
        try {

            List<XmlDetalleGuiaEntrada> xmlDetallesList = objGuiaEntrada.getDetalles().stream()
                    .map(d -> new XmlDetalleGuiaEntrada(
                            d.getIdProducto(),
                            d.getCodigo(),
                            d.getNombre(),
                            d.getImagen(),
                            d.getCodigoBarras(),
                            d.getDescripcionProd(),
                            d.getIdSubcategoria(),
                            d.getCosto(),
                            d.getCantidad(),
                            d.getIdUnidadMedida(),
                            d.getObservacion()

                    ))
                    .toList();
            /*
             * System.out.println("===== Detalles de la guía =====");
             * for (XmlDetalleGuiaEntrada detalle : xmlDetallesList) {
             * System.out.println("idProducto: " + detalle.getIdProducto());
             * System.out.println("codigo: " + detalle.getCodigo());
             * System.out.println("nombre: " + detalle.getNombre());
             * System.out.println("imagen: " + detalle.getImagen());
             * System.out.println("codigoBarras: " + detalle.getCodigoBarras());
             * System.out.println("descripcionProd: " + detalle.getDescripcionProd());
             * System.out.println("idSubcategoria: " + detalle.getIdSubcategoria());
             * System.out.println("costo: " + detalle.getCosto());
             * System.out.println("cantidad: " + detalle.getCantidad());
             * System.out.println("idUnidadMedida: " + detalle.getIdUnidadMedida());
             * System.out.println("observacion: " + detalle.getObservacion());
             * System.out.println("-------------------------------");
             * }
             */
            String xmlGenerado = XmlBuilder.toXmlGuiaEntrada(xmlDetallesList);

            // System.out.println(xmlGenerado);

            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_guia_entrada_con_detalle_xml");

            Map<String, Object> inParams = new HashMap<>();
            inParams.put("id_usuario_sign", objGuiaEntrada.getIdLogin());
            inParams.put("id_tipo_operacion", objGuiaEntrada.getIdTipoOperacion());
            inParams.put("id_tipo_documento", objGuiaEntrada.getIdTipoDocumento());
            inParams.put("codigo_sunat", objGuiaEntrada.getCodigoSunat());
            inParams.put("codigo_interno", objGuiaEntrada.getCodigoInterno());
            inParams.put("id_serie", objGuiaEntrada.getIdSerie());
            inParams.put("id_proveedor", objGuiaEntrada.getIdProveedor());
            inParams.put("id_usuario", objGuiaEntrada.getIdUsuario());
            inParams.put("id_almacen", objGuiaEntrada.getIdAlmacen());
            inParams.put("descripcion", objGuiaEntrada.getDescripcion());
            inParams.put("xml_detalles", xmlGenerado);

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo registrar la guía de entrada.");

            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Guía registrada correctamente");
            }

        } catch (Exception e) {
            e.printStackTrace();
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar guía de entrada : " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseGuiaSalidaMensajeDTO recibirDesdeGuiaSalida(RequestRecibirDesdeGuiaSalidaDTO r) {
        ResponseGuiaSalidaMensajeDTO rp = new ResponseGuiaSalidaMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_recibir_desde_guia_salida");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", r.getIdLogin(),
                    "id_guia_salida", r.getIdGuiaSalida(),
                    "id_tipo_operacion", r.getIdTipoOperacion(),
                    "id_tipo_documento", r.getIdTipoDocumento(),
                    "codigo_sunat", r.getCodigoSunat(),
                    "codigo_interno", r.getCodigoInterno(),
                    "id_serie", r.getIdSerie(),
                    "id_proveedor_sistema", r.getIdProveedorSistema());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo recibir desde guía de salida.");

            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Recepción desde guía de salida realizada correctamente.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            rp.setCodigo("500");
            rp.setMensaje("Error al recibir desde guía de salida : " + e.getMessage());
        }
        return rp;
    }

}
