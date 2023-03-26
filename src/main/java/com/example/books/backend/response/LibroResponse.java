package com.example.books.backend.response;

import com.example.books.backend.model.Libro;

import java.util.List;

public class LibroResponse {
    private List<Libro> libro;

    public List<Libro> getLibro() {
        return libro;
    }
    public void setLibro(List<Libro> libro) {
        this.libro = libro;
    }
}
