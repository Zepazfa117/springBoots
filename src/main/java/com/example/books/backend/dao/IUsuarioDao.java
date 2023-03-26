package com.example.books.backend.dao;

import com.example.books.backend.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioDao extends CrudRepository <Usuario, Long> {
    public Usuario findByNombreUsuario (String nombreUsuario);

    @Query("SELECT u FROM Usuario u where u.nombreUsuario =? 1")
    public Usuario findByIdINombreUsuarioV2(String nombreUsuario);


}
