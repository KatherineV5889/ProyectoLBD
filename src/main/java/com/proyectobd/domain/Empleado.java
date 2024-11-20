package com.proyectobd.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "empleados")
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleados")
    private Long idEmpleados;

    @ManyToOne
    @JoinColumn(name = "id_tienda")
    private Tienda tienda; 

    private String nombre;
    private String apellido;
    private String puesto;

    public Empleado() {
    }

    public Empleado(String nombre, String apellido, String puesto, Tienda tienda) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.puesto = puesto;
        this.tienda = tienda;
    }
}
