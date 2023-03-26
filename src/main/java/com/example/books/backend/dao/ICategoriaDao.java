package com.example.books.backend.dao;

import com.example.books.backend.model.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface ICategoriaDao  extends CrudRepository <Categoria, Long> {

}
