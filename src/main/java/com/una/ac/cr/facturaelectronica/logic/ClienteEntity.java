package com.una.ac.cr.facturaelectronica.logic;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "cliente", schema = "facturaelectronica")
public class ClienteEntity {
    @Id
    @Column(name = "cliente_id")
    private String clienteId;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "correo_electronico")
    private String correoElectronico;
    @Basic
    @Column(name = "usuario_id", insertable=false, updatable=false)
    private String usuarioId;
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario", nullable = false)
    private UsuarioEntity usuarioByUsuarioId;
    @OneToMany(mappedBy = "clienteByCliente")
    private Collection<FacturaEntity> facturasByClienteId;

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
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

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteEntity that = (ClienteEntity) o;
        return Objects.equals(clienteId, that.clienteId) && Objects.equals(nombre, that.nombre) && Objects.equals(correoElectronico, that.correoElectronico) && Objects.equals(usuarioId, that.usuarioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clienteId, nombre, correoElectronico, usuarioId);
    }

    public UsuarioEntity getUsuarioByUsuarioId() {
        return usuarioByUsuarioId;
    }

    public void setUsuarioByUsuarioId(UsuarioEntity usuarioByUsuarioId) {
        this.usuarioByUsuarioId = usuarioByUsuarioId;
    }

    public Collection<FacturaEntity> getFacturasByClienteId() {
        return facturasByClienteId;
    }

    public void setFacturasByClienteId(Collection<FacturaEntity> facturasByClienteId) {
        this.facturasByClienteId = facturasByClienteId;
    }
}
