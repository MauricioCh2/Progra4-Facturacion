package com.una.ac.cr.facturaelectronica.data.XML;

import com.una.ac.cr.facturaelectronica.logic.FacturaEntity;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

import java.io.StringWriter;
import java.util.List;

public class FacturaXML {
    public String generateXml(Iterable<FacturaEntity> facturas) {
        try {
            Facturas facturasContainer = new Facturas();
            facturasContainer.setFacturas((List<FacturaEntity>) facturas);

            JAXBContext jaxbContext = JAXBContext.newInstance(Facturas.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw = new StringWriter();

            jaxbMarshaller.marshal(facturasContainer, sw);

            return sw.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
