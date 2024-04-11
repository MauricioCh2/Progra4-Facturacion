package com.una.ac.cr.facturaelectronica.logic;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "facturas_productos", schema = "facturaelectronica")
public class FacturasProductosEntity {
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "id_producto", insertable=false, updatable=false)
    private int idProducto;
    @Basic
    @Column(name = "id_factura", insertable=false, updatable=false)
    private int idFactura;
    @ManyToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "producto_id", nullable = false)
    private ProductoEntity productoByIdProducto;
    @ManyToOne
    @JoinColumn(name = "id_factura", referencedColumnName = "factura_id", nullable = false)
    private FacturaEntity facturaByIdFactura;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacturasProductosEntity that = (FacturasProductosEntity) o;
        return id == that.id && idProducto == that.idProducto && idFactura == that.idFactura;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idProducto, idFactura);
    }

    public ProductoEntity getProductoByIdProducto() {
        return productoByIdProducto;
    }

    public void setProductoByIdProducto(ProductoEntity productoByIdProducto) {
        this.productoByIdProducto = productoByIdProducto;
    }

    public FacturaEntity getFacturaByIdFactura() {
        return facturaByIdFactura;
    }

    public void setFacturaByIdFactura(FacturaEntity facturaByIdFactura) {
        this.facturaByIdFactura = facturaByIdFactura;
    }
}
