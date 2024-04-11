DROP DATABASE IF EXISTS FacturaElectronica;
CREATE DATABASE FacturaElectronica;
USE  FacturaElectronica;
-- Tabla de Proveedor
CREATE TABLE usuario (
    id_usuario varchar(12) , 
    nombre VARCHAR(100),
    correo_electronico VARCHAR(100),
    contrasena VARCHAR(100),
    activo varchar(12), -- ACEP: Aceptar, RECH: Rechazar, ESP: Espera
    telefono integer, 
    rol varchar(30), -- PROV: proveedor, ADM: administrador
    constraint PK_usuario primary key(id_usuario)
);
-- Tabla de Clientes
CREATE TABLE cliente (
    cliente_id VARCHAR(12) NOT NULL,
    nombre VARCHAR(255),
    correo_electronico VARCHAR(50),
    usuario_id VARCHAR(12) NOT NULL,
    CONSTRAINT PK_Cliente PRIMARY KEY (cliente_id),
    CONSTRAINT FK_Usuario FOREIGN KEY (usuario_id) REFERENCES usuario (id_usuario)
);
-- Tabla de Productos/Servicios
CREATE TABLE producto (
    producto_id INT NOT NULL,
    codigo varchar (255),
    nombre VARCHAR(255),
    descripcion TEXT,
    precio float,
    total float,  
    usuario_id varchar(12),
     constraint PK_Producto primary key(producto_id),
     constraint FK_Producto foreign key (usuario_id) references usuario (id_usuario)
);
 
-- ------------------------------------------------------------------------
-- Tabla de Facturas
CREATE TABLE factura (
    factura_id INT not null,
    fecha DATE,
    total float,
    cliente VARCHAR(12),
    constraint Pk_facturas primary key (factura_id),
	constraint Fk_facturas foreign key (cliente) references Cliente (cliente_id)
);
 -- Tabla de Facturas-Productos RELACION 
CREATE TABLE facturas_productos (
  id INT NOT NULL AUTO_INCREMENT,
  id_producto INT NOT NULL,
  id_factura INT NOT NULL,
  CONSTRAINT Pk_facturas_productos PRIMARY KEY (id),
  CONSTRAINT Fk_id_producto FOREIGN KEY (id_producto) REFERENCES Producto(producto_id),
  CONSTRAINT Fk_id_factura FOREIGN KEY (id_factura) REFERENCES Factura(factura_id)
);
-- ------------------------------------------------------------------------

select * from usuario;
select * from Cliente;
select * from Producto;