package com.una.ac.cr.facturaelectronica.presentation.Administrador;


import com.una.ac.cr.facturaelectronica.data.UsuarioRepository;
import com.una.ac.cr.facturaelectronica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller("ADMIN")
public class Controller {
    @Autowired
    UsuarioService usuarioService;
    @GetMapping("/presentation/Administrador/show")
    public String show(Model model){
        model.addAttribute("proveedores", usuarioService.findByRol("PROV"));
        return "/presentation/proveedores/View";
    }
}
