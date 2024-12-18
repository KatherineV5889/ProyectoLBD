package com.proyectobd.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "empleados")
public class Empleado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleados")
    private Long idEmpleados;

    @ManyToOne
    @JoinColumn(name = "id_tienda")
    private Tienda tienda;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "puesto")
    private String puesto;

    public Empleado() {}
}