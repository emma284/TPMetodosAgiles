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
import java.util.Locale;
import tpmetodosagiles.entidades.Licencia;

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
            if(idTitular==null)
                return null ;
            else
            if(!gbd.titularRenovanteDeLicenciaClaseX(idTitular, claseLicencia)) {
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
    
    public static Date calcularVigenciaDeLicenciaNueva(Date fechaNacimiento){
        
//        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaNacimiento);
        if((calendar.get(Calendar.MONTH) == 2) && (calendar.get(Calendar.DAY_OF_MONTH)== 29)){
            fechaNacimiento = addDay(fechaNacimiento, 1);
        }
        
        Date hoy = new Date();
        
        int edad = getDiffYears(fechaNacimiento, hoy);
        int vigencia = 0;
        
        if(edad<17){
            return null;
        }
        if(edad>17 && edad<21){
            vigencia = 1;
        }
        if(edad>=21 && edad<46){
            vigencia = 5;
        }
        if(edad>=46 && edad<60){
            vigencia = 4;
        }
        if(edad>=60 && edad<70){
            vigencia = 4;
        }
        if(edad>=70 && edad<100){
            vigencia = 1;
        }
        if(edad>=100){
            return null;
        }
        
        int anioFinalizaVigencia = edad + vigencia;
        Date fechaVencimiento = addYear(fechaNacimiento, anioFinalizaVigencia);
        
        return fechaVencimiento;
    }
    
    public static Date calcularVigenciaDeLicenciaExistente(Date fechaNacimiento, Date fechaVencimientoAnterior){
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaNacimiento);
        if((calendar.get(Calendar.MONTH) == 2) && (calendar.get(Calendar.DAY_OF_MONTH)== 29)){
            fechaNacimiento = addDay(fechaNacimiento, 1);
        }
        
        Boolean justoAntesDeVencer = false;
        Boolean vigenciaYaAsignada = false;
        Date hoy = new Date();
        int edad = getDiffYears(fechaNacimiento, hoy);
        int vigencia = 0;
        
        if((fechaVencimientoAnterior.getTime() - hoy.getTime())>0 && daysBetween(hoy, fechaVencimientoAnterior)<=60){
            //esta renovando su licencia antes de su vencimiento; esta se vence en 60 días o menos
            justoAntesDeVencer = true;
        }
        if(edad<17){
            return null;
        }
        if(edad == 20 && justoAntesDeVencer){
            vigencia = 5;
            vigenciaYaAsignada = true;
        }
        if(edad == 45 && justoAntesDeVencer){
            vigencia = 4;
            vigenciaYaAsignada = true;
        }
        if(edad == 59 && justoAntesDeVencer){
            vigencia = 4;
            vigenciaYaAsignada = true;
        }
        if(edad == 69 && justoAntesDeVencer){
            vigencia = 1;
            vigenciaYaAsignada = true;
        }
        if((!vigenciaYaAsignada)&&(edad>17 && edad<21)){
            vigencia = 3;
        }
        if((!vigenciaYaAsignada)&&(edad>=21 && edad<46)){
            vigencia = 5;
        }
        if((!vigenciaYaAsignada)&&(edad>=46 && edad<60)){
            vigencia = 4;
        }
        if((!vigenciaYaAsignada)&&(edad>=60 && edad<70)){
            vigencia = 4;
        }
        if((!vigenciaYaAsignada)&&(edad>=70 && edad<100)){
            vigencia = 1;
        }
        if(edad>=100){
            return null;
        }
        
        int anioFinalizaVigencia = edad + vigencia;

        Date fechaVencimiento = addYear(fechaNacimiento, anioFinalizaVigencia);
        
        return fechaVencimiento;
    }
    
    public static int getDiffYears(Date first, Date last){
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
        if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH) || (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
            diff--;
        }
        return diff;
    }
    
    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }
    
    public static Date addYear(Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, i);
        return cal.getTime();
    }
        public static Date addDay(Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, i);
        return cal.getTime();
    }
    
    public static int daysBetween(Date d1, Date d2){
             return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }
}
