package com.elhidaja.apiselhidaja.persistence.repository;

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
                    "id_almacen_origen", option.getIdAlmacenOrigen(),
                    "tipo_destino", option.getTipoDestino(),
                    "fecha_inicio", option.getFechaInicio(),
                    "fecha_fin", option.getFechaFin()

            );

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            if (rows != null && !rows.isEmpty()) {
                Map<String, Object> firstRow = rows.get(0);
                if (firstRow.containsKey("id_guia_salida")) {
                    List<ResponseGuiaSalidaDTO> guiasSalida = rows.stream().map(row -> {
                        ResponseGuiaSalidaDTO dto = new ResponseGuiaSalidaDTO();
                        dto.setId(((Number) row.get("id_guia_salida")).longValue());
                        dto.setTipoOperacion((String) row.get("tipo_operacion"));
                        dto.setTipoDocumento((String) row.get("tipo_documento"));
                        dto.setTrabajador((String) row.get("trabajador"));
                        dto.setTipoDestino((String) row.get("tipo_destino"));
                        dto.setDestino(((String) row.get("destino")));
                        java.sql.Date sqlDate = (java.sql.Date) row.get("fecha_salida");
                        dto.setFechaSalida(sqlDate.toLocalDate());
                        dto.setDescripcion((String) row.get("descripcion"));
                        dto.setStatus((Boolean) row.get("status"));
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
                    rcd.setTipoOperacion((String) row.get("tipo_operacion"));
                    rcd.setTipoDocumento((String) row.get("tipo_documento"));
                    rcd.setTrabajador((String) row.get("trabajador"));
                    rcd.setTipoDestino((String) row.get("tipo_destino"));
                    rcd.setDestino(((String) row.get("destino")));
                    java.sql.Date sqlDate = (java.sql.Date) row.get("fecha_salida");
                    rcd.setFechaSalida(sqlDate.toLocalDate());
                    rcd.setDescripcion((String) row.get("descripcion"));
                    rcd.setStatus((Boolean) row.get("status"));

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
            inParams.put("id_almacen_origen", objGuiaSalida.getIdAlmacen()); 
            inParams.put("descripcion", objGuiaSalida.getDescripcion());
            inParams.put("tipo_destino", objGuiaSalida.getTipoDestino());
            inParams.put("id_destino", objGuiaSalida.getIdDestino());
            inParams.put("fecha", objGuiaSalida.getFecha());
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
