/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tpmetodosagiles.entidades.Usuario;
import tpmetodosagiles.gestores.GestorDeBaseDeDatos;
import tpmetodosagiles.gestores.GestorDeConfiguracion;
import tpmetodosagiles.interfaces.FXMLVentanaSuperusuarioController;


public class TPMetodosAgiles extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaces/FXMLVentanaSuperusuario.fxml"));;
        
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        GestorDeBaseDeDatos gbd = new GestorDeBaseDeDatos();
        try{
            Usuario usr = gbd.getUsuarioPorId(1);
            GestorDeConfiguracion.setUsuarioActual(usr);
            GestorDeConfiguracion.setVentanaActual(stage);
            //FXMLVentanaSuperusuarioController controller = loader.getController();
            //controller.setConfiguracion(cfg);
        }
        catch(Exception e){
            //TODO: retornar a la ventana de inicio de sesión
            System.out.println("Unable to connect to server sir");
            stage.close();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}