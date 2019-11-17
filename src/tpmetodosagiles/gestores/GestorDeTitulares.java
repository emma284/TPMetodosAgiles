/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.gestores;

import tpmetodosagiles.entidades.Titular;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import tpmetodosagiles.entidades.Licencia;

public class GestorDeTitulares {
            
    private GestorDeBaseDeDatos gbd = new GestorDeBaseDeDatos();
    
    public boolean emitirTitularYLicencia(String tipoDeDocumento, int numeroDocumento, 
            String apellido, String nombre, LocalDate fechaNacimiento, String domicilio, String grupoSanguinio, 
            Boolean esDonante, char sexo, char claseDeLicencia){
        
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
                emisionLicenciaClaseB);
        unTitular.setUsuarioResponsable(GestorDeConfiguracion.getUsuarioActual());
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
    
}
