package com.una.ac.cr.facturaelectronica.logic;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.sql.Date;
import java.util.Objects;

@Entity
@XmlRootElement(name = "factura")
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "factura", schema = "facturaelectronica", catalog = "")
public class FacturaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "factura_id")
    @XmlElement
    private int facturaId;
    @Basic
    @Column(name = "fecha")
    @XmlElement
    private Date fecha;
    @Basic
    @Column(name = "total")
    @XmlElement
    private Double total;
    @Basic
    @Column(name = "cantidad")
    @XmlElement
    private Integer cantidad;
    @Basic
    @Column(name = "cliente")
    @XmlElement
    private String cliente;
    @Basic
    @Column(name = "proveedor")
    @XmlElement
    private String proveedor;
    @Basic
    @Column(name = "id_producto")
    @XmlElement
    private Integer idProducto;

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

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacturaEntity that = (FacturaEntity) o;
        return facturaId == that.facturaId && Objects.equals(fecha, that.fecha) && Objects.equals(total, that.total) && Objects.equals(cantidad, that.cantidad) && Objects.equals(cliente, that.cliente) && Objects.equals(proveedor, that.proveedor) && Objects.equals(idProducto, that.idProducto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(facturaId, fecha, total, cantidad, cliente, proveedor, idProducto);
    }
}
