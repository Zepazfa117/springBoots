package service;

import com.example.books.backend.dao.ICategoriaDao;
import com.example.books.backend.model.Categoria;
import com.example.books.backend.services.CategoriaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

public class CategoriaServiceImplTest {

    @InjectMocks
    CategoriaServiceImpl service;

    @Mock
    ICategoriaDao categoriaDao;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void buscarCategoriasTest (){
        when(categoriaDao.findAll()).thenReturn(null);
    }

    public void  cargarCategoria(){
        Categoria catuno = new Categoria(Long.valueOf(1), "Abarrotes", "Distintos abarrotes");
    }
}
