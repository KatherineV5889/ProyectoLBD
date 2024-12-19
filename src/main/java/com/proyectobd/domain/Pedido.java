package com.proyectobd.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "pedidos")
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long idPedido;

    @Column(name = "id_proveedor")
    private Long idProveedor;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_pedido")
    private Date fechaPedido;

    @Column(name = "total_unidades")
    private Integer totalUnidades;

    @Column(name = "id_producto")
    private Long idProducto;

    @Column(name = "id_tienda")
    private Long idTienda;

    public Pedido() {
    }
}
