/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.interfaces;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import tpmetodosagiles.entidades.Titular;
import tpmetodosagiles.entidades.Usuario;
import tpmetodosagiles.gestores.GestorDeBaseDeDatos;
import tpmetodosagiles.gestores.GestorDeConfiguracion;
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
    private TextField tfNumeroDocumento;
    
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
       unTitular = gestorTitular.getTitularPorDNI(tfNumeroDocumento.getText());
       System.out.println(unTitular.toString());
    }
}
