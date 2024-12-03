package com.proyectobd.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
@Entity
@Table(name = "ventas")
public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ventas")
    private Long idVentas;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_tienda")
    private Tienda tienda;

    private Date fecha;
    private Integer cantidad;
    private Double total;

    public Venta() {
    }

    public Venta(Cliente cliente, Empleado empleado, Producto producto, Tienda tienda, Date fecha, Integer cantidad, Double total) {
        this.cliente = cliente;
        this.empleado = empleado;
        this.producto = producto;
        this.tienda = tienda;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.total = total;
    }
}
