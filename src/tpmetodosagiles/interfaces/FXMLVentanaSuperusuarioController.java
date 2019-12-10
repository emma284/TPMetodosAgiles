/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.interfaces;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import tpmetodosagiles.entidades.Usuario;
import tpmetodosagiles.gestores.GestorDeConfiguracion;

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
    @FXML
    private ImageView imgShowHideMenuPanel;
    
    Image imageShow;
    Image imageHide;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        File fileShow = new File("src\\tpmetodosagiles\\recursos\\imagenes\\imgDeInterface\\Show_32.png");
        if(fileShow.exists()){
            imageShow = new Image(fileShow.toURI().toString());
        }
        
        File fileHide = new File("src\\tpmetodosagiles\\recursos\\imagenes\\imgDeInterface\\Hide_32.png");
        if(fileHide.exists()){
            imageHide = new Image(fileHide.toURI().toString());
        }
    }
    
    @FXML
    private void irAEmitirLicencia(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLEmitirLicencia.fxml"));
        ScrollPane emitirLicenciaGridPane = (ScrollPane) fxmlLoader.load();
        
        try {
            gpAreaDeTrabajo.getChildren().clear();
            gpAreaDeTrabajo.getChildren().add(emitirLicenciaGridPane);
            //FXMLEmitirLicenciaController controller = fxmlLoader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    @FXML
    private void irADarDeAltaTitular(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDarAltaTitular.fxml"));
        GridPane darDeAltaTitularGridPane = (GridPane) fxmlLoader.load();
        
        try {
            gpAreaDeTrabajo.getChildren().clear();
            gpAreaDeTrabajo.getChildren().add(darDeAltaTitularGridPane);
            //FXMLDarAltaTitularController controller = fxmlLoader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void irAEditarDatosDeTitular(ActionEvent event) throws IOException{
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLModificarTitular.fxml"));
        GridPane editarTitularGridPane = (GridPane) fxmlLoader.load();
        
        try {
            gpAreaDeTrabajo.getChildren().clear();
            gpAreaDeTrabajo.getChildren().add(editarTitularGridPane);
            //FXMLEmitirLicenciaController controller = fxmlLoader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void irAListarLicenciasExpiradas(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLListarLicenciasExpiradas.fxml"));
        GridPane listarLicenciasGridPane = (GridPane) fxmlLoader.load();
        
        try {
            gpAreaDeTrabajo.getChildren().clear();
            gpAreaDeTrabajo.getChildren().add(listarLicenciasGridPane);
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    @FXML
    private void irAEditarCostosDeLicencias(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLCostoDeLicencias.fxml"));
        GridPane listarCostosGridPane = (GridPane) fxmlLoader.load();
        
        try {
            gpAreaDeTrabajo.getChildren().clear();
            gpAreaDeTrabajo.getChildren().add(listarCostosGridPane);
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    @FXML
    private void irAOcultarMenu(ActionEvent event) throws IOException{
        if(vbMenu.getPrefWidth() > 45){
            vbMenu.setPrefWidth(45);
            
            if(imageShow != null)
                imgShowHideMenuPanel.setImage(imageShow);
        }
        else{
            vbMenu.setPrefWidth(150);
            
            if(imageHide != null)
                imgShowHideMenuPanel.setImage(imageHide);
        }
    }
    
}
