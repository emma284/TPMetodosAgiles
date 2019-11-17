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
import tpmetodosagiles.entidades.Titular;


public class GestorDeLicencias {
    
    private static GestorDeBaseDeDatos gbd = new GestorDeBaseDeDatos();
    
    public static boolean emitirLicencia(Date fechaEmision, Date fechaVencimiento, char claseLicencia, 
        int numeroDeRenovacion, int numeroDeCopia){
        
        /*Licencia unaLicencia = new Licencia(fechaEmision, fechaVencimiento, claseLicencia, 
                numeroDeRenovacion, numeroDeCopia);
        
        return gbd.guardarLicencia(unaLicencia);*/return true;
    }

    
    public LocalDate calcularVigenciaDeLicencia(LocalDate fechaNacimiento,Integer idTitular, char claseLicencia){
        
        //LocalDate dateNacimiento = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period periodo = Period.between(fechaNacimiento, LocalDate.now());
        int edad = periodo.getYears();
        
        if(edad < 17){
            return null;
        }
        else if(edad > 16 && edad < 21){
            //caso entre 17 y 21
            Licencia resultado = new Licencia();
            if(idTitular==null)
                resultado = null;
            else
                resultado = gbd.getLicenciaPorIDTitularYClase(idTitular, claseLicencia);
            if(resultado == null){
                //Caso primera vez que obtiene licencia
                if(Period.between(fechaNacimiento, LocalDate.now().minusYears(edad)).getMonths() > 10){
                //se encuentra a menos de 2 meses de cumplir años
                return fechaNacimiento.plusYears(edad+1+1);
                }
                else{
                    return fechaNacimiento.plusYears(edad+1);
                }
            }
            else{
                //caso renovación
                if(Period.between(fechaNacimiento, LocalDate.now().minusYears(edad)).getMonths() > 10){
                //se encuentra a menos de 2 meses de cumplir años
                return fechaNacimiento.plusYears(edad+1+3);
                }
                else{
                    return fechaNacimiento.plusYears(edad+3);
                }
            }
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
    }
    
    public boolean validarDatosCreacionDeLicencia(Date fechaEmision, Date fechaVencimiento, char claseLicencia, 
        int numeroDeRenovacion, int numeroDeCopia){
        boolean datosCorrectos = true;
        
       //TODO validar si es posible emitir licencia
        
        return datosCorrectos;
    }
}
