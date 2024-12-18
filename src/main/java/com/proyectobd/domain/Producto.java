package com.proyectobd.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "productos")
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    @Column(name = "id_categoria")
    private Long idCategoria;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "precio_producto")
    private Double precioProducto;

    @Column(name = "stock_producto")
    private Integer stockProducto;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "id_proveedor")
    private Long idProveedor;

    public Producto() {
    }
}