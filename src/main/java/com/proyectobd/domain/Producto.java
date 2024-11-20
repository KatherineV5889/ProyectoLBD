package com.proyectobd.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "productos")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria; 

    private String nombre;
    private Double precioProducto;
    private Integer stockProducto;
    private String descripcion;

    public Producto() {
    }

    public Producto(String nombre, Double precioProducto, Integer stockProducto, String descripcion, Categoria categoria) {
        this.nombre = nombre;
        this.precioProducto = precioProducto;
        this.stockProducto = stockProducto;
        this.descripcion = descripcion;
        this.categoria = categoria;
    }
}
