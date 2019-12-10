/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.gestores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        coleccion.add("Ninguna");
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
    
    public static Collection <String> getLicenciasBasicas(){
        Collection <String> coleccion = new ArrayList<String>();
        coleccion.add("A");
        coleccion.add("B");
        coleccion.add("F");
        coleccion.add("G");
        
        return coleccion;
    }
    
    public static Collection <String> getLicencias(){
        Collection <String> coleccion = new ArrayList<String>();
        coleccion.add("A");
        coleccion.add("B");
        coleccion.add("C");
        coleccion.add("D");
        coleccion.add("E");
        coleccion.add("F");
        coleccion.add("G");
        
        return coleccion;
    }
    
    
    
    
    
    
    /**
     * Da un formato especifico al domicilio que le permite trabajarlo y guardarlo con mayor facilidad.
     * @return <i>String</i> - Domicilio en formato predefinido.
     */
    public static String domicilioFormateado(String nombreCalle, int nroAltura, int nroInterno, int nroPiso){
        if(nombreCalle == null)
            return ( "%" + nroAltura + '%' + nroInterno + '%' + nroPiso );
        else
            return ( nombreCalle + '%' + nroAltura + '%' + nroInterno + '%' + nroPiso );
    }
    
    
    /**
     * Da un formato especifico al domicilio que le permite trabajarlo y guardarlo con mayor facilidad.
     * @return <i>String</i> - Domicilio en formato predefinido.
     */
    public static String domicilioFormateado(String nombreCalle, String nroAltura, String nroInterno, String nroPiso){
        StringBuffer domicilio = new StringBuffer();
        
        if(nombreCalle.length()==0){
            domicilio.append(" %");
        }
        else{
            domicilio.append(nombreCalle);
            domicilio.append('%');
        }
        
        if(nroAltura.length()==0){
            domicilio.append(" %");
        }
        else{
            domicilio.append(nroAltura);
            domicilio.append('%');
        }
        
        if(nroInterno.length()==0){
            domicilio.append(" %");
        }
        else{
            domicilio.append(nroInterno);
            domicilio.append('%');
        }
        
        if(nroPiso.length()==0){
            domicilio.append(" %");
        }
        else{
            domicilio.append(nroPiso);
            domicilio.append('%');
        }
        
        return domicilio.toString();
    }
    
    /**
     * Convierte los posibles valores de los ComboBox de 'esDonante' en su equivalente booleano
     */
    public static boolean esDonanteToBoolean(String esDonante){
        if (esDonante.equals("Sí"))
            return true;
        else
            return false;
    }
    
    public static char sexoToChar(String sexo){
        return sexo.charAt(0);
    }
    
    public static char tipoLicenciaToChar(String tipoLicencia){
        return tipoLicencia.charAt(0);
    }
}