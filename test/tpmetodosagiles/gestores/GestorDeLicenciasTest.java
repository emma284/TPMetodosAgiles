/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.gestores;

import java.util.Date;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Emma
 */
public class GestorDeLicenciasTest {
    
    public GestorDeLicenciasTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of emitirLicencia method, of class GestorDeLicencias.
     */
    @Test
    public void testEmitirLicencia() {
        System.out.println("emitirLicencia");
        Date fechaEmision = null;
        Date fechaVencimiento = null;
        char claseLicencia = ' ';
        int numeroDeRenovacion = 0;
        int numeroDeCopia = 0;
        boolean expResult = false;
        boolean result = GestorDeLicencias.emitirLicencia(fechaEmision, fechaVencimiento, claseLicencia, numeroDeRenovacion, numeroDeCopia);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if(!result==expResult)
            fail("The test case is a prototype.");
    }
    
}
