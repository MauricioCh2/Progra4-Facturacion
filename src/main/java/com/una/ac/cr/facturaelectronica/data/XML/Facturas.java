package com.una.ac.cr.facturaelectronica.data.XML;

import com.una.ac.cr.facturaelectronica.logic.FacturaEntity;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.StringWriter;
import java.util.List;

@XmlRootElement(name = "Facturas")
public class Facturas {
    List<FacturaEntity> facturas;

    public List<FacturaEntity> getFacturas() {
        return facturas;
    }

    @XmlElement(name = "Factura")
    public void setFacturas(List<FacturaEntity> facturas) {
        this.facturas = facturas;
    }
}