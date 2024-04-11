package com.una.ac.cr.facturaelectronica.service;


import com.una.ac.cr.facturaelectronica.data.ClienteRepository;
import com.una.ac.cr.facturaelectronica.logic.ClienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    public Iterable<ClienteEntity> clienteFindAll(){
        return clienteRepository.findAll();
    }
    public Iterable<ClienteEntity> clienteFindAllByProveedorId(String id){
        return clienteRepository.findAllByUsuarioId(id);
    }

    public void clienteSave(ClienteEntity cliente){
        clienteRepository.save(cliente);
    }

}
