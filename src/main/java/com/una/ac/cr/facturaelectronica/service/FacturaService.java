package com.una.ac.cr.facturaelectronica.service;

import com.una.ac.cr.facturaelectronica.data.FacturaRepository;
import com.una.ac.cr.facturaelectronica.logic.FacturaEntity;
import org.springframework.stereotype.Service;

@Service
public class FacturaService {
    FacturaRepository facturaRepository;


    public FacturaService(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    public void facturaSave(FacturaEntity factura){
        facturaRepository.save(factura);
    }

    public Iterable<FacturaEntity> facturaFindAllByCliente(String cedula){
        return facturaRepository.findAllByCliente(cedula);
    }
    public Iterable<FacturaEntity> facturaFindAll(){
        return facturaRepository.findAll();
    }

    public FacturaEntity facturaFindById(int id){
        return facturaRepository.findById(id).get();
    }

    public void saveFactura(FacturaEntity factura) {
        facturaRepository.save(factura);
    }

//    public FacturaEntity facturaFindAllByProveedorId(String idUsuario) {
//        return facturaRepository.findByUsuarioId(idUsuario);
//    }
}
