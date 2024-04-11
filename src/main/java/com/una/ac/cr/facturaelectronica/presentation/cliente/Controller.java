package com.una.ac.cr.facturaelectronica.presentation.cliente;

import com.una.ac.cr.facturaelectronica.logic.ClienteEntity;
import com.una.ac.cr.facturaelectronica.service.ClienteService;
import com.una.ac.cr.facturaelectronica.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.stereotype.Controller("cliente")
public class Controller {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("/presentation/cliente/register")
    public String register(Model model, @RequestParam("proveedorId") String proveedorId ){
        model.addAttribute("idUsuario", proveedorId);
        return "/presentation/proveedorLogin/cliente/View";
    }

    @PostMapping("/presentation/cliente/add")
    public String add(@ModelAttribute ClienteEntity cliente, @RequestParam("idUsuario") String idProveedor) {
        cliente.setUsuarioId(idProveedor);
        cliente.setUsuarioByUsuarioId(usuarioService.proveedorById(idProveedor));
           clienteService.clienteSave(cliente);
        return "redirect:/presentation/clientes";
    }

    @GetMapping("/presentation/clientes")
    public String show(Model model, HttpSession session) {
        String idProveedor = (String) session.getAttribute("idProveedor"); // aqui el id proveedor si va
        model.addAttribute("clientes", clienteService.clienteFindAllByProveedorId(idProveedor));
        return "/presentation/proveedorLogin/cliente/listarCliente";
    }
}
