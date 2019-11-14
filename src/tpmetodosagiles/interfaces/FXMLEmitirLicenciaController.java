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
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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
    private TextField tfNumeroDocumento;
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
    
    private GestorDeConfiguracion configuracion;
    private Titular unTitular;
    
    public FXMLEmitirLicenciaController(){
        gestorTitular = new GestorDeTitulares();
    }
    
    public void setConfiguracion(GestorDeConfiguracion configuracion) {
        this.configuracion = configuracion;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO setDatosTitular()
        // Metodo que busca en la base un titular por DNI ingresado y si existe
        // carga los datos y licencias vigentes en la interfaz 
        
    }   
    
    public void setDatosTitular(){
        
    }
    
    
    @FXML
    public void onEnter(ActionEvent ae){
       //unTitular = gestorTitular.getTitularPorDNI(tfNumeroDocumento.getText());
       //borrar desde acá
       /*unTitular = new Titular();
       unTitular.setNombre("emmanuel");
       unTitular.setApellido("ábrego");
       unTitular.setFechaNacimiento(new Date());
       unTitular.setEsDonante(true);
       unTitular.setSexo('m');
       unTitular.setTipoDeDocumento("DNI");
       unTitular.setGrupoSanguinio("A+");
       
       System.out.println(unTitular.getNombre());*///hasta acá
       tfNombres.setText(unTitular.getNombre());
       tfApellidos.setText(unTitular.getApellido());
       
       dpFechaNacimiento.setValue(dateToLocalDate(unTitular.getFechaNacimiento()));
       
       //Listas de los desplegables
       ObservableList <String> tiposDocumentos = FXCollections.observableArrayList( GestorDeDatosDeInterface.getTipoDeDocumento() );
        cbTipoDocumento.setItems(tiposDocumentos);
        cbTipoDocumento.getSelectionModel().select(unTitular.getTipoDeDocumento());
        
        ObservableList <String> grupoSanguineo = FXCollections.observableArrayList( GestorDeDatosDeInterface.getGrupoSanguinio() );
        cbGrupoSanguineo.setItems(grupoSanguineo);
        cbGrupoSanguineo.getSelectionModel().select(unTitular.getGrupoSanguinio());
        
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
        
        ObservableList <String> observaciones = FXCollections.observableArrayList( GestorDeDatosDeInterface.getObservaciones() );
        cbObservaciones.setItems(observaciones);
        
        ObservableList <String> claseLicencia = FXCollections.observableArrayList( GestorDeDatosDeInterface.getLicenciasBasicas() );
        cbClaseLicencia.setItems(claseLicencia);
       
    }
    public LocalDate dateToLocalDate(Date fecha){
//        Instant instant = Instant.ofEpochMilli(fecha.getTime());
//        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
//        return localDateTime.toLocalDate(); 
        return (LocalDateTime.ofInstant((Instant.ofEpochMilli(fecha.getTime())), ZoneId.systemDefault())).toLocalDate();
    }
}
