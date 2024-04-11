package com.una.ac.cr.facturaelectronica.presentation.factura;

import com.una.ac.cr.facturaelectronica.logic.UsuarioEntity;
import com.una.ac.cr.facturaelectronica.service.FacturaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        model.addAttribute("usuario", session.getAttribute("usuario"));
        model.addAttribute("proveedorName", usuario.getNombre());
        return "/presentation/proveedorLogin/factura/View";
    }

}
