/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.interfaces;

import java.net.URL;
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
public class FXMLCostoDeLicenciasController implements Initializable {

    GestorDeLicencias gestorLicencias;
    @FXML
    private TableView tvTablaCostos;
    @FXML
    private TextField tfCostosAdministrativos;

    
    public FXMLCostoDeLicenciasController(){
        gestorLicencias = new GestorDeLicencias();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cargarTabla();
                
        
    }
    
    @FXML
    public void cargarTabla(){
        
        TableColumn columnaTitulo = new TableColumn("Clase/Vigencia");
        TableColumn columna5Anios = new TableColumn("5 años");
        TableColumn columna4Anios = new TableColumn("4 años");
        TableColumn columna3Anios = new TableColumn("3 años");
        TableColumn columna1Anios = new TableColumn("1 año");
        
//        columna1Anios.getCellData(0);
        tvTablaCostos.getColumns().addAll(columnaTitulo,columna5Anios,columna4Anios,columna3Anios,columna1Anios);
        
//        tvTablaCostos.getColumns().add(0, 6);
//            (tvTablaCostos.getItems().get(t.getTablePosition().getRow())).setEmail(t.getNewValue());
        
    }
    
    @FXML
    public void modificarTabla(){
        
        
        
    }

}
