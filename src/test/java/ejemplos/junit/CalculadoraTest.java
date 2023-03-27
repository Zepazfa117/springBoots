package ejemplos.junit;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalculadoraTest {

     Calculadora calc;
    @AfterAll
    public  static  void ultimo (){
        System.out.println("ultimo");
    }
    @BeforeAll
    public  static  void primero (){
        System.out.println("primero");
    }

    @BeforeEach
    public void instanciaObjeto(){
        calc = new Calculadora();
        System.out.println("@BeforeEach");
    }

    @AfterEach
    public void despuesTest(){
        calc = new Calculadora();
        System.out.println("@AfterEach");
    }

    @Test
    @DisplayName("prueba que ocupa asserte Equals")
    //@Disabled
    public void calculadoraAssertEqualsTest(){

        assertEquals(2,calc.sumar(1,1));
        assertEquals(4, calc.multiplicar(2,2));
        assertEquals(2, calc.dividir(12,6));
        assertEquals(5,calc.restar(9,4));

        System.out.println("prueba unitaria equals");
    }

    @Test
    public void calculadoraTrueFalse(){
        int y;
        Calculadora calc = new Calculadora();
        assertTrue(calc.sumar(1,1)== 2);
        System.out.println("prueba unitaria true or false");

    }
}
