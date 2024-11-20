package com.proyectobd.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
@Entity
@Table(name = "pedidos")
public class Pedido implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long idPedido;
    
    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    private Proveedor proveedor;
   
    @Column(name = "fecha_pedido")
    @Temporal(TemporalType.DATE)
    private Date fecha_pedido;
    
    private int total;
    
    @ManyToOne
    @JoinColumn(name = "id_productos")
    private Producto producto;    

    public Pedido() {
    }

    public Pedido(Proveedor proveedor, Date fecha_pedido, int total, Producto producto) {
        this.proveedor = proveedor;
        this.fecha_pedido = fecha_pedido;
        this.total = total;
        this.producto = producto;
    }
    
    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }    
    
    public Date getFecha_pedido() {
        return fecha_pedido;
    }

    public void setFecha_pedido(Date fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
    }
    
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }    
}
