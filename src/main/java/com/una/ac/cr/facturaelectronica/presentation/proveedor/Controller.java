package com.una.ac.cr.facturaelectronica.presentation.proveedor;

import com.una.ac.cr.facturaelectronica.logic.UsuarioEntity;
import com.una.ac.cr.facturaelectronica.service.ClienteService;
import com.una.ac.cr.facturaelectronica.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@org.springframework.stereotype.Controller("proveedores")
public class Controller {
    @Autowired
    private UsuarioService service;
    @Autowired
    private ClienteService clienteService;
    @GetMapping("/presentation/proveedores/show")
    public String show(Model model){
        model.addAttribute("proveedores", service.usuarioFindAll());
        return "/presentation/proveedores/View";
    }
    @GetMapping("/presentation/register/show")
    public String registerShow() {
        return "/presentation/register/View";
    }

    @PostMapping("/presentation/register/register")
    public String add(@ModelAttribute UsuarioEntity proveedor) {
        service.proveedorSave(proveedor);
        return "index";
    }

    @GetMapping("/")
    public String loginShow() {
        return "index";
    }

    @PostMapping("/presentation/proveedor/login")
    public String processLogin(Model model, @RequestParam String idProveedor, @RequestParam String contrasena,HttpSession session) {
        Optional<UsuarioEntity> optionalProveedor = service.proveedorLogin(idProveedor, contrasena);
        if (optionalProveedor.isPresent()) {
            if(optionalProveedor.get().getRol().equals("ADM")){
                UsuarioEntity proveedor = optionalProveedor.get();
                session.setAttribute("Administrador", proveedor);
                return "redirect:/presentation/Administrador/show";
            }
            if(optionalProveedor.get().getActivo().equals("ACEP")) {
                UsuarioEntity proveedor = optionalProveedor.get();
                session.setAttribute("usuario", proveedor);
                session.setAttribute("idProveedor", proveedor.getIdUsuario());
                model.addAttribute("usuario", proveedor);
                return "redirect:/presentation/facturas/show";
            } else {
                model.addAttribute("error", "Usuario Inactivo o Rechazado.");
                return "index";
            }
        } else {
            model.addAttribute("error", "Usuario o contraseña inválidos.");
            return "index";
        }
    }

    @GetMapping("/presentation/proveedor/edit")
    public String edit(Model model, HttpSession session) {
        UsuarioEntity proveedor = (UsuarioEntity) session.getAttribute("proveedor");
        model.addAttribute("proveedor", proveedor);
        return "/presentation/proveedorLogin/Edit";
    }

    @GetMapping("/presentation/proveedor/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "index";}

    @GetMapping("/presentation/About/about")
    public String about(){
        return "/presentation/about/About";
    }
}
