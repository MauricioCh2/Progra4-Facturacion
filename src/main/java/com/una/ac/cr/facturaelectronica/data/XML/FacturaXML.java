package com.una.ac.cr.facturaelectronica.data.XML;

import com.una.ac.cr.facturaelectronica.logic.FacturaEntity;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

import java.io.StringWriter;
import java.util.List;

public class FacturaXML {
    public String generateXml(Iterable<FacturaEntity> facturas) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(FacturaEntity.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw = new StringWriter();

            for (FacturaEntity factura : facturas) {
                jaxbMarshaller.marshal(factura, sw);
            }

            return sw.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
