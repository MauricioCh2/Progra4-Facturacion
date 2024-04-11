package com.una.ac.cr.facturaelectronica.logic;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "factura", schema = "facturaelectronica")
public class FacturaEntity {
    @Id
    @Column(name = "factura_id")
    private int facturaId;
    @Basic
    @Column(name = "fecha")
    private Date fecha;
    @Basic
    @Column(name = "total")
    private Double total;
    @Basic
    @Column(name = "cliente", insertable=false, updatable=false)
    private String cliente;
    @ManyToOne
    @JoinColumn(name = "cliente", referencedColumnName = "cliente_id")
    private ClienteEntity clienteByCliente;
    @OneToMany(mappedBy = "facturaByIdFactura")
    private Collection<FacturasProductosEntity> facturasProductosByFacturaId;

    public int getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacturaEntity that = (FacturaEntity) o;
        return facturaId == that.facturaId && Objects.equals(fecha, that.fecha) && Objects.equals(total, that.total) && Objects.equals(cliente, that.cliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(facturaId, fecha, total, cliente);
    }

    public ClienteEntity getClienteByCliente() {
        return clienteByCliente;
    }

    public void setClienteByCliente(ClienteEntity clienteByCliente) {
        this.clienteByCliente = clienteByCliente;
    }

    public Collection<FacturasProductosEntity> getFacturasProductosByFacturaId() {
        return facturasProductosByFacturaId;
    }

    public void setFacturasProductosByFacturaId(Collection<FacturasProductosEntity> facturasProductosByFacturaId) {
        this.facturasProductosByFacturaId = facturasProductosByFacturaId;
    }
}
