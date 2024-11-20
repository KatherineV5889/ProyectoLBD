package com.proyectobd.domain;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_VENTAS")
    private Long idVentas;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "ID_EMPLEADO", referencedColumnName = "ID_EMPLEADOS")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUCTO", referencedColumnName = "ID_PRODUCTO")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "ID_TIENDA", referencedColumnName = "ID_TIENDA")
    private Tienda tienda;

    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Column(name = "CANTIDAD")
    private Integer cantidad;

    @Column(name = "TOTAL")
    private Double total;

    public Long getIdVentas() {
        return idVentas;
    }

    public void setIdVentas(Long idVentas) {
        this.idVentas = idVentas;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}

