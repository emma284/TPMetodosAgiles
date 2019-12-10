import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tpmetodosagiles.gestores.GestorDeLicencias;

public class testCalcularVigenciaDeLicencia {
    
    public testCalcularVigenciaDeLicencia() {
    }
    
    @Test
    public void test1() {
        GestorDeLicencias gestorlicencia = new GestorDeLicencias();
        LocalDate fecha = LocalDate.of(2002,11,27);
        LocalDate resultado = gestorlicencia.calcularVigenciaDeLicencia(fecha, 3, 'B');
        LocalDate fechaEsperada = LocalDate.of(2020,11,27);
        
        assertEquals(fechaEsperada.toString(), resultado.toString());
    }
    
    @Test
    public void test2() {
        GestorDeLicencias gestorlicencia = new GestorDeLicencias();
        LocalDate fecha = LocalDate.of(2002,02,7);
        LocalDate resultado = gestorlicencia.calcularVigenciaDeLicencia(fecha, 4, 'A');
        LocalDate fechaEsperada = LocalDate.of(2021,02,7);
        
        assertEquals(fechaEsperada.toString(), resultado.toString());
    }
    
    @Test
    public void test3() {
        GestorDeLicencias gestorlicencia = new GestorDeLicencias();
        LocalDate fecha = LocalDate.of(2003,01,07);
        LocalDate resultado = gestorlicencia.calcularVigenciaDeLicencia(fecha, 5, 'A');
        
        assertNull(resultado);
    }
    
    @Test
    public void test4() {
        GestorDeLicencias gestorlicencia = new GestorDeLicencias();
        LocalDate fecha = LocalDate.of(2003,07,07);
        LocalDate resultado = gestorlicencia.calcularVigenciaDeLicencia(fecha, 6, 'B');
        
        assertNull(resultado);
    }
    
    @Test
    public void test5() {
        GestorDeLicencias gestorlicencia = new GestorDeLicencias();
        LocalDate fecha = LocalDate.of(1999,02,7);
        LocalDate resultado = gestorlicencia.calcularVigenciaDeLicencia(fecha, 7, 'A');
        LocalDate fechaEsperada = LocalDate.of(2025,02,7);
        
        assertEquals(fechaEsperada.toString(), resultado.toString());
    }
    
    @Test
    public void test6() {
        GestorDeLicencias gestorlicencia = new GestorDeLicencias();
        LocalDate fecha = LocalDate.of(1999,05,07);
        LocalDate resultado = gestorlicencia.calcularVigenciaDeLicencia(fecha, 8, 'A');
        LocalDate fechaEsperada = LocalDate.of(2020,05,7);
        
        assertEquals(fechaEsperada.toString(), resultado.toString());
    }
    
    @Test
    public void test7() {
        GestorDeLicencias gestorlicencia = new GestorDeLicencias();
        LocalDate fecha = LocalDate.of(1998,8,7);
        LocalDate resultado = gestorlicencia.calcularVigenciaDeLicencia(fecha, 9, 'G');
        LocalDate fechaEsperada = LocalDate.of(2024,8,7);
        
        assertEquals(fechaEsperada.toString(), resultado.toString());
    }
    
    @Test
    public void test8() {
        GestorDeLicencias gestorlicencia = new GestorDeLicencias();
        LocalDate fecha = LocalDate.of(1974,07,07);
        LocalDate resultado = gestorlicencia.calcularVigenciaDeLicencia(fecha, 10, 'C');
        LocalDate fechaEsperada = LocalDate.of(2024,7,7);
        
        assertEquals(fechaEsperada.toString(), resultado.toString());
    }
    
    @Test
    public void test9() {
        GestorDeLicencias gestorlicencia = new GestorDeLicencias();
        LocalDate fecha = LocalDate.of(1973,01,07);
        LocalDate resultado = gestorlicencia.calcularVigenciaDeLicencia(fecha, 11, 'C');
        LocalDate fechaEsperada = LocalDate.of(2024,1,7);
        
        assertEquals(fechaEsperada.toString(), resultado.toString());
    }
    
    @Test
    public void test10() {
        GestorDeLicencias gestorlicencia = new GestorDeLicencias();
        LocalDate fecha = LocalDate.of(1949,01,07);
        LocalDate resultado = gestorlicencia.calcularVigenciaDeLicencia(fecha, 12, 'G');
        LocalDate fechaEsperada = LocalDate.of(2021,1,7);
        
        assertEquals(fechaEsperada.toString(), resultado.toString());
    }
    
    @Test
    public void test11() {
        GestorDeLicencias gestorlicencia = new GestorDeLicencias();
        LocalDate fecha = LocalDate.of(1949,8,7);
        LocalDate resultado = gestorlicencia.calcularVigenciaDeLicencia(fecha, 13, 'B');
        LocalDate fechaEsperada = LocalDate.of(2022,8,7);
        
        assertEquals(fechaEsperada.toString(), resultado.toString());
    }
    
    @Test
    public void test12() {
        GestorDeLicencias gestorlicencia = new GestorDeLicencias();
        LocalDate fecha = LocalDate.of(2000,3,7);
        LocalDate resultado = gestorlicencia.calcularVigenciaDeLicencia(fecha, 14, 'B');
        LocalDate fechaEsperada = LocalDate.of(2020,3,7);
        
        assertEquals(fechaEsperada.toString(), resultado.toString());
    }
    
    @Test
    public void test13() {
        GestorDeLicencias gestorlicencia = new GestorDeLicencias();
        LocalDate fecha = LocalDate.of(2000,1,7);
        LocalDate resultado = gestorlicencia.calcularVigenciaDeLicencia(fecha, 15, 'B');
        LocalDate fechaEsperada = LocalDate.of(2021,1,7);
        
        assertEquals(fechaEsperada.toString(), resultado.toString());
    }
    
    @Test
    public void test14() {
        GestorDeLicencias gestorlicencia = new GestorDeLicencias();
        LocalDate fecha = LocalDate.of(2001,10,7);
        LocalDate resultado = gestorlicencia.calcularVigenciaDeLicencia(fecha, 16, 'B');
        LocalDate fechaEsperada = LocalDate.of(2022,10,7);
        
        assertEquals(fechaEsperada.toString(), resultado.toString());
    }
    
    @Test
    public void test15() {
        GestorDeLicencias gestorlicencia = new GestorDeLicencias();
        LocalDate fecha = LocalDate.of(1999,3,7);
        LocalDate resultado = gestorlicencia.calcularVigenciaDeLicencia(fecha, 17, 'A');
        LocalDate fechaEsperada = LocalDate.of(2022,3,7);
        
        assertEquals(fechaEsperada.toString(), resultado.toString());
    }
}
