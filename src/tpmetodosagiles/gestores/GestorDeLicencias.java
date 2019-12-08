/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.gestores;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import tpmetodosagiles.entidades.Licencia;
import tpmetodosagiles.entidades.Titular;
import static java.time.temporal.ChronoUnit.MONTHS;
import static java.time.temporal.ChronoUnit.YEARS;

public class GestorDeLicencias {
    
    private static GestorDeBaseDeDatos gbd = new GestorDeBaseDeDatos();
    
    public static boolean emitirLicencia(Date fechaEmision, Date fechaVencimiento, char claseLicencia, 
        int numeroDeRenovacion, int numeroDeCopia){
        
        /*Licencia unaLicencia = new Licencia(fechaEmision, fechaVencimiento, claseLicencia, 
                numeroDeRenovacion, numeroDeCopia);
        
        return gbd.guardarLicencia(unaLicencia);*/return true;
    }

    
    public LocalDate calcularVigenciaDeLicencia(LocalDate fechaNacimiento,Integer idTitular, char claseLicencia){

        long edad = YEARS.between(fechaNacimiento,LocalDate.now());
        
        if(edad < 17){
            //Caso menor de 17 años
            return null;
        }
        else if(edad > 16 && edad < 20){
            //caso entre 17 y 21
            if(idTitular==null)
                return null ;
            else
                //Se verifica si el titular ya tiene alguna licencia registrada
                if(!gbd.titularRenovanteDeLicenciaClaseX(idTitular, claseLicencia)) {
                    //Caso primera vez que obtiene licencia
                    if(MONTHS.between(fechaNacimiento,LocalDate.now().minusYears(edad)) >= 10){
                    //se encuentra a menos de 2 meses de cumplir años
                    return fechaNacimiento.plusYears(edad+1+1);
                    }
                    else{
                        return fechaNacimiento.plusYears(edad+1);
                    }
                }
                else{
                    //caso renovación
                    if(MONTHS.between(fechaNacimiento,LocalDate.now().minusYears(edad)) >= 10){
                    //se encuentra a menos de 2 meses de cumplir años
                    return fechaNacimiento.plusYears(edad+1+3);
                    }
                    else{
                        return fechaNacimiento.plusYears(edad+3);
                    }
                }
        }
        else if(edad == 20){
            if(MONTHS.between(fechaNacimiento,LocalDate.now().minusYears(edad)) >= 10){
                //se encuentra a menos de 2 meses de cumplir años
                return fechaNacimiento.plusYears(edad+1+5);
            }
            else{
                //Se verifica si el titular ya tiene alguna licencia registrada
                if(!gbd.titularRenovanteDeLicenciaClaseX(idTitular, claseLicencia)){
                    return fechaNacimiento.plusYears(edad+1);
                }
                else{
                    return fechaNacimiento.plusYears(edad+3);
                }
            }
        }
        else if(edad < 46){
            //caso hasta 46 años
            if(MONTHS.between(fechaNacimiento,LocalDate.now().minusYears(edad)) >= 10){
                //se encuentra a menos de 2 meses de cumplir años
                return fechaNacimiento.plusYears(edad+1+5);
            }
            else{
                return fechaNacimiento.plusYears(edad+5);
            }
        }
        else if(edad == 46){
            if(MONTHS.between(fechaNacimiento,LocalDate.now().minusYears(edad)) >= 10){
                //se encuentra a menos de 2 meses de cumplir años
                return fechaNacimiento.plusYears(edad+1+4);
            }
            else{
                return fechaNacimiento.plusYears(edad+5);
            }
        }
        else if(edad < 60){
            //caso hasta 60 años
            if(MONTHS.between(fechaNacimiento,LocalDate.now().minusYears(edad)) >= 10){
                //se encuentra a menos de 2 meses de cumplir años
                return fechaNacimiento.plusYears(edad+1+4);
            }
            else{
                return fechaNacimiento.plusYears(edad+4);
            }
        }
        else if (edad == 60){
            if(MONTHS.between(fechaNacimiento,LocalDate.now().minusYears(edad)) >= 10){
                //se encuentra a menos de 2 meses de cumplir años
                return fechaNacimiento.plusYears(edad+1+3);
            }
            else{
                return fechaNacimiento.plusYears(edad+4);
            }
        }
        else if(edad < 70){
            //caso hasta 70 años
            if(MONTHS.between(fechaNacimiento,LocalDate.now().minusYears(edad)) >= 10){
                //se encuentra a menos de 2 meses de cumplir años
                return fechaNacimiento.plusYears(edad+1+3);
            }
            else{
                return fechaNacimiento.plusYears(edad+3);
            }
        }
        else if (edad == 70){
            if(MONTHS.between(fechaNacimiento,LocalDate.now().minusYears(edad)) >= 10){
                //se encuentra a menos de 2 meses de cumplir años
                return fechaNacimiento.plusYears(edad+1+1);
            }
            else{
                return fechaNacimiento.plusYears(edad+3);
            }
        }
        else{
            //caso de 71 en adelante
            if(MONTHS.between(fechaNacimiento,LocalDate.now().minusYears(edad)) >= 10){
                //se encuentra a menos de 2 meses de cumplir años
                return fechaNacimiento.plusYears(edad+1+1);
            }
            else{
                return fechaNacimiento.plusYears(edad+1);
            }
        }
    }
  
     
    public Integer calcularCostoDeLicencia(Licencia licencia, Titular titular, Boolean renueva){
        
        int costo = 8;
        char clase = licencia.getClaseLicencia();
        LocalDate fechaNacimiento = titular.getFechaNacimiento();
        
        /*para licencia tipo D y F
        5 años: 60
        4 años: 50
        3 años: 45
        1 año: 40 
        */
        Period periodo = Period.between(fechaNacimiento, LocalDate.now());
        int edad = periodo.getYears();
        
        if((edad>16 && edad<21 && (!renueva))||(edad >= 70)){
            switch(clase){
                case 'A':
                case 'B':
                case 'G':
                    costo += 20;
                    break;
                case 'C':
                    costo += 23;
                    break;
                case 'D':
                case 'F':
                    costo += 40;
                    break;
                case 'E':
                    costo += 29;
                    break;
            }
            return costo;
        }
        if((edad>16 && edad<21)||(edad>=60 && edad<70)){
            switch(clase){
                case 'A': 
                case 'B':
                case 'G':
                    costo += 25;
                    break;
                case 'C':
                    costo += 30;
                    break;
                case 'D':
                case 'F':
                    costo += 45;
                    break;
                case 'E':
                    costo += 39;
                    break;
            }
            return costo;
        }
        if(edad>=21 && edad<46){
            switch(clase){
                case 'A': 
                case 'B':
                case 'G':
                    costo += 40;
                    break;
                case 'C':
                    costo += 47;
                    break;
                case 'D':
                case 'F': 
                    costo += 60;
                    break;
                case 'E':
                    costo += 59;
                    break;
            }
            return costo;
        }
        if(edad>=46 && edad<60){
            switch(clase){
                case 'A': 
                case 'B':
                case 'G':
                    costo += 30;
                    break;
                case 'C':
                    costo += 35;
                    break;
                case 'D':
                case 'F':    
                    costo += 60;
                    break;
                case 'E':
                    costo += 44;
                    break;
            }
            return costo;
        }
        
        return 0;
    }
    
    public List<Licencia> buscarLicenciasExpiradas(String nombre,String apellido,char clase,LocalDate fechaDesde,LocalDate fechaHasta){
        List<Licencia> lista = gbd.getLicenciasExpiradas(nombre,apellido,clase,fechaDesde,fechaHasta);
        
        return lista;
    }


}
