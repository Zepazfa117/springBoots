package com.example.books.backend.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="roles")
public class Role implements Serializable {

    private static final long serialVersionUID = -5167102603938388029L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 20)
    private String nombre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
