package com.example.books.backend.services;

import com.example.books.backend.model.Libro;
import com.example.books.backend.response.LibroResponseRest;
import org.springframework.http.ResponseEntity;


public interface ILibroService {
    public ResponseEntity<LibroResponseRest> buscarLibros();
    public ResponseEntity<LibroResponseRest> buscarPorId(Long id);
    public ResponseEntity<LibroResponseRest> crear(Libro libro);
    public ResponseEntity<LibroResponseRest> actualizar(Libro libro, Long id);
    public ResponseEntity<LibroResponseRest> eliminar(Long id);
}
