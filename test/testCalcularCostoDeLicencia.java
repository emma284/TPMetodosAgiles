import java.time.LocalDate;
import java.time.Month;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tpmetodosagiles.gestores.GestorDeLicencias;
import tpmetodosagiles.entidades.Licencia;
import tpmetodosagiles.entidades.Titular;

public class testCalcularCostoDeLicencia {
    
    public testCalcularCostoDeLicencia() {
    }
    
    char claseLicencia='E';
    
    @Test
    public void test1() {
        Licencia licencia = new Licencia();
        licencia.setClaseLicencia(claseLicencia);
        licencia.setFechaEmision(LocalDate.of(2019, 12, 7));
        licencia.setFechaVencimiento(LocalDate.of(2020, 8, 3));
        Titular titular = new Titular();
        titular.setFechaNacimiento(LocalDate.of(2001, 8, 3));
        licencia.setTitular(titular);
        double resultado[];
        resultado = GestorDeLicencias.getArrayCostoLicencia(licencia);
        
        assertEquals(resultado[0], 29.,.001);
        assertEquals(resultado[1], 8.,.001);
        assertEquals(resultado[2], 37.,.001);
    }
    
    @Test
    public void test2() {
        Licencia licencia = new Licencia();
        licencia.setClaseLicencia(claseLicencia);
        licencia.setFechaEmision(LocalDate.of(2019, 5, 21));
        licencia.setFechaVencimiento(LocalDate.of(2022, 5, 20));
        Titular titular = new Titular();
        titular.setFechaNacimiento(LocalDate.of(1999, 5, 20));
        licencia.setTitular(titular);
        double resultado[];
        resultado = GestorDeLicencias.getArrayCostoLicencia(licencia);
        
        assertEquals(resultado[0], 39.,.001);
        assertEquals(resultado[1], 8.,.001);
        assertEquals(resultado[2], 47.,.001);
    }
    
    @Test
    public void test3() {
        Licencia licencia = new Licencia();
        licencia.setClaseLicencia(claseLicencia);
        licencia.setFechaEmision(LocalDate.of(2014, 5, 21));
        licencia.setFechaVencimiento(LocalDate.of(2019, 7, 20));
        Titular titular = new Titular();
        titular.setFechaNacimiento(LocalDate.of(1993, 7, 20));
        licencia.setTitular(titular);
        double resultado[];
        resultado = GestorDeLicencias.getArrayCostoLicencia(licencia);
        
        assertEquals(resultado[0], 59.,.001);
        assertEquals(resultado[1], 8.,.001);
        assertEquals(resultado[2], 67.,.001);
    }
    
    @Test
    public void test4() {
        Licencia licencia = new Licencia();
        licencia.setClaseLicencia(claseLicencia);
        licencia.setFechaEmision(LocalDate.of(2000, 6, 25));
        licencia.setFechaVencimiento(LocalDate.of(2005, 5, 26));
        Titular titular = new Titular();
        titular.setFechaNacimiento(LocalDate.of(1971, 5, 26));
        licencia.setTitular(titular);
        double resultado[];
        resultado = GestorDeLicencias.getArrayCostoLicencia(licencia);
        
        assertEquals(resultado[0], 59.,.001);
        assertEquals(resultado[1], 8.,.001);
        assertEquals(resultado[2], 67.,.001);
    }
    
    public void test5() {
        Licencia licencia = new Licencia();
        licencia.setClaseLicencia(claseLicencia);
        licencia.setFechaEmision(LocalDate.of(2047, 3, 27));
        licencia.setFechaVencimiento(LocalDate.of(2051, 5, 26));
        Titular titular = new Titular();
        titular.setFechaNacimiento(LocalDate.of(2000, 5, 26));
        licencia.setTitular(titular);
        double resultado[];
        resultado = GestorDeLicencias.getArrayCostoLicencia(licencia);
        
        assertEquals(resultado[0], 44.,.001);
        assertEquals(resultado[1], 8.,.001);
        assertEquals(resultado[2], 52.,.001);
    }
    
    public void test6() {
        Licencia licencia = new Licencia();
        licencia.setClaseLicencia(claseLicencia);
        licencia.setFechaEmision(LocalDate.of(2051, 6, 30));
        licencia.setFechaVencimiento(LocalDate.of(2055, 3, 19));
        Titular titular = new Titular();
        titular.setFechaNacimiento(LocalDate.of(2001, 3, 19));
        licencia.setTitular(titular);
        double resultado[];
        resultado = GestorDeLicencias.getArrayCostoLicencia(licencia);
        
        assertEquals(resultado[0], 44.,.001);
        assertEquals(resultado[1], 8.,.001);
        assertEquals(resultado[2], 52.,.001);
    }
    
    public void test7() {
        Licencia licencia = new Licencia();
        licencia.setClaseLicencia(claseLicencia);
        licencia.setFechaEmision(LocalDate.of(2000, 7, 10));
        licencia.setFechaVencimiento(LocalDate.of(2003, 7, 12));
        Titular titular = new Titular();
        titular.setFechaNacimiento(LocalDate.of(1939, 7, 12));
        licencia.setTitular(titular);
        double resultado[];
        resultado = GestorDeLicencias.getArrayCostoLicencia(licencia);
        
        assertEquals(resultado[0], 39.,.001);
        assertEquals(resultado[1], 8.,.001);
        assertEquals(resultado[2], 47.,.001);
    }
    
    public void test8() {
        Licencia licencia = new Licencia();
        licencia.setClaseLicencia(claseLicencia);
        licencia.setFechaEmision(LocalDate.of(2005, 7, 20));
        licencia.setFechaVencimiento(LocalDate.of(2008, 7, 12));
        Titular titular = new Titular();
        titular.setFechaNacimiento(LocalDate.of(1940, 7, 12));
        licencia.setTitular(titular);
        double resultado[];
        resultado = GestorDeLicencias.getArrayCostoLicencia(licencia);
        
        assertEquals(resultado[0], 39.,.001);
        assertEquals(resultado[1], 8.,.001);
        assertEquals(resultado[2], 47.,.001);
    }
    
    public void test9() {
        Licencia licencia = new Licencia();
        licencia.setClaseLicencia(claseLicencia);
        licencia.setFechaEmision(LocalDate.of(2000, 7, 10));
        licencia.setFechaVencimiento(LocalDate.of(2001, 7, 12));
        Titular titular = new Titular();
        titular.setFechaNacimiento(LocalDate.of(1929, 7, 12));
        licencia.setTitular(titular);
        double resultado[];
        resultado = GestorDeLicencias.getArrayCostoLicencia(licencia);
        
        assertEquals(resultado[0], 29.,.001);
        assertEquals(resultado[1], 8.,.001);
        assertEquals(resultado[2], 37.,.001);
    }
    
    public void test10() {
        Licencia licencia = new Licencia();
        licencia.setClaseLicencia(claseLicencia);
        licencia.setFechaEmision(LocalDate.of(2075, 8, 30));
        licencia.setFechaVencimiento(LocalDate.of(2001, 7, 17));
        Titular titular = new Titular();
        titular.setFechaNacimiento(LocalDate.of(2000, 8, 30));
        licencia.setTitular(titular);
        double resultado[];
        resultado = GestorDeLicencias.getArrayCostoLicencia(licencia);
        
        assertEquals(resultado[0], 29.,.001);
        assertEquals(resultado[1], 8.,.001);
        assertEquals(resultado[2], 37.,.001);
    }
}
