package com.example.books.backend.controllers;

import com.example.books.backend.model.Categoria;
import com.example.books.backend.response.CategoriaResponseRest;
import com.example.books.backend.services.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class CategoriaRestController {

    @Autowired
    private ICategoriaService services;

    @GetMapping("/categorias")
    public ResponseEntity <CategoriaResponseRest> consultaCat(){
        ResponseEntity <CategoriaResponseRest> response = services.buscarCategorias();
        return response;
    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseRest> consultaPorId (@PathVariable Long id){
        ResponseEntity<CategoriaResponseRest> response = services.buscarPorId(id);
        return response;
    }
    @PostMapping("/categorias")
    public ResponseEntity <CategoriaResponseRest> crear (@RequestBody Categoria request){
        ResponseEntity<CategoriaResponseRest> response = services.crear(request);
        return response;
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity <CategoriaResponseRest> actualizar (@RequestBody Categoria request, @PathVariable Long id){
        ResponseEntity<CategoriaResponseRest> response = services.actualizar(request, id);
        return response;
    }
    @DeleteMapping("/categorias/{id}")
    public ResponseEntity <CategoriaResponseRest> eliminar(@PathVariable Long id){
        ResponseEntity<CategoriaResponseRest> response = services.eliminar(id);
        return response;
    }
}
