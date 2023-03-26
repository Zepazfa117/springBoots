package com.example.books.backend.dao;

import com.example.books.backend.model.Libro;
import org.springframework.data.repository.CrudRepository;

public interface ILibroDao extends CrudRepository<Libro,Long> {
}
