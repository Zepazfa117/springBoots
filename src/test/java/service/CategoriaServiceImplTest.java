package service;

import com.example.books.backend.dao.ICategoriaDao;
import com.example.books.backend.model.Categoria;
import com.example.books.backend.response.CategoriaResponseRest;
import com.example.books.backend.services.CategoriaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CategoriaServiceImplTest {

    @InjectMocks
    CategoriaServiceImpl service;

    @Mock
    ICategoriaDao categoriaDao;
    List<Categoria> list = new ArrayList<Categoria>();

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        this.cargarCategorias();;
    }

    @Test
    public void buscarCategoriasTest (){
        when(categoriaDao.findAll()).thenReturn(list);

        ResponseEntity<CategoriaResponseRest> response = service.buscarCategorias();

        assertEquals(4,response.getBody().getCategoriaResponse().getCategoria().size());

        verify(categoriaDao, times(1)).findAll();
    }

    public void  cargarCategorias(){
        Categoria catuno = new Categoria(Long.valueOf(1), "Abarrotes", "Distintos abarrotes");
        Categoria catdos = new Categoria(Long.valueOf(1), "Lacteos", "variedad de lacteos");
        Categoria cattres = new Categoria(Long.valueOf(1), "Bebidas", "Bebidas sin azucar");
        Categoria catcuatro = new Categoria(Long.valueOf(1), "Carnes blancas", "Distintas carnes");

        list.add(catuno);
        list.add(catdos);
        list.add(cattres);
        list.add(catcuatro);

    }
}
