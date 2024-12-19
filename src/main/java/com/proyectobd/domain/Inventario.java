package com.proyectobd.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "inventarios")
public class Inventario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inventario")
    private Long idInventario;

    @Column(name = "total_productos")
    private Integer totalProductos;

    @Column(name = "id_tienda")
    private Long idTienda;

    public Inventario() {
    }
}
