/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.gestores;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author usuario
 */
public class GestorDeDatosDeInterface {
    
    public static Collection <String> getTipoDeDocumento(){
        Collection <String> coleccion = new ArrayList<String>();
        coleccion.add("DNI");
        coleccion.add("DNI Extranjero");
        coleccion.add("Libreta Cívica");
        coleccion.add("Libreta de Enrrolamiento");
        
        return coleccion;
    }
    
    public static Collection <String> getCalles(){
        Collection <String> coleccion = new ArrayList<String>();
        coleccion.add("25 de Mayo");
        coleccion.add("9 de Julio");
        coleccion.add("Manuel Belgrano");
        coleccion.add("Primera Junta");
        coleccion.add("San Martín");
        coleccion.add("Sarmiento");
        
        return coleccion;
    }
    
    public static Collection <String> getGrupoSanguinio(){
        Collection <String> coleccion = new ArrayList<String>();
        coleccion.add("A+");
        coleccion.add("AB+");
        coleccion.add("B+");
        coleccion.add("O+");
        coleccion.add("A-");
        coleccion.add("AB-");
        coleccion.add("B-");
        coleccion.add("O-");
        
        return coleccion;
    }
    
    public static Collection <String> getEsDonante(){
        Collection <String> coleccion = new ArrayList<String>();
        coleccion.add("Sí");
        coleccion.add("No");
        
        return coleccion;
    }
    
    public static Collection <String> getSexos(){
        Collection <String> coleccion = new ArrayList<String>();
        coleccion.add("Masculino");
        coleccion.add("Femenino");
        
        return coleccion;
    }
    
    public static Collection <String> getObservaciones(){
        Collection <String> coleccion = new ArrayList<String>();
        coleccion.add("Conduce con anteojos");
        coleccion.add("Conducción diurna");
        coleccion.add("Vehículos de transmisión automática");
        coleccion.add("Conducción no permitida en ruta/autopista");
        coleccion.add("Conducción sin remolque");
        coleccion.add("Prótesis auditiva o de ayuda a la comunicación");
        coleccion.add("Prótesis de los miembros superiores");
        coleccion.add("Prótesis de los miembros inferiores");
        
        return coleccion;
    }
    
    
}
