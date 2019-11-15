/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.gestores;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.Period;
import java.util.Date;
import tpmetodosagiles.entidades.Licencia;


public class GestorDeLicencias {
    
    private static GestorDeBaseDeDatos gbd = new GestorDeBaseDeDatos();
    
    public static boolean emitirLicencia(Date fechaEmision, Date fechaVencimiento, char claseLicencia, 
        int numeroDeRenovacion, int numeroDeCopia){
        
        /*Licencia unaLicencia = new Licencia(fechaEmision, fechaVencimiento, claseLicencia, 
                numeroDeRenovacion, numeroDeCopia);
        
        return gbd.guardarLicencia(unaLicencia);*/return true;
    }
    
    //TODO calcular vigencia de licencia
    public LocalDate calcularVigenciaDeLicencia(LocalDate fechaNacimiento, String tipoDeDocumento, int numeroDocumento){
        
        //LocalDate dateNacimiento = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period periodo = Period.between(fechaNacimiento, LocalDate.now());
        int edad = periodo.getYears();
        if(edad > 16 && edad < 21){
            //caso entre 17 y 21
            //TODO Busacar licencia del mismo titular para ver si son 1 o 3 años
        }
        else if(edad < 47){
            //caso hasta 46 años
            if(Period.between(fechaNacimiento, LocalDate.now().minusYears(edad)).getMonths() > 10){
                //se encuentra a menos de 2 meses de cumplir años
                return fechaNacimiento.plusYears(edad+1+5);
            }
            else{
                return fechaNacimiento.plusYears(edad+5);
            }
        }
        else if(edad < 61){
            //caso hasta 60 años
            if(Period.between(fechaNacimiento, LocalDate.now().minusYears(edad)).getMonths() > 10){
                //se encuentra a menos de 2 meses de cumplir años
                return fechaNacimiento.plusYears(edad+1+4);
            }
            else{
                return fechaNacimiento.plusYears(edad+4);
            }
        }
        else if(edad < 69){
            //caso hasta 68 años
            if(Period.between(fechaNacimiento, LocalDate.now().minusYears(edad)).getMonths() > 10){
                //se encuentra a menos de 2 meses de cumplir años
                return fechaNacimiento.plusYears(edad+1+3);
            }
            else{
                return fechaNacimiento.plusYears(edad+3);
            }
        }
        else{
            //caso de 69 en adelante
            if(Period.between(fechaNacimiento, LocalDate.now().minusYears(edad)).getMonths() > 10){
                //se encuentra a menos de 2 meses de cumplir años
                return fechaNacimiento.plusYears(edad+1+1);
            }
            else{
                return fechaNacimiento.plusYears(edad+1);
            }
        }
        return null;
    }
    
    public boolean validarDatosCreacionDeLicencia(Date fechaEmision, Date fechaVencimiento, char claseLicencia, 
        int numeroDeRenovacion, int numeroDeCopia){
        boolean datosCorrectos = true;
        
       //TODO validar si es posible emitir licencia
        
        return datosCorrectos;
    }
}
