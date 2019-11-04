/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.interfaces;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import tpmetodosagiles.entidades.Usuario;
import tpmetodosagiles.gestores.GestorDeConfiguracion;

/**
 * FXML Controller class
 *
 * @author Gino
 */
public class FXMLEmitirLicenciaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private GestorDeConfiguracion configuracion;

    public void setConfiguracion(GestorDeConfiguracion configuracion) {
        this.configuracion = configuracion;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO setDatosTitular()
        // Metodo que busca en la base un titular por DNI ingresado y si existe
        // carga los datos y licencias vigentes en la interfaz 
    }    
    
}
