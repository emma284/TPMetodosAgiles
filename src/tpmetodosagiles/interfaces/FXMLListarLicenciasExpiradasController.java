/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.interfaces;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import tpmetodosagiles.entidades.Licencia;
import tpmetodosagiles.entidades.Titular;
import tpmetodosagiles.gestores.GestorDeDatosDeInterface;
import tpmetodosagiles.gestores.GestorDeLicencias;
import tpmetodosagiles.gestores.GestorDeTitulares;
//import tpmetodosagiles.layouts.TextFieldSoloLetras;

/**
 * FXML Controller class
 *
 * @author usuario
 */
public class FXMLListarLicenciasExpiradasController implements Initializable {

    GestorDeLicencias gestorLicencias;
    @FXML
    private TextField tfNombreTitular;
    @FXML
    private TextField tfApellidoTitular;
    @FXML
    private DatePicker dpFechaDesde;
    @FXML
    private DatePicker dpFechaHasta;
    @FXML
    private ListView lvLicencias;
    @FXML
    private ComboBox cbTipoLicencia;

    private ObservableList<Licencia> licenciaObservableList;    
    private List<Licencia> listaLicencias;
    
    public FXMLListarLicenciasExpiradasController(){
        gestorLicencias = new GestorDeLicencias();
        licenciaObservableList = FXCollections.observableArrayList();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList <String> tiposLicencias = FXCollections.observableArrayList(GestorDeDatosDeInterface.getLicencias());
        cbTipoLicencia.setItems(tiposLicencias);
                

        
    }
        
    public void buscarLicencias(){
        
        String nombre = tfNombreTitular.getText().toString();
        String apellido = tfApellidoTitular.getText().toString();
        LocalDate fechaDesde = dpFechaDesde.getValue();
        LocalDate fechaHasta = dpFechaHasta.getValue();
        String clase = cbTipoLicencia.getSelectionModel().getSelectedItem().toString();
        listaLicencias = gestorLicencias.buscarLicenciasExpiradas(nombre,apellido,clase,fechaDesde,fechaHasta);
        if(listaLicencias == null){
            System.out.println("No se han encontrado Licencias");
        }else{
            //CONTINUAR
            setDatosDeLicenciasEnTabla();
        }
    }
    
    private void setDatosDeLicenciasEnTabla(){
        
        //QUEDE ACÁ
        licenciaObservableList.clear();
        for(Licencia licencia: listaLicencias){
            if(licencia.getFechaVencimiento().isAfter(LocalDate.now()) || licencia.getFechaVencimiento().isEqual(LocalDate.now())){
                licenciaObservableList.add(licencia);
            }
        }
        
        ObservableList <String> claseLicencia = FXCollections.observableArrayList( GestorDeDatosDeInterface.getLicencias());
        for(Licencia licencia : licenciaObservableList){
            claseLicencia.remove(String.valueOf(licencia.getClaseLicencia()));
        }
        
        lvLicencias.setItems(licenciaObservableList);
        lvLicencias.setCellFactory(studentListView -> new FXMLCeldaListaLicenciasController());
        
    }
    private boolean datosValidos(){
        boolean datosCorrectos = true;
        StringBuffer errores = new StringBuffer("");

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
}
