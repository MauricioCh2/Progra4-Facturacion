package com.una.ac.cr.facturaelectronica.presentation.factura;

import com.una.ac.cr.facturaelectronica.logic.UsuarioEntity;
import com.una.ac.cr.facturaelectronica.service.FacturaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@org.springframework.stereotype.Controller("Facturas")
public class Controller {
    @Autowired
    FacturaService facturaService;

    public Controller(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @GetMapping("/presentation/facturas/show")
    public String showFacturas(Model model, HttpSession  session){
        UsuarioEntity  usuario = (UsuarioEntity) session.getAttribute("usuario");
        session.setAttribute("usuario", session.getAttribute("usuario"));
        session.setAttribute("proveedorName", usuario.getNombre());
        return "/presentation/proveedorLogin/factura/View";
    }
    @GetMapping("/presentation/facturas/listarFacturas")
    public String listarFacturas(Model model, HttpSession session){
//        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
//        model.addAttribute("facturas", facturaService.facturaFindAllByProveedorId(usuario.getIdUsuario()));
        return "/presentation/proveedorLogin/factura/listarFacturas";
    }

    @PostMapping("/presentation/facturas/add")
    public String addFactura(HttpSession session){
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
//        facturaService.facturaSave(usuario.getIdUsuario());
        return "redirect:/presentation/facturas/show";
    }



}
