package com.una.ac.cr.facturaelectronica.presentation.producto;


import com.una.ac.cr.facturaelectronica.logic.ProductoEntity;
import com.una.ac.cr.facturaelectronica.service.ProductoService;
import com.una.ac.cr.facturaelectronica.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.stereotype.Controller("producto")
public class Controller {
    @Autowired
    ProductoService productoService;
    @Autowired
    private UsuarioService usuarioService;
    @GetMapping("/presentation/producto/show")
    public String show(Model model, HttpSession session){
        String idProveedor = (String) session.getAttribute("idProveedor");
        model.addAttribute("productos", productoService.productoFindAllByProveedorId(idProveedor));
        return "/presentation/proveedorLogin/producto/listarProductos";
    }

    @GetMapping("/presentation/producto/register")
    public String register(Model model, @RequestParam("proveedorId") String proveedorId ){
        model.addAttribute("idUsuario", proveedorId);
        return "/presentation/proveedorLogin/producto/View";
    }

    @PostMapping("/presentation/producto/add")
    public String add(@ModelAttribute ProductoEntity producto, @RequestParam("idUsuario") String idProveedor){
        producto.setUsuarioId(idProveedor);
        producto.setUsuarioByUsuarioId(usuarioService.proveedorById(idProveedor));
        productoService.productoSave(producto);
        return "redirect:/presentation/producto/show";
    }
}
