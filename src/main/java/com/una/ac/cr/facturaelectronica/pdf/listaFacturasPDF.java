package com.una.ac.cr.facturaelectronica.pdf;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.una.ac.cr.facturaelectronica.logic.FacturaEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import java.util.Map;

@Component("presentation/provedorLogin/factura/listarFacturas")
public class listaFacturasPDF extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Iterable<FacturaEntity> facturas = (Iterable<FacturaEntity>) model.get("facturas");
        PdfPTable tablaFacturas = new PdfPTable(6);
        facturas.forEach(factura -> {
            tablaFacturas.addCell(String.valueOf(factura.getFacturaId()));
            tablaFacturas.addCell(factura.getProveedor());
            tablaFacturas.addCell(factura.getCliente());
            tablaFacturas.addCell(String.valueOf(factura.getCantidad()));
            tablaFacturas.addCell(String.valueOf(factura.getFecha()));
            tablaFacturas.addCell(String.valueOf(factura.getTotal()));
        });
        document.add(tablaFacturas);

    }
}
