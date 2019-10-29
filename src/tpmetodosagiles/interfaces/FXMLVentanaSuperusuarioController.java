/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.interfaces;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import tpmetodosagiles.entidades.Usuario;

/**
 * FXML Controller class
 *
 * @author usuario
 */
public class FXMLVentanaSuperusuarioController implements Initializable {

    @FXML
    private GridPane gpAreaDeTrabajo;
    @FXML
    private VBox vbMenu;
    
    private Usuario usuarioActual;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @FXML
    private void irAEmitirLicencia(ActionEvent event) throws IOException{
        //TODO: Cambiar a pantalla de 'Emitir Licencia' 
    }
    
    @FXML
    private void irADarDeAltaTitular(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDarAltaTitular.fxml"));
        //fxmlLoader.setController(new FXMLDarAltaTitularController(usuarioActual));
        GridPane darDeAltaTitularGridPane = (GridPane) fxmlLoader.load();
        
        try {
            gpAreaDeTrabajo.getChildren().clear();
            gpAreaDeTrabajo.getChildren().add(darDeAltaTitularGridPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void irAEditarDatosDeTitular(ActionEvent event) throws IOException{
        //TODO: Cambiar a pantalla de 'Editar Datos de Titular'
    }
    
    @FXML
    private void irAOcultarMenu(ActionEvent event) throws IOException{
        if(vbMenu.getPrefWidth() > 45){
            vbMenu.setPrefWidth(45);
        }
        else{
            vbMenu.setPrefWidth(150);
        }
    }
    
}