package com.example.books.backend.services;

import com.example.books.backend.dao.ICategoriaDao;
import com.example.books.backend.model.Categoria;
import com.example.books.backend.response.CategoriaResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements ICategoriaService{

    private static final Logger log = LoggerFactory.getLogger(CategoriaServiceImpl.class);

    @Autowired
    private ICategoriaDao categoriaDao;
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoriaResponseRest> buscarCategorias() {

        log.info("inicio metodo buscarCategoria()");

        CategoriaResponseRest response = new CategoriaResponseRest();

        try {
            List<Categoria> categoria = (List<Categoria>) categoriaDao.findAll();
            response.getCategoriaResponse().setCategoria(categoria);
            response.setMetadata("respuesta OK", "00", "respuesta exitosa");
        }catch (Exception e){
            response.setMetadata("respuesta NO OK", "-1", "Error al consultar categorias");
            log.error("error al consultar categorias: ", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK); // devuelve 200
    }
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoriaResponseRest> buscarPorId(Long id){
        log.info("Inicio metodo buscarPorId");
        CategoriaResponseRest response = new CategoriaResponseRest();
        List<Categoria> list = new ArrayList<>();

        try {
            Optional<Categoria> categoria = categoriaDao.findById(id);
            if (categoria.isPresent()){
                list.add(categoria.get());
                response.getCategoriaResponse().setCategoria(list);
            }
            else {
                log.error("Error en consultar categoria");
                response.setMetadata("Respuesta NO OK", "-1","Categoria no encontrada");
                return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error en consultar categoria");
            response.setMetadata("Respuesta NO OK", "-1","Error al consultar categoria");
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setMetadata("respuesta ok", "00", "respuesta exitosa");
        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK); // devuelve 200;
    }

    @Override
    @Transactional
    public ResponseEntity<CategoriaResponseRest> crear(Categoria categoria){
        log.info("Inicio metodo crear");

        CategoriaResponseRest response = new CategoriaResponseRest();
        List<Categoria> list = new ArrayList<>();

        try {
            Categoria categoriaGuardada = categoriaDao.save(categoria);

            if (categoriaGuardada != null){
                list.add(categoriaGuardada);
                response.getCategoriaResponse().setCategoria(list);
            }
            else {
                log.error("Error en grabar categoria");
                response.setMetadata("Respuesta NO OK", "-1","Categorize no guardada");
                return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            log.error("Error en grabar categoria");
            response.setMetadata("Respuesta NO OK", "-1","Error al grabar categoria");
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setMetadata("respuesta OK", "00", "categoria creada");
        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK); // devuelve 200;
    }

    @Override
    @Transactional
    public ResponseEntity<CategoriaResponseRest> actualizar(Categoria categoria, Long id) {
        log.info("Inicio metodo actualizar");

        CategoriaResponseRest response = new CategoriaResponseRest();
        List<Categoria> list = new ArrayList<>();

        try {
        Optional<Categoria> categoriaBuscada = categoriaDao.findById(id);

        if (categoriaBuscada.isPresent()){
            categoriaBuscada.get().setNombre(categoria.getNombre());
            categoriaBuscada.get().setDescripcion(categoria.getDescripcion());

            Categoria categoriaActualizar = categoriaDao.save(categoriaBuscada.get()); //actualizando
            if (categoriaActualizar != null){
                response.setMetadata("Respuesta OK", "00", "Categoria Actualizada" );
                list.add(categoriaActualizar);
                response.getCategoriaResponse().setCategoria(list);
            }else {
                log.error("Error al actualizar categoria");
                response.setMetadata("Respuesta NO OK", "-1", "Categoria NO Actualizada");
                return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST);
            }

        }else {
            log.error("Error al actualizar categoria");
            response.setMetadata("Respuesta NO OK", "-1", "Categoria NO Actualizada");
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);
        }
        }catch (Exception e){
            log.error("Error al actualizar categoria", e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta NO OK", "-1", "Categoria NO Actualizada");
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoriaResponseRest> eliminar(Long id) {
        log.info("Inicio metodo Eliminar");

        CategoriaResponseRest response = new CategoriaResponseRest();
        List<Categoria> list = new ArrayList<>();

        try {
            Optional<Categoria> categoriaBuscada = categoriaDao.findById(id);
            if (categoriaBuscada.isPresent()){
                //eliminamos registro
                categoriaDao.deleteById(id);
                response.setMetadata("Respuesta OK", "00", "Categoria Eliminada");
            }else {
                log.error("Error en eliminar categoria");
                response.setMetadata("Respuesta NO OK", "-1","Error al Eliminar categoria");
                return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error en eliminar categoria");
            e.getStackTrace();
            response.setMetadata("Respuesta NO OK", "-1","Error al eliminar categoria");
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
    }

}
