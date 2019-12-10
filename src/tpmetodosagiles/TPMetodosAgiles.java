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
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tpmetodosagiles.entidades.Usuario;
import tpmetodosagiles.gestores.GestorDeBaseDeDatos;
import tpmetodosagiles.gestores.GestorDeConfiguracion;
import tpmetodosagiles.gestores.util.MySession;
import tpmetodosagiles.gestores.util.MySessionDBCiudad;
import tpmetodosagiles.interfaces.FXMLVentanaSuperusuarioController;


public class TPMetodosAgiles extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaces/FXMLVentanaSuperusuario.fxml"));;
        
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Sistema de registro vehicular - Santa Fe Ciudad");
        stage.setOnHidden(event -> {MySession.close(); MySessionDBCiudad.close();});
        stage.show();
        
        GestorDeBaseDeDatos gbd = new GestorDeBaseDeDatos();
        try{
            Usuario usr = gbd.getUsuarioPorId(1);
            if(usr == null){
                //No se encontró el usuario en la base de datos
                Alert mensajeErrores = new Alert(Alert.AlertType.ERROR);
                mensajeErrores.setTitle("No se encontró el usuario");
                mensajeErrores.setHeaderText("No se pudo encontrar el usuario en la base de datos.");
                mensajeErrores.setContentText("-Id de usuario: " + 1 );
                mensajeErrores.initModality(Modality.APPLICATION_MODAL);
                mensajeErrores.show();
                stage.close();
            }
            else{
                GestorDeConfiguracion.setUsuarioActual(usr);
                GestorDeConfiguracion.setVentanaActual(stage);
                //FXMLVentanaSuperusuarioController controller = loader.getController();
                //controller.setConfiguracion(cfg);
            }
        }
        catch(Exception e){
            //TODO: retornar a la ventana de inicio de sesión en vez de cerrar el programa
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