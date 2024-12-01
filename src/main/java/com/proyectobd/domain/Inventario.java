package com.proyectobd.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "inventarios")
public class Inventario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inventario")
    private Long idInventario;

    private int totalProductos;
    
    @ManyToOne
    @JoinColumn(name = "id_tienda")
    private Tienda tienda; 
    
    public Inventario() {
    }

    public Inventario(int totalProductos, Tienda tienda) {
        this.totalProductos = totalProductos;
        this.tienda = tienda;
    }
    
    public int getTotalProductos() {
        return totalProductos;
    }

    public void setTotalProductos(int totalProductos) {
        this.totalProductos = totalProductos;
    }
    
}
