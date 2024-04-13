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

import java.util.ArrayList;
import java.util.List;

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

//    @GetMapping("/presentation/producto/findByCodigo")
//    public String find(HttpSession session, @RequestParam("codigoId") String codigoId, @RequestParam("idProveedor") String proveedorId){
//           session.setAttribute ("producto", productoService.findProductoById(codigoId, proveedorId));
//        return "/presentation/proveedorLogin/factura/View";
//    }

    @GetMapping("/presentation/producto/findByCodigo")
    public String find(Model model, HttpSession session, @RequestParam("codigoId") String codigoId){
        String idProveedor = (String) session.getAttribute("idProveedor");

        // Obtener la lista de productos de la sesión, si existe
        List<ProductoEntity> productos = (List<ProductoEntity>) session.getAttribute("productos");

        // Verificar si la lista de productos no existe en la sesión
        if(productos == null) {
            productos = new ArrayList<>();
        }

        // Buscar el producto por código y proveedor
        ProductoEntity producto = productoService.findProductoById(codigoId, idProveedor);

        // Si el producto existe, agregarlo a la lista de productos en sesión
        if(producto != null) {
            productos.add(producto);
            session.setAttribute("productoId", producto.getProductoId());
        }else {
            // Si el producto no existe, mostrar un mensaje al usuario
            model.addAttribute("mensaje", "Producto no encontrado");
        }

        // Guardar la lista actualizada en la sesión
        session.setAttribute("productos", productos);

        // Agregar la lista de productos al modelo para mostrar en la vista
        model.addAttribute("productos", productos);

        return "/presentation/proveedorLogin/factura/View";
    }

}
