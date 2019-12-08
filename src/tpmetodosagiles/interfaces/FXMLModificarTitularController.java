/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.interfaces;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import tpmetodosagiles.entidades.Titular;
import tpmetodosagiles.enums.SexoEnum;
import tpmetodosagiles.gestores.GestorDeConfiguracion;
import tpmetodosagiles.gestores.GestorDeDatosDeInterface;
import tpmetodosagiles.gestores.GestorDeTitulares;

/**
 * FXML Controller class
 *
 * @author usuario
 */
public class FXMLModificarTitularController implements Initializable {

     GestorDeTitulares gestorTitular;
//    @FXML
//    private TextField tfTipoDocumento;
    @FXML
    private TextField tfNumeroDocumento;
    @FXML
    private TextField tfNombreTitular;
    @FXML
    private TextField tfApellidoTitular;
    @FXML
    private ComboBox cbObservaciones;
    @FXML
    private TextField tfNroAltura;
    @FXML
    private TextField tfNroInterno;
    @FXML
    private TextField tfPiso;
    @FXML
    private ComboBox cbTipoDocumento;
    @FXML
    private ComboBox cbCalleTitular;
    @FXML
    private ComboBox cbGrupoSanguinio;
    @FXML
    private ComboBox cbEsDonante;
    @FXML
    private ComboBox cbSexo;
    @FXML
    private ComboBox cbClaseLicencia;
    @FXML
    private DatePicker dpFechaNacimiento;
    @FXML
    private ImageView imgFotoTitular;
    
    private Titular unTitular;

    public FXMLModificarTitularController() {
        gestorTitular = new GestorDeTitulares();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList <String> tiposDocumentos = FXCollections.observableArrayList( GestorDeDatosDeInterface.getTipoDeDocumento() );
        cbTipoDocumento.setItems(tiposDocumentos);
        
        ObservableList <String> grupoSanguinio = FXCollections.observableArrayList( GestorDeDatosDeInterface.getGrupoSanguinio() );
        cbGrupoSanguinio.setItems(grupoSanguinio);
        
        ObservableList <String> esDonante = FXCollections.observableArrayList( GestorDeDatosDeInterface.getEsDonante() );
        cbEsDonante.setItems(esDonante);
        
        ObservableList <String> sexos = FXCollections.observableArrayList( GestorDeDatosDeInterface.getSexos() );
        cbSexo.setItems(sexos);
        
        ObservableList <String> calles = FXCollections.observableArrayList( GestorDeDatosDeInterface.getCalles() );
        cbCalleTitular.setItems(calles);
        
        ObservableList <String> observaciones = FXCollections.observableArrayList( GestorDeDatosDeInterface.getObservaciones() );
        cbObservaciones.setItems(observaciones);
    }
    
            
    @FXML
    private void modificarTitular(ActionEvent event){
        
        if(!this.datosValidos())
            return;
        
        boolean esDonante = GestorDeDatosDeInterface.esDonanteToBoolean(cbEsDonante.getValue().toString());
        String domicilio = GestorDeDatosDeInterface.domicilioFormateado(cbCalleTitular.getValue().toString(), tfNroAltura.getText(), tfNroInterno.getText(), tfPiso.getText() );
        char sexo = GestorDeDatosDeInterface.sexoToChar(cbSexo.getValue().toString());
        
        String grupoSanguineo = cbGrupoSanguinio.getValue().toString();
        String observaciones = cbObservaciones.getValue().toString();
        
        unTitular.setDomicilio(domicilio);
        unTitular.setGrupoSanguinio(grupoSanguineo);
        unTitular.setEsDonante(esDonante);
        unTitular.setSexo(sexo);
        unTitular.setObservaciones(observaciones);
        
        gestorTitular.guardarModificacionTitular(unTitular); 
    
       
        Alert mensajeErrores = new Alert(Alert.AlertType.INFORMATION);
        mensajeErrores.setTitle("Modificación Exitosa");
        mensajeErrores.setHeaderText("Se han guardado correctamente las modificaciones al Titular");
        mensajeErrores.initModality(Modality.APPLICATION_MODAL);
        mensajeErrores.show();
        return;
        
        

    }
        
    
    
    public void buscarTitular(){
        
        if(!datosDeBusquedaValidos()){
            Alert mensajeErrores = new Alert(Alert.AlertType.INFORMATION);
            mensajeErrores.setTitle("Datos de búsqueda faltantes/incorrectos");
            mensajeErrores.setHeaderText("Debe ingresar los campos correctamente para hallar un Titular");
            
            mensajeErrores.initModality(Modality.APPLICATION_MODAL);
            mensajeErrores.show();
            return;
        }
        
        unTitular = gestorTitular.getTitularPorDNI(cbTipoDocumento.getSelectionModel().getSelectedItem().toString(), tfNumeroDocumento.getText());
        if(unTitular == null){
            Alert mensajeErrores = new Alert(Alert.AlertType.INFORMATION);
            mensajeErrores.setTitle("No hay Titulat");
            mensajeErrores.setHeaderText("No se ha encontrado un titular para los campos de búsqueda ingresados");
            mensajeErrores.initModality(Modality.APPLICATION_MODAL);
            mensajeErrores.show();
        }
        else{
            setDatosTitular();
        }
    }
    
    public void setDatosTitular(){
        tfNombreTitular.setText(unTitular.getNombre());
        tfApellidoTitular.setText(unTitular.getApellido());

        dpFechaNacimiento.setValue(unTitular.getFechaNacimiento());

        String direccion = unTitular.getDomicilio();
        String[] campos = direccion.split("%");
        cbCalleTitular.getSelectionModel().select(campos[0]);
        tfNroAltura.setText(campos[1]);
        tfNroInterno.setText(campos[2]);
        tfPiso.setText(campos[3]);
        
        ObservableList <String> observaciones = FXCollections.observableArrayList( GestorDeDatosDeInterface.getObservaciones() );
        cbObservaciones.setItems(observaciones);
        cbObservaciones.getSelectionModel().select(unTitular.getObservaciones());

        ObservableList <String> grupoSanguinio = FXCollections.observableArrayList( GestorDeDatosDeInterface.getGrupoSanguinio() );
        cbGrupoSanguinio.setItems(grupoSanguinio);
        cbGrupoSanguinio.getSelectionModel().select(unTitular.getGrupoSanguinio());
        
        ObservableList <String> esDonante = FXCollections.observableArrayList( GestorDeDatosDeInterface.getEsDonante() );
        cbEsDonante.setItems(esDonante);
        cbEsDonante.getSelectionModel().select(unTitular.getEsDonante() ? "Sí" : "No");
        
        ObservableList <String> sexos = FXCollections.observableArrayList( GestorDeDatosDeInterface.getSexos() );
        cbSexo.setItems(sexos);
        switch(unTitular.getSexo()){
            case 'm':
            case 'M':
                cbSexo.getSelectionModel().select("Masculino");
                break;
            case 'f':
            case 'F':
                cbSexo.getSelectionModel().select("Femenino");
                break;
        }
        
        ObservableList <String> calles = FXCollections.observableArrayList( GestorDeDatosDeInterface.getCalles() );
        cbCalleTitular.setItems(calles);
        
    }

    @FXML
    private void borrarDatosDePantalla(ActionEvent event){
        this.borrarDatosTitular();
        this.deshabilitarEdicionDatosDeTitular();
        this.habilitarBusqueda();
        
        
        /*
        borrar datos introducidos (no olvidar imagen)
        deshabilitar datos de titular introducidos y habilitar datos de busqueda
        */
    }

    
    /**
     * Valida que se hallan cargado todos los datos de interface obligatorios.
     * @return <i>boolean:</i> 'true' si los datos de interface son correctos y están completos; 'false' en otro caso.
     */

    private boolean datosValidos(){
        boolean datosCorrectos = true;
        StringBuffer errores = new StringBuffer("");
        
        
        if (cbCalleTitular.getValue() == null){
            errores.append("-El campo 'Calle' no puede estár vacío.\n");
            datosCorrectos = false;
        }
        if (tfNroAltura.getText().isEmpty()){
            errores.append("-El campo 'Altura' no puede estár vacío.\n");
            datosCorrectos = false;
        }
        if (cbGrupoSanguinio.getValue() == null){
            errores.append("-Debe seleccionar una de las opciones de 'Grupo Sanguínio'.\n");
            datosCorrectos = false;
        }
        if (cbEsDonante.getValue() == null){
            errores.append("-Debe especificar si el titular es donante o no.\n");
            datosCorrectos = false;
        }
        if (cbSexo.getValue() == null){
            errores.append("-Debe seleccionar el sexo de nacimiento del titular.\n");
            datosCorrectos = false;
        }
        try{
            Integer.parseInt(tfNroAltura.getText());
        }
        catch(NumberFormatException fne){
            errores.append("-La altura debe ser un número.\n");
            datosCorrectos = false;
        }
        
        if (!datosCorrectos){
            Alert mensajeErrores = new Alert(Alert.AlertType.INFORMATION);
            mensajeErrores.setTitle("Datos faltantes/incorrectos");
            mensajeErrores.setHeaderText("Debe corregir los siguientes puntos antes de proseguir");
            mensajeErrores.setContentText(errores.toString());
            
            mensajeErrores.initModality(Modality.APPLICATION_MODAL);
            mensajeErrores.show();
        }
        
        return datosCorrectos;
    }
    
    /**
     * Verifica que los datos de 'Tipo de documento' y 'Número de documento' sean validos (no sean nulos ni vacíos y que el número de documento solo esté compuesto por caracteres numéricos).
     */
    private boolean datosDeBusquedaValidos() {
        
        String tipoDocumento = (cbTipoDocumento.getValue()==null)? null : cbTipoDocumento.getSelectionModel().getSelectedItem().toString();
        String numeroDocumento = tfNumeroDocumento.getText();
        
        if (tipoDocumento == null || numeroDocumento == null){
            return false;
        }
        
        if(tipoDocumento.isEmpty()){
            return false;
        }
        
        try{
            Integer.parseInt(numeroDocumento);
        }
        catch(NumberFormatException fne){
            return false;
        }
        
        return true;
    }


    private void deshabilitarBusqueda() {
        cbTipoDocumento.setDisable(true);
        tfNumeroDocumento.setDisable(true);
    }
    
    private void habilitarBusqueda() {
        cbTipoDocumento.setDisable(false);
        tfNumeroDocumento.setDisable(false);
    }
    private void habilitarEdicionDatosDeTitular() {
        
        cbCalleTitular.setDisable(false);
        tfNroAltura.setDisable(false);
        tfPiso.setDisable(false);
        tfNroInterno.setDisable(false);
        cbSexo.setDisable(false);
        cbSexo.setDisable(false);
    }

    private void deshabilitarEdicionDatosDeTitular() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void borrarDatosTitular() {
        //TODO No olvidar imagen
    }
}
