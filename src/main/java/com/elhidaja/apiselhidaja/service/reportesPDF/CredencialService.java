package com.elhidaja.apiselhidaja.service.reportesPDF;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import com.elhidaja.apiselhidaja.presentation.dto.usuario.Response.*;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;

@Service
public class CredencialService {

    public byte[] generarCredencial(ResponseDetalleUsuarioDTO usuario) throws Exception {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        float width = 350;
        float height = 550;
        float centerX = width / 2;

        Document document = new Document(new Rectangle(width, height));
        PdfWriter writer = PdfWriter.getInstance(document, baos);
        document.open();

        PdfContentByte cb = writer.getDirectContent();

        // ==========================================
        // PÁGINA 1: FRONTAL
        // ==========================================

        // Imagen de fondo Frontal
        Image frontal = Image.getInstance(getClass().getResource("/images/idCardFront.png"));
        frontal.scaleAbsolute(width, height);
        frontal.setAbsolutePosition(0, 0);
        document.add(frontal);

        // Configuración de fuente
        BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
        BaseFont bfBold = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

        cb.beginText();

        cb.setFontAndSize(bfBold, 18);
        cb.showTextAligned(Element.ALIGN_CENTER, "" + usuario.getUsuario().getNombre(), centerX, 300, 0);
        cb.setFontAndSize(bfBold, 16);
        cb.showTextAligned(Element.ALIGN_CENTER, "" + usuario.getUsuario().getPuesto(), centerX, 280, 0);
        cb.setFontAndSize(bf, 14);
        cb.showTextAligned(Element.ALIGN_RIGHT, "TELEFONO :" + usuario.getUsuario().getTelefono(), centerX, 240, 0);
        // cb.showTextAligned(Element.ALIGN_CENTER, "EMAIL: " +
        // usuario.getUsuario().getEmail(), centerX, 220, 0);
        cb.endText();

        // Código de Barras Centrado
        Barcode128 barcode = new Barcode128();
        barcode.setCode(usuario.getUsuario().getNumeroDocumento());
        barcode.setFont(null); // Opcional: quitar el texto debajo de las barras si prefieres solo las barras

        Image barcodeImg = barcode.createImageWithBarcode(cb, null, null);

        // Dimensiones deseadas del código de barras
        float barcodeW = 240;
        float barcodeH = 60;

        barcodeImg.scaleAbsolute(barcodeW, barcodeH);
        // Cálculo para centrar: (AnchoPagina - AnchoImagen) / 2
        float barcodeX = (width - barcodeW) / 2;
        barcodeImg.setAbsolutePosition(barcodeX, 100);
        document.add(barcodeImg);

        // Añadir el número debajo del código de barras centrado manualmente (opcional
        // si barcode.setFont(null))
        cb.beginText();
        cb.setFontAndSize(bf, 12);
        cb.showTextAligned(Element.ALIGN_CENTER, usuario.getUsuario().getNumeroDocumento(), centerX, 80, 0);
        cb.endText();
        // ==========================================
        // IMAGEN DEL USUARIO (FOTO)
        // ==========================================
        String fotoUsuarioUrl = usuario.getUsuario().getUrlImagen(); // Suponiendo que tienes un campo "imagen"
        Image fotoUsuario;

        if (fotoUsuarioUrl != null && !fotoUsuarioUrl.isEmpty()) {
            try {
                // Intentamos cargar la imagen del usuario desde URL
                fotoUsuario = Image.getInstance(fotoUsuarioUrl);
            } catch (Exception e) {
                // Si hay algún error (URL incorrecta, inaccesible), usamos la imagen por
                // defecto
                fotoUsuario = Image.getInstance(getClass().getResource("/images/user.png"));
            }
        } else {
            // Si no tiene URL, usamos la imagen por defecto
            fotoUsuario = Image.getInstance(getClass().getResource("/images/user.png"));
        }

        // Escalado proporcional
        float maxWidth = 120;
        float maxHeight = 120;
        float scaleX = maxWidth / fotoUsuario.getWidth();
        float scaleY = maxHeight / fotoUsuario.getHeight();
        float scale = Math.min(scaleX, scaleY);
        fotoUsuario.scaleAbsolute(fotoUsuario.getWidth() * scale, fotoUsuario.getHeight() * scale);

        // Centrado horizontal y posición vertical
        float fotoX = (width - fotoUsuario.getScaledWidth()) / 2;
        float fotoY = 340; // ajustar según diseño
        fotoUsuario.setAbsolutePosition(fotoX, fotoY);
        document.add(fotoUsuario);
        // ==========================================
        // PÁGINA 2: TRASERA
        // ==========================================
        document.newPage();

        Image trasera = Image.getInstance(getClass().getResource("/images/idCardBack.png"));
        trasera.scaleAbsolute(width, height);
        trasera.setAbsolutePosition(0, 0);
        document.add(trasera);

        // Términos y condiciones
        String terminosTexto = "• Esta credencial es personal e intransferible.\n" +
                "• Debe portarse en todo momento.\n" +
                "• En caso de pérdida, reportarla inmediatamente.";

        Font fontTerminos = new Font(bf, 14);

        ColumnText ct = new ColumnText(cb);
        // Definimos un área centrada para el texto
        ct.setSimpleColumn(20, 350, 330, 500);

        Paragraph p = new Paragraph(terminosTexto, fontTerminos);
        p.setFont(new Font(bfBold, 18));
        p.setAlignment(Element.ALIGN_LEFT); // Centrar el bloque de texto
        p.setSpacingBefore(50);
        ct.addElement(p);
        ct.go();

        // Fechas de Emisión y Vencimiento Centradas
        cb.beginText();
        cb.setFontAndSize(bfBold, 12);

        LocalDate hoy = LocalDate.now();
        LocalDate venc = hoy.plusYears(1);

        cb.showTextAligned(Element.ALIGN_CENTER, "Emisión: " + hoy.toString(), centerX, 140, 0);
        cb.showTextAligned(Element.ALIGN_CENTER, "Válido Hasta: " + venc.toString(), centerX, 120, 0);
        cb.endText();

        // QR Centrado
        String contenidoQR = usuario.getUsuario().getEmail() + "|" + usuario.getUsuario().getDocumento();
        float qrSize = 150; // Tamaño del QR
        Image qrImg = generarQR(contenidoQR, (int) qrSize, (int) qrSize);

        // Cálculo para centrar QR: (350 - 150) / 2 = 100
        float qrX = (width - qrSize) / 2;
        qrImg.setAbsolutePosition(qrX, 170); // Ajusté un poco la altura (Y) para que no pegue con el borde
        document.add(qrImg);

        document.close();
        return baos.toByteArray();
    }

    // GENERAR QR CON ZXING
    private Image generarQR(String texto, int w, int h) throws Exception {
        BitMatrix matrix = new MultiFormatWriter().encode(texto, BarcodeFormat.QR_CODE, w, h);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(matrix, "PNG", baos);
        return Image.getInstance(baos.toByteArray());
    }
}
