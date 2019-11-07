/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.gestores;

import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 * @author Emma
 */

public class GestorDeLicenciasTest {
    private Date fechaEmision,fechaVencimiento;
    private char claseLicencia;
    private int numeroDeRenovacion,numeroDeCopia;
    private boolean exp;
    
    public GestorDeLicenciasTest() {
    }
    
    public GestorDeLicenciasTest(Date fechaEmision, Date fechaVencimiento, char claseLicencia, int numeroDeRenovacion, int numeroDeCopia, boolean exp) {
        this.fechaEmision = fechaEmision;
        this.fechaVencimiento = fechaVencimiento;
        this.claseLicencia = claseLicencia;
        this.numeroDeRenovacion = numeroDeRenovacion;
        this.numeroDeCopia = numeroDeCopia;
        this.exp = exp;
    }
    
    @ParameterizedTest
    @CsvSource({"2009-04-10,2025-04-15,A,2,2",
                "2009-09-25,2020-05-06,B,3,3",
                "2019-12-03,2023-06-25,C,4,4",
                "1994-10-10,2024-03-02,D,5,5",
                "1935-04-28,1955-04-21,E,6,6",
                "2000-05-13,2005-06-17,F,7,7",
                "2005-01-01,2008-04-06,G,8,8"
                })
    public void testEmitirLicencia(Date fechaEmision, Date fechaVencimiento, char claseLicencia, int numeroDeRenovacion, int numeroDeCopia, boolean exp) {
        boolean result = GestorDeLicencias.emitirLicencia(fechaEmision, fechaVencimiento, claseLicencia, numeroDeRenovacion, numeroDeCopia);
        assertEquals(exp, result);
        if(!result==exp)
            fail("The test case is a prototype.");
    }
    
}
