package com.example.books.backend.services;

import com.example.books.backend.model.Categoria;
import com.example.books.backend.response.CategoriaResponseRest;
import org.springframework.http.ResponseEntity;

public interface ICategoriaService {
    public ResponseEntity<CategoriaResponseRest> buscarCategorias ();
    public ResponseEntity<CategoriaResponseRest> buscarPorId(Long id);
    public ResponseEntity<CategoriaResponseRest> crear(Categoria categoria);
    public ResponseEntity<CategoriaResponseRest> actualizar(Categoria categoria, Long id);
    public ResponseEntity<CategoriaResponseRest> eliminar(Long id);
}
