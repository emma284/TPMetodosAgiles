/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.interfaces;

import java.io.File;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javax.swing.filechooser.FileSystemView;
import tpmetodosagiles.entidades.ContribuyenteDTO;
import tpmetodosagiles.enums.SexoEnum;
import tpmetodosagiles.gestores.GestorDeConfiguracion;
import tpmetodosagiles.gestores.GestorDeContribuyente;
import tpmetodosagiles.gestores.GestorDeDatosDeInterface;
import tpmetodosagiles.gestores.GestorDeTitulares;
import tpmetodosagiles.layouts.TextFieldSoloLetras;
import tpmetodosagiles.layouts.TextFieldSoloNumeros;

/**
 * FXML Controller class
 *
 * @author usuario
 */
public class FXMLDarAltaTitularController implements Initializable {

    @FXML
    private TextFieldSoloNumeros tfNumeroDocumento;
    @FXML
    private TextFieldSoloLetras tfNombreTitular;
    @FXML
    private TextFieldSoloLetras tfApellidoTitular;
    @FXML
    private TextFieldSoloNumeros tfNroAltura;
    @FXML
    private TextFieldSoloNumeros tfNroInterno;
    @FXML
    private TextFieldSoloNumeros tfPiso;
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
    private ComboBox cbObservaciones;
    @FXML
    private ComboBox cbClaseLicencia;
    @FXML
    private DatePicker dpFechaNacimiento;
    @FXML
    private ImageView imgFotoTitular;
    @FXML
    private Button btnBuscarContribuyente;
    @FXML
    private Button btnBorrarTodo;
    @FXML
    private Button btnSeleccionarFotografia;
    @FXML
    private Button btnDarDeAltaTitular;
    
    
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
        
        ObservableList <String> claseLicencia = FXCollections.observableArrayList( GestorDeDatosDeInterface.getLicenciasBasicas() );
        cbClaseLicencia.setItems(claseLicencia);
        
        tfNumeroDocumento.setLongitudMaxima(8);
        tfNroAltura.setLongitudMaxima(6);
        tfNroInterno.setLongitudMaxima(5);
        tfPiso.setLongitudMaxima(2);
    }
    
    @FXML
    private void buscarEnBDCiudad(ActionEvent event){
        if(!this.datosDeBusquedaValidos((String) cbTipoDocumento.getValue(), tfNumeroDocumento.getText())){
            Alert mensajeErrores = new Alert(Alert.AlertType.INFORMATION);
            mensajeErrores.setTitle("Datos incorrectos");
            mensajeErrores.setHeaderText("Los datos de búsqueda son incorrectos o están incompletos");
            mensajeErrores.setContentText("Verifique que los datos de 'Tipo de documento' y 'Número de documento' hayan sido ingresado correctamente.");
            mensajeErrores.initModality(Modality.APPLICATION_MODAL);
            mensajeErrores.show();
            
            return;
        }
        
        GestorDeContribuyente gc = new GestorDeContribuyente();
        ContribuyenteDTO contr;
        contr = gc.getContribuyente((String) cbTipoDocumento.getValue().toString(), tfNumeroDocumento.getText());
        
        if(contr == null){
            //No se encontró al humano en la base de datos de la ciudad, entonces no es contribuyente en la ciudad
            Alert mensajeErrores = new Alert(Alert.AlertType.INFORMATION);
            mensajeErrores.setTitle("No se puede registrar al solicitante");
            mensajeErrores.setHeaderText("Los datos de búsqueda no coinciden con los de un contribuyente de la ciudad.");
            mensajeErrores.setContentText("Verifique que los datos de 'Tipo de documento' y 'Número de documento' hayan sido ingresado correctamente.");
            mensajeErrores.initModality(Modality.APPLICATION_MODAL);
            mensajeErrores.show();
            
            return;
        }
        else{
            this.rellenarDatosTitularConDatosContribuyente(contr);
            this.deshabilitarBusqueda();
            this.habilitarEdicionDatosDeTitular();
        }
        
        
        /*
        llamar al ADB2 y pedirle el contribuyente
        verificar si el contribuyente existe
        si existe => deshabilitar las opciones de busqueda y habilitar los de datos del titular; y rellenar los datos de la pantalla que estén disponibles
        si no existe => mostrar mensaje
        
        */
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
    
    @FXML
    private void seleccionarFotografia(ActionEvent event){
        FileChooser.ExtensionFilter filtroImagenes = new FileChooser.ExtensionFilter("Archivos de Imagen", "*.jpg", "*.jpeg");
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(FileSystemView.getFileSystemView().getHomeDirectory());
        fc.getExtensionFilters().add(filtroImagenes);
        File file = fc.showOpenDialog(GestorDeConfiguracion.getVentanaActual());
        
        if(file == null)
            return;
        
        Image foto = new Image(file.toURI().toString());
        imgFotoTitular.setImage(foto);
    }
    
    
    @FXML
    private void darDeAltaTitular(ActionEvent event){
        //Verifica que todos los datos obligatorios hayan sido cargados a la interface (que no esté vacía la opción)
        if (!this.datosValidos())
            return;
        
        //Obtiene el valor booleano correspondiente al elemento elegido en el ComboBox cbEsDonante
        boolean esDonante = GestorDeDatosDeInterface.esDonanteToBoolean(cbEsDonante.getValue().toString());
        //Obtiene el domicilio formateado
        String domicilio = GestorDeDatosDeInterface.domicilioFormateado(cbCalleTitular.getValue().toString(), tfNroAltura.getText(), tfNroInterno.getText(), tfPiso.getText() );
        //Obtiene el valor char de sexo
        char sexo = GestorDeDatosDeInterface.sexoToChar(cbSexo.getValue().toString());
       
        
        //Intenta crear un nuevo titular
        GestorDeTitulares gestTitular = new GestorDeTitulares();
        try{
            if (gestTitular.emitirTitularYLicencia(cbTipoDocumento.getValue().toString(), Integer.parseInt(tfNumeroDocumento.getText()),
                    tfApellidoTitular.getText(), tfNombreTitular.getText(), dpFechaNacimiento.getValue(), domicilio, 
                    cbGrupoSanguinio.getValue().toString(), esDonante, sexo,
                    GestorDeDatosDeInterface.tipoLicenciaToChar(cbClaseLicencia.getValue().toString()),
                    cbObservaciones.getValue().toString())){
                
                // Se registran el nuevo titular y licencia con exito
                Alert mensajeExito = new Alert(Alert.AlertType.INFORMATION);
                mensajeExito.setTitle("Transacción completada");
                mensajeExito.setHeaderText("Se ha registrado el titular y la licencia en el sistema");
                mensajeExito.initModality(Modality.APPLICATION_MODAL);
                mensajeExito.show();
            }
            
        }
        catch(NumberFormatException nfe){
            //Ocurre si el número de documento no se puede parsear como tal
            Alert mensajeErrores = new Alert(Alert.AlertType.INFORMATION);
            mensajeErrores.setTitle("Datos incorrectos");
            mensajeErrores.setHeaderText("Debe corregir los siguientes puntos antes de proseguir");
            mensajeErrores.setContentText("El número de documento solo puede contener caracteres numéricos.");
            
            mensajeErrores.initModality(Modality.APPLICATION_MODAL);
            mensajeErrores.show();
        }
    }
    
    /**
     * Valida que se hallan cargado todos los datos de interface obligatorios.
     * @return <i>boolean:</i> 'true' si los datos de interface son correctos y están completos; 'false' en otro caso.
     */
    private boolean datosValidos(){
        boolean datosCorrectos = true;
        StringBuffer errores = new StringBuffer("");
        
        if (cbTipoDocumento.getValue() == null){
            errores.append("-Debe seleccionar el tipo de documento de identificación nacional del titular.\n");
            datosCorrectos = false;
        }
        if (tfNumeroDocumento.getText().length() < 7){
            errores.append("-El campo 'Nro de Documento' debe contener al menos 7 caracteres.\n");
            datosCorrectos = false;
        }
        if (tfNombreTitular.getText().isEmpty()){
            errores.append("-El campo 'Nombres' no puede estár vacío.\n");
            datosCorrectos = false;
        }
        if (tfApellidoTitular.getText().isEmpty()){
            errores.append("-El campo 'Apellidos' no puede estár vacío.\n");
            datosCorrectos = false;
        }
        if (dpFechaNacimiento.getValue() == null){
            errores.append("-El campo 'Fecha de Nacimiento' no puede estár vacío.\n");
            datosCorrectos = false;
        }
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
        if (cbObservaciones.getValue() == null ){
            errores.append("-Debe seleccionar una de las opciones de 'Observaciones'.\n");
            datosCorrectos = false;
        }
        if (cbClaseLicencia.getValue() == null){
            errores.append("-Debe seleccionar una de las opciones de 'Clase de Licencia Solicitada'.\n");
            datosCorrectos = false;
        }
        //TODO: Emir-Luciano: verificar que se haya seleccionado una fotografía (que no sea la de 'por defecto')
        
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
    private boolean datosDeBusquedaValidos(String tipoDocumento, String numeroDocumento) {
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
    
    
    private void rellenarDatosTitularConDatosContribuyente(ContribuyenteDTO contr) {
        tfNombreTitular.setText(contr.nombres);
        tfApellidoTitular.setText(contr.apellidos);
        dpFechaNacimiento.setValue(contr.fechaNacimiento);
        if(contr.calle != null)
            cbCalleTitular.setValue(contr.calle);
        if(contr.altura != 0)
            tfNroAltura.setText("" + contr.altura);
        cbSexo.setValue(contr.sexo.getDescriptor());
        if(contr.departamento != null)
            tfNroInterno.setText(contr.departamento);
        
        //TODO Observaciones no puede quedar vacio cuando valide los datos del sistema (en todo caso debe quedar en "Ninguno")
    }

    private void deshabilitarBusqueda() {
        cbTipoDocumento.setDisable(true);
        tfNumeroDocumento.setDisable(true);
        btnBuscarContribuyente.setDisable(true);
    }
    
    private void habilitarBusqueda() {
        cbTipoDocumento.setDisable(false);
        tfNumeroDocumento.setDisable(false);
        btnBuscarContribuyente.setDisable(false);
    }
    private void habilitarEdicionDatosDeTitular() {
        
        cbCalleTitular.setDisable(false);
        tfNroAltura.setDisable(false);
        tfPiso.setDisable(false);
        tfNroInterno.setDisable(false);
        cbSexo.setDisable(false);
        cbGrupoSanguinio.setDisable(false);
        cbEsDonante.setDisable(false);
        cbObservaciones.setDisable(false);
        cbClaseLicencia.setDisable(false);
        btnBorrarTodo.setDisable(false);
        btnSeleccionarFotografia.setDisable(false);
        btnDarDeAltaTitular.setDisable(false);
    }

    private void deshabilitarEdicionDatosDeTitular() {
        
        cbCalleTitular.setDisable(true);
        tfNroAltura.setDisable(true);
        tfPiso.setDisable(true);
        tfNroInterno.setDisable(true);
        cbSexo.setDisable(true);
        cbGrupoSanguinio.setDisable(true);
        cbEsDonante.setDisable(true);
        cbObservaciones.setDisable(true);
        cbClaseLicencia.setDisable(true);
        btnBorrarTodo.setDisable(true);
        btnSeleccionarFotografia.setDisable(true);
        btnDarDeAltaTitular.setDisable(true);
    }

    private void borrarDatosTitular() {
        tfNombreTitular.clear();
        tfApellidoTitular.clear();
        dpFechaNacimiento.setValue(null);
        cbCalleTitular.setValue("");
        tfNroAltura.clear();
        tfNroInterno.clear();
        cbSexo.setValue("");
        //TODO No olvidar imagen
    }

    
}
