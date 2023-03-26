package com.example.books.backend.controllers;

import com.example.books.backend.model.Libro;
import com.example.books.backend.response.LibroResponseRest;
import com.example.books.backend.services.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class LibroRestController {
    @Autowired
    private ILibroService services;

    @GetMapping("/libros")
    public ResponseEntity<LibroResponseRest> consultarLib (){
        ResponseEntity<LibroResponseRest> response = services.buscarLibros();
        return response;
    }

    @GetMapping("/libros/{id}")
    public ResponseEntity<LibroResponseRest> consultaPorId (@PathVariable Long id){
        ResponseEntity<LibroResponseRest> response = services.buscarPorId(id);
        return response;
    }

    @PostMapping("/libros")
    public ResponseEntity <LibroResponseRest> crear (@RequestBody Libro request){
        ResponseEntity<LibroResponseRest> response = services.crear(request);
        return response;
    }
    @PutMapping("/libros/{id}")
    public ResponseEntity <LibroResponseRest> actualizar (@RequestBody Libro request, @PathVariable Long id){
        ResponseEntity<LibroResponseRest> response = services.actualizar(request, id);
        return response;
    }
    @DeleteMapping("/libros/{id}")
    public ResponseEntity <LibroResponseRest> eliminar(@PathVariable Long id){
        ResponseEntity<LibroResponseRest> response = services.eliminar(id);
        return response;
    }

}
