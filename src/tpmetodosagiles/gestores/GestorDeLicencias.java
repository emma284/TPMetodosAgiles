/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.gestores;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
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
	LocalDate fechaVencimiento = fechaNacimiento.plusYears(edad);
	Boolean aMenosDeDosMesesDeCumpleanios = MONTHS.between(fechaNacimiento,LocalDate.now().minusYears(edad)) >= 10;
	if(aMenosDeDosMesesDeCumpleanios){
		//se encuentra a menos de 2 meses de cumplir años
		fechaVencimiento = fechaVencimiento.plusYears(1);
	}
        
        if(edad < 17){
            //Caso menor de 17 años
            return null;
        }
        else if((edad > 16 && edad < 20) || (edad == 20 && !aMenosDeDosMesesDeCumpleanios)){
            //caso entre 17 y 21
            if(idTitular==null)
                return null ;
            else
                //Se verifica si el titular ya tiene alguna licencia registrada
                if(!gbd.titularRenovanteDeLicenciaClaseX(idTitular, claseLicencia)) {
			//Caso primera vez que obtiene licencia
                	return fechaVencimiento.plusYears(1);
                }
                else{
                    //caso renovación
                        return fechaVencimiento.plusYears(3); 
                }
        }
        else if((edad < 46) || (edad == 46 && !aMenosDeDosMesesDeCumpleanios)){
            //caso menor a 47 años
                return fechaVencimiento.plusYears(5);
        }
        else if((edad < 60) || (edad == 60 && !aMenosDeDosMesesDeCumpleanios)){
            //caso menor a 61 años
                return fechaVencimiento.plusYears(4);
        }
        else if((edad < 70) || (edad == 70 && !aMenosDeDosMesesDeCumpleanios)){
            //caso menor a 71 años
                return fechaVencimiento.plusYears(3);
        }
        else{
            //caso de 71 en adelante
                return fechaVencimiento.plusYears(1);
        }
    }
     
    public Double calcularCostoDeLicencia(Licencia licencia){
        
        Double costo = 0.0;
        long aniosVigencia = 0;
        char clase = licencia.getClaseLicencia();
        LocalDate fechaNacimiento = licencia.getTitular().getFechaNacimiento();
        long edad = YEARS.between(fechaNacimiento,licencia.getFechaEmision());
        
        if(MONTHS.between(fechaNacimiento,licencia.getFechaEmision().minusYears(edad)) >= 10){
            aniosVigencia = YEARS.between(licencia.getFechaEmision(), licencia.getFechaVencimiento());
        }else{
            aniosVigencia = YEARS.between(licencia.getFechaEmision(), licencia.getFechaVencimiento())+1;
        }
        costo = gbd.getCostoLicencia(licencia.getClaseLicencia(), (int)aniosVigencia);
      return costo;
    }
    
    public List<Licencia> buscarLicenciasExpiradas(String nombre,String apellido,char clase,LocalDate fechaDesde,LocalDate fechaHasta){
        List<Licencia> lista = gbd.getLicenciasExpiradas(nombre,apellido,clase,fechaDesde,fechaHasta);
        
        return lista;
    }
    
    public List<List<Double>> obtenerCostos(){
        List<List<Double>> lista = gbd.obtenerCostos();
//        double lista = gbd.obtenerCostos();
        
        return lista;
    }

    public Double obtenerCostoAdministrativo(){
        Double retorno = gbd.obtenerCostoAdministrativo();
        
        return retorno;
    }
    
    public void guardarValoresLicencia(ArrayList<List<Double>> costos){
        
        String tipos = "ABCDEFG";
        int i = 0;
        for(List<Double> costosLicencia : costos){
            gbd.guardarValoresLicencia(costosLicencia,tipos.charAt(i));
            i++;
        }
        
    }
    
    public void guardarCostoAdministrativo(Double costoAdm){
        
        gbd.guardarCostoAdministrativo(costoAdm);
        
    }
}
