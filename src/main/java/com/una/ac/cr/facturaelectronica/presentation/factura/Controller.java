package com.una.ac.cr.facturaelectronica.presentation.factura;

import com.una.ac.cr.facturaelectronica.data.XML.FacturaXML;
import com.una.ac.cr.facturaelectronica.logic.ClienteEntity;
import com.una.ac.cr.facturaelectronica.logic.FacturaEntity;
import com.una.ac.cr.facturaelectronica.logic.ProductoEntity;
import com.una.ac.cr.facturaelectronica.logic.UsuarioEntity;
import com.una.ac.cr.facturaelectronica.service.ClienteService;
import com.una.ac.cr.facturaelectronica.service.FacturaService;
import com.una.ac.cr.facturaelectronica.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        List<String> proveedoresNombres = new ArrayList<>();
        List<String> clientesNombres = new ArrayList<>();

        for (FacturaEntity factura : facturas) {
            UsuarioEntity proveedor = usuarioService.proveedorById(factura.getProveedor());
            ClienteEntity cliente = clienteService.clienteFindById(factura.getCliente(), usuarioId);
            if(proveedor != null) {
                proveedoresNombres.add(proveedor.getNombre());
                clientesNombres.add(cliente.getNombre());
            } else {
                proveedoresNombres.add(""); // Agregar un elemento vacío si el proveedor es nulo
            }
            if(cliente != null) {

            } else {
                clientesNombres.add(""); // Agregar un elemento vacío si el cliente es nulo
            }
        }

        model.addAttribute("proveedoresNombres", proveedoresNombres);
        model.addAttribute("clientesNombres", clientesNombres);
        model.addAttribute("facturas", facturas);

        return "/presentation/proveedorLogin/factura/listarFacturas";
    }
    /* @GetMapping("/presentation/facturas/listarFacturas")
    public String listarFacturas(Model model, HttpSession session){
        String usuarioId = (String) session.getAttribute("usuarioId");
        model.addAttribute("cliente", session.getAttribute("cliente"));
        model.addAttribute("usuario", session.getAttribute("usuario"));
        Iterable<FacturaEntity> facturas = facturaService.facturaFindAllByProveedorId(usuarioId);
        model.addAttribute("facturas", facturas);
        return "/presentation/proveedorLogin/factura/listarFacturas";
    }*/
    @GetMapping(value = "/factura/{id}/xml", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public String generateXml(@PathVariable("id") int id) {
        FacturaEntity aux = facturaService.getFacturaById(id);
        try {
            FacturaXML facturaXML = new FacturaXML();
            return facturaXML.generateXml(facturaService.facturaFindAllByProveedorId(aux.getProveedor()));
        } catch (Exception e) {
            // Manejar excepción
            return null;
        }

    }
    @PostMapping("/presentation/facturas/add")
    public String addFactura(HttpSession session, Model model) {
        String usuarioId = (String) session.getAttribute("usuarioId");
        String clienteId = (String) session.getAttribute("clienteId");
        Integer productoId = (Integer) session.getAttribute("productoId");

        // Bandera de verificación
        boolean isValid = true;

        if (usuarioId != null && !usuarioId.isEmpty() && clienteId != null && !clienteId.isEmpty() && productoId != null) {
            List<ProductoEntity> productos = (List<ProductoEntity>) session.getAttribute("productos");
            if (productos == null ) {
                productos = new ArrayList<>();
            }

            FacturaEntity factura = new FacturaEntity();
            factura.setCliente(clienteId);
            factura.setFecha(new Date(System.currentTimeMillis()));
            factura.setProveedor(usuarioId);
            factura.setIdProducto(productoId);
            factura.setCantidad(productos.size());

            Double totalFactura = 0.0d;
            for (ProductoEntity producto : productos) {
                totalFactura += producto.getPrecio();
            }
            factura.setTotal(totalFactura);

            session.removeAttribute("productos");

            ClienteEntity cliente = clienteService.clienteFindById(clienteId, usuarioId);
            UsuarioEntity usuario = usuarioService.proveedorById(usuarioId);

            // Verificar si cliente y usuario no son nulos
            if (cliente != null && usuario != null) {
                session.setAttribute("cliente", cliente);
                session.setAttribute("usuario", usuario);

                facturaService.saveFactura(factura);
            } else {
                // Si cliente o usuario son nulos, establecer la bandera de verificación en falso
                isValid = false;
            }
        } else {
            // Si usuarioId, clienteId o productoId son nulos, establecer la bandera de verificación en falso
            isValid = false;
        }

        // Si la bandera de verificación es falsa, agregar un mensaje de error al modelo y redirigir a la vista actual
        if (!isValid) {
            model.addAttribute("error", "No se seleccionó cliente o proveedor");
            return "redirect:/presentation/facturas/add";
        }

        return "redirect:/presentation/facturas/listarFacturas";
    }
    @GetMapping("/presentation/facturas/add")
    public String showAddFacturaForm(Model model, HttpSession session) {
        // Aquí puedes agregar cualquier lógica necesaria para preparar la vista de agregar factura
        // Por ejemplo, podrías agregar atributos al modelo o inicializar algunos valores de sesión

        return "/presentation/proveedorLogin/factura/View"; // Retorna la vista de agregar factura
    }




}
/* @GetMapping("/presentation/facturas/listarFacturas")
    public String listarFacturas(Model model, HttpSession session){
        String usuarioId = (String) session.getAttribute("usuarioId");
        Iterable<FacturaEntity> facturas = facturaService.facturaFindAllByProveedorId(usuarioId);

        List<String> proveedoresNombres = new ArrayList<>();
        List<String> clientesNombres = new ArrayList<>();

        for (FacturaEntity factura : facturas) {
            UsuarioEntity proveedor = usuarioService.proveedorById(factura.getProveedor());
            ClienteEntity cliente = clienteService.clienteFindById(factura.getCliente(), usuarioId);
            if(proveedor != null) {
                proveedoresNombres.add(proveedor.getNombre());
            } else {
                proveedoresNombres.add(""); // Agregar un elemento vacío si el proveedor es nulo
            }
            if(cliente != null) {
                clientesNombres.add(cliente.getNombre());
            } else {
                clientesNombres.add(""); // Agregar un elemento vacío si el cliente es nulo
            }
        }

        model.addAttribute("proveedoresNombres", proveedoresNombres);
        model.addAttribute("clientesNombres", clientesNombres);
        model.addAttribute("facturas", facturas);

        return "/presentation/proveedorLogin/factura/listarFacturas";
    }
    /* @GetMapping("/presentation/facturas/listarFacturas")
    public String listarFacturas(Model model, HttpSession session){
        String usuarioId = (String) session.getAttribute("usuarioId");
        model.addAttribute("cliente", session.getAttribute("cliente"));
        model.addAttribute("usuario", session.getAttribute("usuario"));
        Iterable<FacturaEntity> facturas = facturaService.facturaFindAllByProveedorId(usuarioId);
        model.addAttribute("facturas", facturas);
        return "/presentation/proveedorLogin/factura/listarFacturas";
    }*/
/*
@GetMapping(value = "/factura/{id}/xml", produces = MediaType.APPLICATION_XML_VALUE)
@ResponseBody
public String generateXml(@PathVariable("id") int id) {
    FacturaEntity aux = facturaService.getFacturaById(id);
    try {
        FacturaXML facturaXML = new FacturaXML();
        return facturaXML.generateXml(facturaService.facturaFindAllByProveedorId(aux.getProveedor()));
    } catch (Exception e) {
        // Manejar excepción
        return null;
    }

}
@PostMapping("/presentation/facturas/add")
public String addFactura(HttpSession session, Model model) {
    String usuarioId = (String) session.getAttribute("usuarioId");
    String clienteId = (String) session.getAttribute("clienteId");
    Integer productoId = (Integer) session.getAttribute("productoId");

    if (usuarioId != null && !usuarioId.isEmpty() && clienteId != null && !clienteId.isEmpty() && productoId != null) {
        List<ProductoEntity> productos = (List<ProductoEntity>) session.getAttribute("productos");
        if (productos == null ) {
            productos = new ArrayList<>();
        }

        FacturaEntity factura = new FacturaEntity();
        factura.setCliente(clienteId);
        factura.setFecha(new Date(System.currentTimeMillis()));
        factura.setProveedor(usuarioId);
        factura.setIdProducto(productoId);
        factura.setCantidad(productos.size());

        Double totalFactura = 0.0d;
        for (ProductoEntity producto : productos) {
            totalFactura += producto.getPrecio();
        }
        factura.setTotal(totalFactura);

        session.removeAttribute("productos");

        ClienteEntity cliente = clienteService.clienteFindById(clienteId, usuarioId);
        UsuarioEntity usuario = usuarioService.proveedorById(usuarioId);

        // Verificar si cliente y usuario no son nulos
        if (cliente != null && usuario != null) {
            session.setAttribute("cliente", cliente);
            session.setAttribute("usuario", usuario);

            facturaService.saveFactura(factura);
        } else {
            // Si cliente o usuario son nulos, retornar la vista actual sin realizar ninguna acción
            model.addAttribute("error", "No se seleccionó cliente o proveedor");
            return "redirect:/presentation/facturas/add";
        }
    }

    return "redirect:/presentation/facturas/listarFacturas";
}*/