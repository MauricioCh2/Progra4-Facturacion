package com.una.ac.cr.facturaelectronica.presentation.factura;

import com.una.ac.cr.facturaelectronica.logic.ClienteEntity;
import com.una.ac.cr.facturaelectronica.logic.FacturaEntity;
import com.una.ac.cr.facturaelectronica.logic.ProductoEntity;
import com.una.ac.cr.facturaelectronica.logic.UsuarioEntity;
import com.una.ac.cr.facturaelectronica.service.ClienteService;
import com.una.ac.cr.facturaelectronica.service.FacturaService;
import com.una.ac.cr.facturaelectronica.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller("Facturas")
public class Controller {
    @Autowired
    FacturaService facturaService;
 @Autowired
    ClienteService clienteService;
 @Autowired
    UsuarioService usuarioService;
    public Controller(FacturaService facturaService, ClienteService clienteService, UsuarioService usuarioService) {
        this.facturaService = facturaService;
        this.clienteService = clienteService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/presentation/facturas/show")
    public String showFacturas(Model model, HttpSession  session){
        UsuarioEntity  usuario = (UsuarioEntity) session.getAttribute("usuario");
        session.setAttribute("usuario", session.getAttribute("usuario"));
        session.setAttribute("usuarioId", usuario.getIdUsuario());
        session.setAttribute("proveedorName", usuario.getNombre());
        return "/presentation/proveedorLogin/factura/View";
    }



    @GetMapping("/presentation/facturas/listarFacturas")
    public String listarFacturas(Model model, HttpSession session){
        String usuarioId = (String) session.getAttribute("usuarioId");
        Iterable<FacturaEntity> facturas = facturaService.facturaFindAllByProveedorId(usuarioId);

        // Crear listas para almacenar los nombres de los proveedores y clientes
        List<String> proveedoresNombres = new ArrayList<>();
        List<String> clientesNombres = new ArrayList<>();

        // Iterar sobre las facturas y buscar los nombres de los proveedores y clientes
        for (FacturaEntity factura : facturas) {
            UsuarioEntity proveedor = usuarioService.proveedorById(factura.getProveedor());
            ClienteEntity cliente = clienteService.clienteFindById(factura.getCliente(), usuarioId);

            proveedoresNombres.add(proveedor.getNombre());
            clientesNombres.add(cliente.getNombre());
        }

        // Añadir las listas al modelo
        model.addAttribute("proveedoresNombres", proveedoresNombres);
        model.addAttribute("clientesNombres", clientesNombres);
        model.addAttribute("facturas", facturas);

        return "/presentation/proveedorLogin/factura/listarFacturas";
    }
    @PostMapping("/presentation/facturas/add")
    public String addFactura(HttpSession session, Model model) {
        //        // Obtener el proveedor, cliente y productos de la sesión
        String usuarioId = (String) session.getAttribute("usuarioId");
        String clienteId = (String) session.getAttribute("clienteId");
        Integer productoId = (Integer) session.getAttribute("productoId");
        List<ProductoEntity> productos = (List<ProductoEntity>) session.getAttribute("productos");

        // Crear una nueva instancia de la entidad factura
        FacturaEntity factura = new FacturaEntity();
        factura.setCliente(clienteId);
        factura.setFecha(new Date(System.currentTimeMillis()));
        factura.setProveedor(usuarioId);
        factura.setIdProducto(productoId);
        factura.setCantidad(productos.size());

        // Calcular el total de la factura sumando los precios de los productos
        Double totalFactura = 0.0d;
        for (ProductoEntity producto : productos) {
            totalFactura += producto.getPrecio();
        }
        factura.setTotal(totalFactura);
        // Limpiar la lista de productos de la sesión
        session.removeAttribute("productos");
//        session.removeAttribute("clienteNombre");

        ClienteEntity cliente = clienteService.clienteFindById(clienteId, usuarioId);
        UsuarioEntity usuario = usuarioService.proveedorById(usuarioId);
        session.setAttribute("cliente", cliente);
        session.setAttribute("usuario", usuario);

        facturaService.saveFactura(factura);
        return "redirect:/presentation/facturas/listarFacturas";
    }





}
