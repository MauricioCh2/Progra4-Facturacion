package com.una.ac.cr.facturaelectronica.service;

import com.una.ac.cr.facturaelectronica.data.UsuarioRepository;
import com.una.ac.cr.facturaelectronica.logic.ClienteEntity;
import com.una.ac.cr.facturaelectronica.logic.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    List<ClienteEntity> clientes;
    public List<UsuarioEntity> usuarioFindAll(){
        return usuarioRepository.findAll();
    }
    public Optional<UsuarioEntity> proveedorLogin(String usuario, String contrasena){
        return usuarioRepository.findByContrasenaAndIdUsuario(contrasena, usuario);
    }
    public void proveedorSave(UsuarioEntity usuario){
        usuarioRepository.save(usuario);
    }
    public UsuarioEntity proveedorById(String id){
      return  usuarioRepository.findByIdUsuario(id);
    }

    public List<UsuarioEntity> findByRol(String prov) {
        return usuarioRepository.findByRol(prov);
    }
}
