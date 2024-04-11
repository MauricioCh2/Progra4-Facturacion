package com.una.ac.cr.facturaelectronica.logic;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "usuario", schema = "facturaelectronica")
public class UsuarioEntity {
    @Id
    @Column(name = "id_usuario")
    private String idUsuario;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "correo_electronico")
    private String correoElectronico;
    @Basic
    @Column(name = "contrasena")
    private String contrasena;
    @Basic
    @Column(name = "activo")
    private String activo;
    @Basic
    @Column(name = "telefono")
    private Integer telefono;
    @Basic
    @Column(name = "rol")
    private String rol;
    @OneToMany(mappedBy = "usuarioByUsuarioId")
    private Collection<ClienteEntity> clientesByIdUsuario;
    @OneToMany(mappedBy = "usuarioByUsuarioId")
    private Collection<ProductoEntity> productosByIdUsuario;


    public UsuarioEntity() {
    }
    public UsuarioEntity(String idUsuario, String nombre, String correoElectronico, String contrasena, Integer telefono) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        this.activo = "ESP";
        this.telefono = telefono;
        this.rol = "PROV";
    }
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioEntity that = (UsuarioEntity) o;
        return Objects.equals(idUsuario, that.idUsuario) && Objects.equals(nombre, that.nombre) && Objects.equals(correoElectronico, that.correoElectronico) && Objects.equals(contrasena, that.contrasena) && Objects.equals(activo, that.activo) && Objects.equals(telefono, that.telefono) && Objects.equals(rol, that.rol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, nombre, correoElectronico, contrasena, activo, telefono, rol);
    }

    public Collection<ClienteEntity> getClientesByIdUsuario() {
        return clientesByIdUsuario;
    }

    public void setClientesByIdUsuario(Collection<ClienteEntity> clientesByIdUsuario) {
        this.clientesByIdUsuario = clientesByIdUsuario;
    }

    public Collection<ProductoEntity> getProductosByIdUsuario() {
        return productosByIdUsuario;
    }

    public void setProductosByIdUsuario(Collection<ProductoEntity> productosByIdUsuario) {
        this.productosByIdUsuario = productosByIdUsuario;
    }
}
