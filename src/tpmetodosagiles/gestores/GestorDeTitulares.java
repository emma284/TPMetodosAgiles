/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.gestores;

import tpmetodosagiles.entidades.Titular;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.MONTHS;
import static java.time.temporal.ChronoUnit.YEARS;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import tpmetodosagiles.entidades.Licencia;

public class GestorDeTitulares {
    private GestorDeBaseDeDatos gbd;
    private GestorDeLicencias gdl;
    
    public GestorDeTitulares() {
        gbd = new GestorDeBaseDeDatos();
        gdl = new GestorDeLicencias();
    }
    
    
    public boolean emitirTitularYLicencia(String tipoDeDocumento, int numeroDocumento, 
            String apellido, String nombre, LocalDate fechaNacimiento, String domicilio, String grupoSanguinio, 
            Boolean esDonante, char sexo, char claseDeLicencia, String observaciones, String rutaDeFotoDeTitular){
        
       
        //Verifica si se trata de una licencia de clase B para indicarlo en el atributo de Titular
        LocalDate emisionLicenciaClaseB;
        if(claseDeLicencia == 'B')
            emisionLicenciaClaseB = LocalDate.now();
        else
            emisionLicenciaClaseB = null;
        
        //Guarda la foto seleccionada del titular y verifica si se logró guardar correctamente.
        String rutaDestinoDeFotoTitular = GestorDeBaseDeDatos.guardarFotoTitular(rutaDeFotoDeTitular, String.valueOf(numeroDocumento));
        if (rutaDestinoDeFotoTitular == null)
            return false;
        
        //Genera el Titular con sus datos
        Titular unTitular = new Titular(tipoDeDocumento, numeroDocumento, apellido, nombre, 
                fechaNacimiento, domicilio,  grupoSanguinio, esDonante, sexo, LocalDate.now(), 
                emisionLicenciaClaseB, observaciones, rutaDestinoDeFotoTitular);
        unTitular.setUsuarioResponsable(GestorDeConfiguracion.getUsuarioActual());
        System.out.println(unTitular.getUsuarioResponsable().getApellido());
        //Verifica si el usuario actual ya está en el sistema
        if(gbd.getTitularPorDNI(tipoDeDocumento, ""+numeroDocumento) != null){
            Alert mensajeErrores = new Alert(Alert.AlertType.INFORMATION);
            mensajeErrores.setTitle("No se pudo registrar el titular");
            mensajeErrores.setHeaderText("El titular ingresado ya se encuentra registrado en el sistema");
            mensajeErrores.setContentText("Bajo criterio de búsqueda: tipo y número de documento. \nDiríjase a la ventana 'Emitir licencia' para emitir nuevas licencias a titulares ya registrados."); 
            mensajeErrores.initModality(Modality.APPLICATION_MODAL);
            mensajeErrores.show();
            
            return false;
        }
        
        //Calcula la fecha de vencimiento de la licencia que se está emitiendo
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
        
        double [] costo = GestorDeLicencias.getArrayCostoLicencia(unaLicencia);
        GestorDeLicencias.crearPDFLicencia(unaLicencia,"src\\tpmetodosagiles\\recursos\\temp\\licencia.pdf", 1);
        GestorDeLicencias.crearPDFComprobante( Double.toString(costo[0]),  Double.toString(costo[1]),  Double.toString(costo[2]), "src\\tpmetodosagiles\\recursos\\temp\\comprobante.pdf", 1);
        return (gbd.guardarTitular(unTitular) && gbd.guardarLicencia(unaLicencia));
    }

    public Titular getTitularPorDNI(String tipoDocumento, String numDocumento) {
        Titular unTitular = gbd.getTitularPorDNI(tipoDocumento, numDocumento);
        if(unTitular!=null){
            List<Licencia> licencias = gbd.getLicenciasPorIDTitular(unTitular.getIdTitular());
            if(licencias!= null)
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
        StringBuffer errores = new StringBuffer("");
        //fecha actual
        LocalDate fechaHoy = LocalDate.now();
        boolean poseeLicenciaVigenteMismaClase = false;
        //edad del titular
        int edad = (int)YEARS.between(unTitular.getFechaNacimiento(), fechaHoy);
        //tiempo desde que obtuvo su primera licencia de clase B
        int antiguedadClaseB;
        if(unTitular.getFechaEmisionLicenciaTipoB() != null)
            antiguedadClaseB = (int)YEARS.between(unTitular.getFechaEmisionLicenciaTipoB(), fechaHoy);
        else
            antiguedadClaseB = 0;
        //Recorrer listado de licencias que ha tenido el titular si no está vacío
        for (Licencia licencia : unTitular.getLicencias()) {
            if(licencia.getClaseLicencia()==claseLicencia && licencia.getFechaVencimiento().isAfter(fechaHoy)){
                poseeLicenciaVigenteMismaClase=true;
            } 
        }
        switch (claseLicencia){
            
            case 'C':
            case 'D': 
            case 'E':
                //Caso de que no haya tenido licencia de clase B desde hace, por lo menos, un año y  es menor de 21 años
                if(antiguedadClaseB < 1 || edad < 21){
                    retorno = false;
                    errores.append(antiguedadClaseB<1 ? "-Debe tener una licencia de clase B emitida hace, al menos, un año.\n" : "");
                    errores.append(edad<21 ? "-Debe tener más de 21 años.\n" : "");
                }
                //Solo entra al else en caso de que lleve al menos un año con licencia de clase B y la edad sea mayor o igual a 21
                else{
                    errores.append(edad<65 ? "" : "-Debe ser menor de 65 años.\n");
                    retorno = (edad < 65);
                }
                break;
            case 'A':
            case 'B':
            case 'F':
            case 'G':
                //Nunca debería mostrar este error.
                errores.append(poseeLicenciaVigenteMismaClase ? "-Ya posee una licencia de clase " + claseLicencia + " vigente.\n" : "");
                retorno = !poseeLicenciaVigenteMismaClase;
                break;
            default:
                retorno = false;
                break;
        }
        if(!retorno){
            Alert mensajeErrores = new Alert(Alert.AlertType.INFORMATION);
            mensajeErrores.setTitle("Error");
            mensajeErrores.setHeaderText("La licencia no puede ser emitida.");
            mensajeErrores.setContentText(errores.toString());
            mensajeErrores.initModality(Modality.APPLICATION_MODAL);
            mensajeErrores.show();
        }
        return retorno;
    }
    
    public boolean validarLicenciaARenovar(Titular unTitular, Licencia unaLicencia){
        boolean retorno;
        StringBuffer errores = new StringBuffer("");
        LocalDate fechaHoy = LocalDate.now();
        LocalDate fechaNacimiento = unTitular.getFechaNacimiento();
        int edad = (int)YEARS.between(unTitular.getFechaNacimiento(), fechaHoy);
        if(fechaHoy.isBefore(unaLicencia.getFechaVencimiento()) && YEARS.between(fechaHoy, unaLicencia.getFechaVencimiento())<1){            
            retorno = MONTHS.between(fechaNacimiento,fechaHoy.minusYears(edad)) >= 10;
        }
        else{
            retorno = false;
        }
        
        if(!retorno){
            Alert mensajeErrores = new Alert(Alert.AlertType.INFORMATION);
            mensajeErrores.setTitle("Error");
            mensajeErrores.setHeaderText("La licencia no puede ser renovada.");
            mensajeErrores.setContentText(errores.toString());
            mensajeErrores.initModality(Modality.APPLICATION_MODAL);
            mensajeErrores.show();
        }
        return retorno;
    }
    
    public boolean emitirLicencia(Titular unTitular, char claseLicencia) {
        LocalDate fechaVencimientoLicencia = 
                gdl.calcularVigenciaDeLicencia(unTitular.getFechaNacimiento(), null, claseLicencia);
        if(validarLicenciaAEmitir(unTitular, claseLicencia)){
            Licencia unaLicencia = new Licencia(LocalDate.now(),fechaVencimientoLicencia,claseLicencia,1,1);
            unaLicencia.setTitular(unTitular);
            unaLicencia.setUsuarioResponsable(GestorDeConfiguracion.getUsuarioActual());
            if(gbd.guardarLicencia(unaLicencia)){
                double [] costo = GestorDeLicencias.getArrayCostoLicencia(unaLicencia);
                GestorDeLicencias.crearPDFLicencia(unaLicencia,"src\\tpmetodosagiles\\recursos\\temp\\licencia.pdf", 1);
                GestorDeLicencias.crearPDFComprobante( Double.toString(costo[0]),  Double.toString(costo[1]),  Double.toString(costo[2]), "src\\tpmetodosagiles\\recursos\\temp\\comprobante.pdf", 1);
                
                //Se registraron la nueva licencia con éxito => muestra un mensaje
                Alert mensajeExito = new Alert(Alert.AlertType.INFORMATION);
                mensajeExito.setTitle("Transacción completada");
                mensajeExito.setHeaderText("Se ha registrado la licencia en el sistema");
                mensajeExito.initModality(Modality.APPLICATION_MODAL);
                mensajeExito.show();
                
                return true;
            }
        }
        return false;
    }
    
    public boolean renovarLicencia(Licencia unaLicenciaARenovar){
        LocalDate fechaVencimientoLicencia = 
                gdl.calcularVigenciaDeLicencia(unaLicenciaARenovar.getTitular().getFechaNacimiento(), null, unaLicenciaARenovar.getClaseLicencia());
        if(validarLicenciaARenovar(unaLicenciaARenovar.getTitular(), unaLicenciaARenovar)){
            Licencia unaLicencia = new Licencia(LocalDate.now(),fechaVencimientoLicencia,unaLicenciaARenovar.getClaseLicencia(),1,unaLicenciaARenovar.getNumeroDeRenovacion()+1);
            unaLicencia.setTitular(unaLicenciaARenovar.getTitular());
            unaLicencia.setUsuarioResponsable(GestorDeConfiguracion.getUsuarioActual());
            if(gbd.guardarLicencia(unaLicencia)){
                unaLicenciaARenovar.setFechaVencimiento(LocalDate.now().minusDays(1));
                gbd.modificarLicencia(unaLicenciaARenovar);
                double [] costo = GestorDeLicencias.getArrayCostoLicencia(unaLicencia);
                GestorDeLicencias.crearPDFLicencia(unaLicencia,"src\\tpmetodosagiles\\recursos\\temp\\licencia.pdf", 1);
                GestorDeLicencias.crearPDFComprobante( Double.toString(costo[0]),  Double.toString(costo[1]),  Double.toString(costo[2]), "src\\tpmetodosagiles\\recursos\\temp\\comprobante.pdf", 1);
                
                //Se registraron la nueva licencia con éxito => muestra un mensaje
                Alert mensajeExito = new Alert(Alert.AlertType.INFORMATION);
                mensajeExito.setTitle("Transacción completada");
                mensajeExito.setHeaderText("Se ha registrado la licencia en el sistema");
                mensajeExito.initModality(Modality.APPLICATION_MODAL);
                mensajeExito.show();
                
                return true;
            }
        }
        return false;
    }
    
    public void guardarModificacionTitular(Titular unTitular){
        gbd.guardarTitular(unTitular);
    }
    

//    private Licencia getLicenciaARenovar(List<Licencia> licencias, char claseLicencia) {
//        
//        LocalDate fechaLicenciaARenovar = null;
//        int i=0;
//        int posicionDeLicencia=0;
//        
//        for(Licencia licencia : licencias){
//            if(licencia.getClaseLicencia()==claseLicencia){
//                if(fechaLicenciaARenovar == null || licencia.getFechaVencimiento().isAfter(fechaLicenciaARenovar)){
//                    fechaLicenciaARenovar = licencia.getFechaVencimiento();
//                    posicionDeLicencia=i;
//                }
//            }
//            i++;
//        }
//        
//        return fechaLicenciaARenovar == null ? null : licencias.get(posicionDeLicencia);
//    }
}
