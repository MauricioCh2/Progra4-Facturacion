package com.una.ac.cr.facturaelectronica;
import com.una.ac.cr.facturaelectronica.data.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FacturaElectronicaApplication implements CommandLineRunner {
    @Autowired
    public UsuarioRepository proveedorRepository;
    public static void main(String[] args) {
        SpringApplication.run(FacturaElectronicaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        proveedorRepository.save(new ProveedorEntity("PROV1", "Proveedor A", "proveedora@example.com", "contrasena123", true, 123456789, "San Jose", "Tipo 1"));
//        proveedorRepository.save(new ProveedorEntity("1", "a", "proveedora@example.com", "123", true, 123456789, "San Jose", "Tipo 1"));
    }
}
