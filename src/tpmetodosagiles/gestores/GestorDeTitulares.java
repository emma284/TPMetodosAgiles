/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.gestores;

import tpmetodosagiles.entidades.Titular;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import tpmetodosagiles.entidades.Licencia;

public class GestorDeTitulares {
            
    private GestorDeBaseDeDatos gbd = new GestorDeBaseDeDatos();
    
    public boolean emitirTitularYLicencia(String tipoDeDocumento, int numeroDocumento, 
            String apellido, String nombre, LocalDate fechaNacimiento, String domicilio, String grupoSanguinio, 
            Boolean esDonante, char sexo, char claseDeLicencia, String observaciones){
        
        GestorDeBaseDeDatos gbd = new GestorDeBaseDeDatos();
        //Verifica si se trata de una licencia de clase B para indicarlo en el atributo de Titular
        LocalDate emisionLicenciaClaseB;
        if(claseDeLicencia == 'B')
            emisionLicenciaClaseB = LocalDate.now();
        else
            emisionLicenciaClaseB = null;
        
        //Genera el Titular con sus datos
        Titular unTitular = new Titular(tipoDeDocumento, numeroDocumento, apellido, nombre, 
                fechaNacimiento, domicilio,  grupoSanguinio, esDonante, sexo, LocalDate.now(), 
                emisionLicenciaClaseB, observaciones);
        unTitular.setUsuarioResponsable(GestorDeConfiguracion.getUsuarioActual());
        System.out.println(unTitular.getUsuarioResponsable().getApellido());
        //Verifica si el usuario actual ya está en el sistema
        if(gbd.getTitularPorDNI(tipoDeDocumento, ""+numeroDocumento) != null)
            return false;
        
        //Calcula la fecha de vencimiento de la licencia que se está emitiendo
        GestorDeLicencias gdl = new GestorDeLicencias();
        LocalDate fechaVencimientoLicencia = 
                gdl.calcularVigenciaDeLicencia(unTitular.getFechaNacimiento(), null, claseDeLicencia);
        
        if(fechaVencimientoLicencia == null){
            //Ocurre si el titular no tiene más de 17 años
            Alert mensajeErrores = new Alert(Alert.AlertType.INFORMATION);
            mensajeErrores.setTitle("Menor de edad");
            mensajeErrores.setHeaderText("Imposible generar licencia");
            mensajeErrores.setContentText("El solicitante es menor de 17 años. No es posible generar la licencia requerida.");
            
            mensajeErrores.initModality(Modality.APPLICATION_MODAL);
            mensajeErrores.show();
            return false;
        }
        
        Licencia unaLicencia = new Licencia(LocalDate.now(), fechaVencimientoLicencia, claseDeLicencia, 1, 1);
        unaLicencia.setTitular(unTitular);
        unaLicencia.setUsuarioResponsable(GestorDeConfiguracion.getUsuarioActual());
        
        return (gbd.guardarTitular(unTitular) && gbd.guardarLicencia(unaLicencia));
    }

    public Titular getTitularPorDNI(String tipoDocumento, String numDocumento) {
        Titular unTitular = gbd.getTitularPorDNI(tipoDocumento, numDocumento);
        List<Licencia> licencias = gbd.getLicenciasPorIDTitular(unTitular.getIdTitular());
        if(unTitular!=null){
            unTitular.setLicencias(licencias);
        }
        return unTitular;
    }
    
    public boolean validarDatosCreacionDeTitular(String tipoDeDocumento, int numeroDocumento, 
        String apellido, String nombre, LocalDate fechaNacimiento, String domicilio, String grupoSanguinio, 
        Boolean esDonante, char sexo, LocalDate fechaEntradaSistema, LocalDate fechaEmisionLicenciaTipoB){
        boolean datosCorrectos = true;
        StringBuffer errores = new StringBuffer("");
        
        if(fechaNacimiento.compareTo(LocalDate.now().minusYears(17)) > 0){
            errores.append("-El titular debe tener al menos 17 años.\n");
            datosCorrectos = false;
        }
        
        if(gbd.getTitularPorDNI(tipoDeDocumento,""+numeroDocumento)!= null){
        //if(gbd.usuarioRepetido(tipoDeDocumento, numeroDocumento)){
            errores.append("-El titular ya se encuentra registrado en el sistema.\n");
            datosCorrectos = false;
        }
        
      return datosCorrectos;  
    }

    private boolean validarLicenciaAEmitir(Titular unTitular, char claseLicencia) {
        boolean retorno;
        //fecha actual
        LocalDate fechaHoy = LocalDate.now();
        boolean poseeLicenciaMismaClase = false;
        //edad del titular
        int edadTitular = Period.between(unTitular.getFechaNacimiento(), fechaHoy).getYears();
        //tiempo desde que obtuvo su primera licencia de clase B
        int antiguedadClaseB = Period.between(unTitular.getFechaEmisionLicenciaTipoB(), fechaHoy).getYears();
        //Recorrer listado de licencias que ha tenido el titular
        for (Licencia licencia : unTitular.getLicencias()) {
            if(licencia.getClaseLicencia()==claseLicencia ){
                poseeLicenciaMismaClase=true;
            } 
        }
        switch (claseLicencia){
            case 'D': 
            case 'E':
            case 'F':
                //Caso de que no haya tenido licencia de clase B desde hace, por lo menos, un año y  es menor de 21 años
                if(antiguedadClaseB < 1 || edadTitular < 21){
                    retorno = false;
                }
                //Solo entra al else en caso de que lleve al menos un año con licencia de clase B y la edad sea mayor o igual a 21
                else{
//                    if(!poseeLicenciaMismaClase){
//                        retorno = true;
//                    }
//                    else{
                        retorno = (edadTitular < 65);
                    //}
                }
                break;
            case 'A':
            case 'B':
            case 'C':
            case 'G':
                //Si ya tiene una licencia vigente en realidad debe renovarla, falta ver si debe hacer esto o tira error
//                if(!poseeLicenciaMismaClase){
//                    retorno = validarLicenciaARenovar(unTitular,claseLicencia);
//                    break;
//                }
//                else{
                    retorno = !poseeLicenciaMismaClase;
                    break;
//                }
            default:
                retorno = false;
                break;
        }
        
        return retorno;
    }
    
    public boolean validarLicenciaARenovar(Titular unTitular, char claseLicencia){
        boolean retorno;
        //fecha actual
        LocalDate fechaHoy = LocalDate.now();
        Licencia licenciaARenovar = getLicenciaARenovar(unTitular.getLicencias(),claseLicencia);
        
        if(fechaHoy.isBefore(licenciaARenovar.getFechaVencimiento())){
            Period periodo = Period.between(licenciaARenovar.getFechaVencimiento(),fechaHoy);
            switch(periodo.getYears()){
                case 0:
                    switch(periodo.getDays()){
                        case 0:
                            retorno = periodo.getMonths()<=2;
                            break;
                        default:
                            retorno = periodo.getMonths()<2;
                            break;

                    }
                    break;
                default:
                    retorno = false;
                    break;
            }
        }
        else{
            retorno = true;
        }
        
        return retorno;
    }
    
    public void emitirLicencia(Titular unTitular, char claseLicencia) {
        GestorDeLicencias gdl = new GestorDeLicencias();
        LocalDate fechaVencimientoLicencia = 
                gdl.calcularVigenciaDeLicencia(unTitular.getFechaNacimiento(), null, claseLicencia);
        if(validarLicenciaAEmitir(unTitular, claseLicencia)){
            Licencia unaLicencia = new Licencia(LocalDate.now(),fechaVencimientoLicencia,claseLicencia,1,1);
            unaLicencia.setTitular(unTitular);
            gbd.guardarLicencia(unaLicencia);
        }
    }
    
    public void renovarLicencia(Titular unTitular, Licencia unaLicenciaARenovar){
        GestorDeLicencias gdl = new GestorDeLicencias();
        LocalDate fechaVencimientoLicencia = 
                gdl.calcularVigenciaDeLicencia(unTitular.getFechaNacimiento(), null, unaLicenciaARenovar.getClaseLicencia());
        if(validarLicenciaARenovar(unTitular, unaLicenciaARenovar.getClaseLicencia())){
            Licencia unaLicencia = new Licencia(LocalDate.now(),fechaVencimientoLicencia,unaLicenciaARenovar.getClaseLicencia(),1,unaLicenciaARenovar.getNumeroDeRenovacion()+1);
            unaLicencia.setTitular(unTitular);
            gbd.guardarLicencia(unaLicencia);
        }
    }

    private Licencia getLicenciaARenovar(List<Licencia> licencias, char claseLicencia) {
        Licencia retorno = new Licencia();
        LocalDate fechaLicenciaARenovar;
        int i=0;
        while(licencias.get(i).getClaseLicencia()!=claseLicencia){
            i++;
        }
        fechaLicenciaARenovar=licencias.get(i).getFechaVencimiento();
        
        for(Licencia licencia : licencias){
            if(licencia.getClaseLicencia()==claseLicencia){
                if(licencia.getFechaVencimiento().isAfter(fechaLicenciaARenovar)){
                    fechaLicenciaARenovar = licencia.getFechaVencimiento();
                    retorno = licencia;
                }
            }
        }
        
        return retorno;
    }
}
