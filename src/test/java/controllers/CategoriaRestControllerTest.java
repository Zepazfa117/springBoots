package controllers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import  static org.mockito.ArgumentMatchers.any;
import com.example.books.backend.controllers.CategoriaRestController;
import com.example.books.backend.model.Categoria;
import com.example.books.backend.response.CategoriaResponseRest;
import com.example.books.backend.services.ICategoriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.mockito.Mockito.when;


public class CategoriaRestControllerTest {

    @InjectMocks
    CategoriaRestController categoriaController;
    @Mock
    private ICategoriaService service;

    @BeforeEach
    public  void init (){
        MockitoAnnotations.openMocks(this);
    }
     @Test
    public void CrearTest(){
         MockHttpServletRequest request = new MockHttpServletRequest();
         RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

         Categoria categoria = new Categoria(Long.valueOf(1),"Clasicos","Libros clasicos de Loiteratura moderna");

         when(service.crear(any(Categoria.class))).thenReturn( new ResponseEntity <CategoriaResponseRest>(HttpStatus.OK));

         ResponseEntity<CategoriaResponseRest> respuesta = categoriaController.crear(categoria);

         assertThat(respuesta.getStatusCode()).isEqualTo(200);
     }

}
