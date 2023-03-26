package com.example.books.backend.services;
import com.example.books.backend.dao.ILibroDao;
import com.example.books.backend.model.Libro;
import com.example.books.backend.response.LibroResponseRest;
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
public class LibroServiceImpl implements ILibroService{
    private static final Logger log = LoggerFactory.getLogger(LibroServiceImpl.class);
    @Autowired
    private ILibroDao LibroDao;
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<LibroResponseRest> buscarLibros() {

        log.info("inicio metodo buscarLibros()");

        LibroResponseRest response = new LibroResponseRest();
            try {
                List<Libro> libro = (List<Libro>) LibroDao.findAll();
                response.getLibroResponse().setLibro(libro);
                response.setMetadata("respuesta OK", "00", "respuesta exitosa");
            }catch (Exception e){
                response.setMetadata("respuesta NO OK", "-1", "Error al consultar Libro");
                log.error("error al consultar Libros: ", e.getMessage());
                e.getStackTrace();
                return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK); // devuelve 200
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<LibroResponseRest> buscarPorId(Long id) {
        log.info("Inicio metodo buscarPorId");
        LibroResponseRest response = new LibroResponseRest();
        List<Libro> list = new ArrayList<>();

        try {
            Optional<Libro> libro = LibroDao.findById(id);
            if (libro.isPresent()){
                list.add(libro.get());
                response.getLibroResponse().setLibro(list);
            }
            else {
                log.error("Error en consultar libro");
                response.setMetadata("Respuesta NO OK", "-1","Libro no encontrado");
                return new ResponseEntity<LibroResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error en consultar Libro");
            response.setMetadata("Respuesta NO OK", "-1","Error al consultar Libro");
            return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setMetadata("respuesta ok", "00", "respuesta exitosa");
        return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK); // devuelve un 200
    }

    @Override
    public ResponseEntity<LibroResponseRest> crear(Libro libro) {
        log.info("Inicio metodo crear");

        LibroResponseRest response = new LibroResponseRest();
        List<Libro> list = new ArrayList<>();

        try {
            Libro libroGuardado = LibroDao.save(libro);

            if (libroGuardado != null){
                list.add(libroGuardado);
                response.getLibroResponse().setLibro(list);
            }
            else {
                log.error("Error en grabar Libro");
                response.setMetadata("Respuesta NO OK", "-1","Libro no guardada");
                return new ResponseEntity<LibroResponseRest>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            log.error("Error en grabar categoria");
            response.setMetadata("Respuesta NO OK", "-1","Error al grabar Libro");
            return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setMetadata("respuesta ok", "00", "Libro creada");
        return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LibroResponseRest> actualizar(Libro libro, Long id) {
        log.info("Inicio metodo actualizar");

        LibroResponseRest response = new LibroResponseRest();
        List<Libro> list = new ArrayList<>();

        try {
            Optional<Libro> libroBuscado = LibroDao.findById(id);

            if (libroBuscado.isPresent()){
                libroBuscado.get().setNombre(libro.getNombre());
                libroBuscado.get().setDescripcion(libro.getDescripcion());

                Libro libroActualizar = LibroDao.save(libroBuscado.get()); //actualizando
                if (libroActualizar != null){
                    response.setMetadata("Respuesta OK", "00", "Libro Actualizada" );
                    list.add(libroActualizar);
                    response.getLibroResponse().setLibro(list);
                }else {
                    log.error("Error al actualizar Libro");
                    response.setMetadata("Respuesta NO OK", "-1", "Libro NO Actualizada");
                    return new ResponseEntity<LibroResponseRest>(response, HttpStatus.BAD_REQUEST);
                }

            }else {
                log.error("Error al actualizar Libro");
                response.setMetadata("Respuesta NO OK", "-1", "Libro NO Actualizada");
                return new ResponseEntity<LibroResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error al actualizar Libro", e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta NO OK", "-1", "Libro NO Actualizada");
            return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
    }
    @Transactional
    @Override
    public ResponseEntity<LibroResponseRest> eliminar(Long id) {
        log.info("Inicio metodo Eliminar");

        LibroResponseRest response = new LibroResponseRest();
        List<Libro> list = new ArrayList<>();

        try {
            Optional<Libro> libroBuscado = LibroDao.findById(id);
            if (libroBuscado.isPresent()){
                //eliminamos registro
                LibroDao.deleteById(id);
                response.setMetadata("Respuesta OK", "00", "Libro Eliminado");
            }else {
                log.error("Error en eliminar libro");
                response.setMetadata("Respuesta NO OK", "-1","Error al Eliminar Libro");
                return new ResponseEntity<LibroResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error en eliminar Libro");
            e.getStackTrace();
            response.setMetadata("Respuesta NO OK", "-1","Error al eliminar Libro");
            return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
    }
}
