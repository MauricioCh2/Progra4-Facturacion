package com.una.ac.cr.facturaelectronica.service;

import com.una.ac.cr.facturaelectronica.data.ProductoRepository;
import com.una.ac.cr.facturaelectronica.logic.ProductoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public Iterable<ProductoEntity> productoFindAll(){
        return productoRepository.findAll();
    }
    public Iterable<ProductoEntity> productoFindAllByProveedorId(String id){
        return productoRepository.findAllByUsuarioId(id);
    }

    public void productoSave(ProductoEntity producto){
        productoRepository.save(producto);
    }


}