package com.elhidaja.apiselhidaja.persistence.repository;

import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.guiaSalida.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.guiaSalida.Response.*;
import com.elhidaja.apiselhidaja.service.DAO.GuiaSalidaDAO;
import com.elhidaja.apiselhidaja.util.xml.XmlBuilder;
import com.elhidaja.apiselhidaja.util.xml.XmlDetalleGuiaSalida;

@Repository
public class GuiaSalidaRepository implements GuiaSalidaDAO {
    private final JdbcTemplate jdbc;

    public GuiaSalidaRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseGuiaSalidaAllDTO getAllD(RequestGuiaSalidaOptionDTO option) {
        ResponseGuiaSalidaAllDTO rp = new ResponseGuiaSalidaAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_guias_salida");

            Map<String, Object> inParams = Map.of(
                    "status", option.getEstado(),
                    "id_almacen", option.getIdAlmacen());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            if (rows != null && !rows.isEmpty()) {
                Map<String, Object> firstRow = rows.get(0);
                if (firstRow.containsKey("id_guia_salida")) {
                    List<ResponseGuiaSalidaDTO> guiasSalida = rows.stream().map(row -> {
                        ResponseGuiaSalidaDTO dto = new ResponseGuiaSalidaDTO();
                        dto.setId(((Number) row.get("id_guia_salida")).longValue());
                        dto.setProveedor((String) row.get("proveedor"));
                        dto.setTrabajador((String) row.get("trabajador"));
                        dto.setDescripcion((String) row.get("descripcion"));

                        Object fechaObj = row.get("fecha_creacion");
                        if (fechaObj instanceof java.sql.Timestamp) {
                            java.sql.Timestamp timestamp = (java.sql.Timestamp) fechaObj;
                            dto.setFechaSalida(timestamp.toLocalDateTime().toLocalDate());
                        } else if (fechaObj instanceof java.util.Date) {
                            java.util.Date fecha = (java.util.Date) fechaObj;
                            dto.setFechaSalida(fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        } else {
                            dto.setFechaSalida(null);
                        }
                        return dto;
                    }).toList();

                    rp.setGuiasSalida(guiasSalida);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Consulta exitosa");
                } else {
                    rp.setExito(false);
                    rp.setCodigo("404");
                    rp.setMensaje((String) firstRow.get("mensaje"));
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontraron guías de salida para los criterios especificados.");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener los datos: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseDetalleGuiaSalidaDTO getByIdD(RequestGuiaSalidaFilterDTO id) {
        ResponseDetalleGuiaSalidaDTO rp = new ResponseDetalleGuiaSalidaDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_guia_salida_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_guia_salida", id.getId());

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
                    ResponseGuiaSalidaDTO rcd = new ResponseGuiaSalidaDTO();
                    rcd.setId(((Number) row.get("id_guia_salida")).longValue());
                    rcd.setProveedor(((String) row.get("proveedor")));
                    rcd.setTrabajador(((String) row.get("trabajador")));
                    rcd.setDescripcion((String) row.get("descripcion"));
                    Object fechaObj = row.get("fecha_creacion");
                    if (fechaObj instanceof java.sql.Timestamp) {
                        java.sql.Timestamp timestamp = (java.sql.Timestamp) fechaObj;
                        rcd.setFechaSalida(timestamp.toLocalDateTime().toLocalDate());
                    } else if (fechaObj instanceof java.util.Date) {
                        java.util.Date fecha = (java.util.Date) fechaObj;
                        rcd.setFechaSalida(fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                    } else {
                        rcd.setFechaSalida(null);
                    }

                    rp.setGuiaSalida(rcd);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Guía encontrada");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró la guía de salida");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseGuiaSalidaMensajeDTO activateD(RequestGuiaSalidaIdDTO id) {
        ResponseGuiaSalidaMensajeDTO rp = new ResponseGuiaSalidaMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_guia_salida");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_guia_salida", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar la guía de salida.");
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
    public ResponseGuiaSalidaMensajeDTO desactivateD(RequestGuiaSalidaIdDTO id) {
        ResponseGuiaSalidaMensajeDTO rp = new ResponseGuiaSalidaMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_guia_salida");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", id.getIdLogin(),
                    "id_guia_salida", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar la guía de salida.");
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
    public ResponseGuiaSalidaMensajeDTO updateD(RequestGuiaSalidaUpdateDTO objGuiaSalida) {
        ResponseGuiaSalidaMensajeDTO rp = new ResponseGuiaSalidaMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_actualizar_guia_salida");

            Map<String, Object> inParams = Map.of(
                    "id_usuario_sign", objGuiaSalida.getIdLogin(),
                    "id_guia_salida", objGuiaSalida.getId(),
                    "descripcion", objGuiaSalida.getDescripcion());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo actualizar la guía de salida.");
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
    public ResponseGuiaSalidaMensajeDTO insertD(RequestGuiaSalidaInsertDTO objGuiaSalida) {
        ResponseGuiaSalidaMensajeDTO rp = new ResponseGuiaSalidaMensajeDTO();
        try {
            List<XmlDetalleGuiaSalida> xmlDetallesList = objGuiaSalida.getDetalles().stream()
                    .map(d -> new XmlDetalleGuiaSalida(
                            d.getId(),
                            d.getCantidad(),
                            d.getIdUnidadMedida(),
                            d.getObservacion()))
                    .collect(Collectors.toList());

            String xmlGenerado = XmlBuilder.toXmlGuiaSalida(xmlDetallesList);
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_guia_salida_con_detalle_xml");

            Map<String, Object> inParams = new HashMap<>();
            inParams.put("id_usuario_sign", objGuiaSalida.getIdLogin());
            inParams.put("id_tipo_operacion", objGuiaSalida.getIdTipoOperacion());
            inParams.put("id_tipo_documento", objGuiaSalida.getIdTipoDocumento());
            inParams.put("codigo_sunat", objGuiaSalida.getCodigoSunat());
            inParams.put("codigo_interno", objGuiaSalida.getCodigoInterno());
            inParams.put("id_serie", objGuiaSalida.getIdSerie());
            inParams.put("id_proveedor", objGuiaSalida.getIdProveedor());
            inParams.put("id_usuario", objGuiaSalida.getIdUsuario());
            inParams.put("id_almacen", objGuiaSalida.getIdAlmacen());
            inParams.put("descripcion", objGuiaSalida.getDescripcion());
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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo registrar la guía de salida.");

            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Guía registrada correctamente");
            }

        } catch (Exception e) {
            e.printStackTrace();
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar guía de salida : " + e.getMessage());
        }
        return rp;
    }

 
}
