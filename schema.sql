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
    factura_id INT not null auto_increment,
    fecha DATE,
    total float,
    cantidad int,
    cliente VARCHAR(12),
    proveedor VARCHAR(12),
    id_producto int,
    constraint Pk_facturas primary key (factura_id),
    constraint Fk1_facturas foreign key (cliente) references cliente (cliente_id),
    constraint FK2_facturas foreign key (proveedor) references usuario (id_usuario),
    constraint FK3_facturas foreign key (id_producto) references producto (producto_id)
);
-- ------------------------------------------------------------------------



INSERT INTO usuario (id_usuario, nombre, correo_electronico, contrasena, activo, telefono, rol) 
VALUES ('11', 'Juan Perez', 'juan@example.com', '11', 'ACEP', 123456789, 'PROV'),
       ('22', 'Maria Lopez', 'maria@example.com', '22', 'AC', 987654321, 'ADM');

INSERT INTO cliente (cliente_id, nombre, correo_electronico, usuario_id) 
VALUES ('cli001', 'Cliente Uno', 'cliente1@example.com', '11'),
       ('cli002', 'Cliente Dos', 'cliente2@example.com', '22');


INSERT INTO producto (producto_id, codigo, nombre, descripcion, precio, total, usuario_id) 
VALUES (1, 'PROD001', 'Producto Uno', 'Descripción del producto uno', 100.0, 500.0, '11'),
       (2, 'PROD002', 'Producto Dos', 'Descripción del producto dos', 150.0, 300.0, '22');


select * from usuario;
select * from Cliente;
select * from Producto;
select * from factura;
