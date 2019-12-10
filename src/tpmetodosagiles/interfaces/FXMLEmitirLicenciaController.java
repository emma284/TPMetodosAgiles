/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.interfaces;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import tpmetodosagiles.entidades.Licencia;
import tpmetodosagiles.entidades.Titular;
import tpmetodosagiles.entidades.Usuario;
import tpmetodosagiles.gestores.GestorDeBaseDeDatos;
import tpmetodosagiles.gestores.GestorDeConfiguracion;
import tpmetodosagiles.gestores.GestorDeDatosDeInterface;
import tpmetodosagiles.gestores.GestorDeTitulares;
import tpmetodosagiles.layouts.TextFieldSoloNumeros;

/**
 * FXML Controller class
 *
 * @author Gino
 */
public class FXMLEmitirLicenciaController implements Initializable {
    GestorDeTitulares gestorTitular;
    @FXML
    private ComboBox cbTipoDocumento;
    @FXML
    private TextFieldSoloNumeros tfNumeroDocumento;
    @FXML
    private TextField tfNombres;
    @FXML
    private TextField tfApellidos;
    @FXML
    private DatePicker dpFechaNacimiento;
    @FXML
    private ComboBox cbSexo;
    @FXML
    private ComboBox cbCalleTitular;
    @FXML
    private TextField tfNroAltura;
    @FXML
    private TextField tfNroInterno;
    @FXML
    private TextField tfPiso;
    @FXML
    private ComboBox cbEsDonante;
    @FXML
    private ComboBox cbGrupoSanguineo;
    @FXML
    private ComboBox cbObservaciones;
    @FXML
    private ComboBox cbClaseLicencia;
    
    @FXML
    private ListView<Licencia> lvLicencias;

    private ObservableList<Licencia> licenciaObservableList;
    
    private Titular unTitular;
    private Licencia unaLicencia;
    
    public FXMLEmitirLicenciaController(){
        gestorTitular = new GestorDeTitulares();
        licenciaObservableList = FXCollections.observableArrayList();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList <String> tiposDocumentos = FXCollections.observableArrayList( GestorDeDatosDeInterface.getTipoDeDocumento() );
        cbTipoDocumento.setItems(tiposDocumentos);
        
        tfNumeroDocumento.setLongitudMaxima(8);
    }
    
    public void titularNoEncontrado(){
        limpiarDatosTitular();
        Alert mensajeErrores = new Alert(Alert.AlertType.INFORMATION);
        mensajeErrores.setTitle("No se puede encontrar el titular");
        mensajeErrores.setHeaderText("Los datos de búsqueda no coinciden con los de un titular registrado en el sistema.");
        mensajeErrores.setContentText("Verifique que los datos de 'Tipo de documento' y 'Número de documento' hayan sido ingresados correctamente.");
        mensajeErrores.initModality(Modality.APPLICATION_MODAL);
        mensajeErrores.show();
    }
    
    
    public void setDatosTitular(){
        tfNombres.setText(unTitular.getNombre());
        tfApellidos.setText(unTitular.getApellido());

        dpFechaNacimiento.setValue(unTitular.getFechaNacimiento());

        String direccion = unTitular.getDomicilio();
        String[] campos = direccion.split("%");
        cbCalleTitular.getSelectionModel().select(campos[0]);
        tfNroAltura.setText(campos[1]);
        tfNroInterno.setText(campos[2]);
        tfPiso.setText(campos[3]);
        
        //Listas de los desplegables

        cbGrupoSanguineo.getSelectionModel().select(unTitular.getGrupoSanguinio());

        cbEsDonante.getSelectionModel().select(unTitular.getEsDonante() ? "Sí" : "No");
        
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
        
        cbObservaciones.getSelectionModel().select(unTitular.getObservaciones());
        licenciaObservableList.clear();
        /*Se revisan todas las licencias vigentes del titular, se agregan las vigentes al listado de licencias vigentes y las que se pueden emitir en el desplegable.*/
        ObservableList <String> claseLicencia = FXCollections.observableArrayList( GestorDeDatosDeInterface.getLicencias());
        for(Licencia licencia: unTitular.getLicencias()){
            if(licencia.getFechaVencimiento().isAfter(LocalDate.now()) || licencia.getFechaVencimiento().isEqual(LocalDate.now())){
                licenciaObservableList.add(licencia);
                claseLicencia.remove(String.valueOf(licencia.getClaseLicencia()));
            }
        }
        cbClaseLicencia.setItems(claseLicencia);
        
        lvLicencias.setItems(licenciaObservableList);
        lvLicencias.setCellFactory(studentListView -> new FXMLCeldaListaLicenciasController());
    }
    
    public void limpiarDatosTitular(){
        tfNombres.clear();
        tfApellidos.clear();
        dpFechaNacimiento.setValue(null);
        cbCalleTitular.setValue(null);
        tfNroAltura.clear();
        tfNroInterno.clear();
        tfPiso.clear();
        cbGrupoSanguineo.setValue(null);
        cbEsDonante.setValue(null);
        cbSexo.setValue(null);
        cbObservaciones.setValue(null);
        cbClaseLicencia.setValue(null);
        licenciaObservableList.clear();
    }
    
    public void validarDatos(){
        if(cbTipoDocumento.getSelectionModel().isEmpty() && tfNumeroDocumento.getText().isEmpty()){
            limpiarDatosTitular();
            Alert mensajeErrores = new Alert(Alert.AlertType.INFORMATION);
            mensajeErrores.setTitle("Datos incorrectos");
            mensajeErrores.setHeaderText("Los datos de búsqueda son incorrectos o están incompletos");
            mensajeErrores.setContentText("Verifique que los datos de 'Tipo de documento' y 'Número de documento' hayan sido ingresado correctamente.");
            mensajeErrores.initModality(Modality.APPLICATION_MODAL);
            mensajeErrores.show();
        }
        else{
            unTitular = gestorTitular.getTitularPorDNI(cbTipoDocumento.getSelectionModel().getSelectedItem().toString(), tfNumeroDocumento.getText());
            if(unTitular == null){
                titularNoEncontrado();
            }else{
                setDatosTitular();
            }
        }
    }
    
    @FXML
    public void onEnter(ActionEvent ae){
       validarDatos();
    }
    
    
    @FXML
    public void emitirLicenciaOnClick(){
        if(cbClaseLicencia.getSelectionModel().getSelectedItem()==null){
            Alert mensajeErrores = new Alert(Alert.AlertType.INFORMATION);
            mensajeErrores.setTitle("Datos faltantes");
            mensajeErrores.setHeaderText("Debe seleccionar la clase de licencia que desea emitir.");
            mensajeErrores.initModality(Modality.APPLICATION_MODAL);
            mensajeErrores.show();
        }else if(gestorTitular.emitirLicencia(unTitular,cbClaseLicencia.getSelectionModel().getSelectedItem().toString().charAt(0))){
            validarDatos();
        }
    }
    
    @FXML
    public void renovarLicenciaOnClick(){
        if(lvLicencias.getSelectionModel().getSelectedItem()==null){
            Alert mensajeErrores = new Alert(Alert.AlertType.INFORMATION);
            mensajeErrores.setTitle("Datos faltantes");
            mensajeErrores.setHeaderText("Debe seleccionar de la lista la licencia que desea renovar.");
            mensajeErrores.initModality(Modality.APPLICATION_MODAL);
            mensajeErrores.show();
        }else if(gestorTitular.renovarLicencia(lvLicencias.getSelectionModel().getSelectedItem())){
            validarDatos();
        }
        
    }
}
