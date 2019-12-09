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
        char clase = (cbTipoLicencia.getValue()==null)? 'Z' : cbTipoLicencia.getSelectionModel().getSelectedItem().toString().charAt(0);
        
        listaLicencias = gestorLicencias.buscarLicenciasExpiradas(nombre,apellido,clase,fechaDesde,fechaHasta);
        
        if(listaLicencias.isEmpty()){
            licenciaObservableList.clear();
            System.out.println("No se han encontrado Licencias");
            Alert mensajeErrores = new Alert(Alert.AlertType.INFORMATION);
            mensajeErrores.setTitle("No hay licencias");
            mensajeErrores.setHeaderText("No se han encontrado licencias para los filtros de búsqueda ingresados");
            mensajeErrores.initModality(Modality.APPLICATION_MODAL);
            mensajeErrores.show();
            
        }
        else{
            setDatosDeLicenciasEnTabla();
        }
    }
    
    private void setDatosDeLicenciasEnTabla(){
        
        licenciaObservableList.clear();
        for(Licencia licencia: listaLicencias){
                licenciaObservableList.add(licencia);
        }
        
        lvLicencias.setItems(licenciaObservableList);
        lvLicencias.setCellFactory(studentListView -> new FXMLCeldaListaLicenciasController());
        
    }

}
