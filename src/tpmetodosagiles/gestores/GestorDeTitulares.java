/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.gestores;

import java.util.Date;
import tpmetodosagiles.entidades.Titular;

public class GestorDeTitulares {
    
    private static GestorDeBaseDeDatos gbd = new GestorDeBaseDeDatos();
    
    public static boolean emitirTitular(String tipoDeDocumento, int numeroDocumento, 
            String apellido, String nombre, Date fechaNacimiento, String domicilio, String grupoSanguinio, 
            Boolean esDonante, char sexo, Date fechaEntradaSistema, Date fechaEmisionLicenciaTipoB){
        
        Titular unTitular = new Titular(tipoDeDocumento, numeroDocumento, apellido, nombre, 
                fechaNacimiento, domicilio,  grupoSanguinio, esDonante, sexo, fechaEntradaSistema, 
                fechaEmisionLicenciaTipoB);
        
        return gbd.guardarTitular(unTitular);
        
    }
}
