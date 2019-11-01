/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import tpmetodosagiles.entidades.Licencia;


public class testLicencia extends Application {
    
    private TextField idLicencia, claseLicencia, numeroDeRenovacion, numeroDeCopia;
    private DatePicker date;
    private Button savebtn;
    
    @Override
    public void start(Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Group root = new Group();
        Scene scene = new Scene(root, 400, 300);
        
        
        idLicencia = new TextField();
        idLicencia.setTooltip(new Tooltip("Ingrese ID de Licencia"));
        idLicencia.setFont(Font.font("SanSerif", 15));
        idLicencia.setPromptText("Licencia ID");
        idLicencia.setMaxWidth(200);

        claseLicencia = new TextField();
        claseLicencia.setTooltip(new Tooltip("Ingrese Clase de Licencia"));
        claseLicencia.setFont(Font.font("SanSerif", 15));
        claseLicencia.setPromptText("Clase");
        claseLicencia.setMaxWidth(200);

        numeroDeRenovacion = new TextField();
        numeroDeRenovacion.setTooltip(new Tooltip("Ingrese Numero de Renovacion"));
        numeroDeRenovacion.setFont(Font.font("SanSerif", 15));
        numeroDeRenovacion.setPromptText("Numero de Renovacion");
        numeroDeRenovacion.setMaxWidth(200);

        numeroDeCopia = new TextField();
        numeroDeCopia.setTooltip(new Tooltip("Ingrese Numero de Copia"));
        numeroDeCopia.setFont(Font.font("SanSerif", 15));
        numeroDeCopia.setPromptText("Numero de Copia");
        numeroDeCopia.setMaxWidth(200);

        date = new DatePicker();
        date.setTooltip(new Tooltip("Ingrese fecha de emision"));		
        date.setPromptText("Fecha de emision");
        date.setMaxWidth(200);
        date.setStyle("-fx-font-size:15");

        //Hibernate Configuration
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sf = cfg.buildSessionFactory();

        Licencia licencia = new Licencia();

        savebtn = new Button("Save");
        savebtn.setTooltip(new Tooltip("Guardar Datos de Licencia"));
        savebtn.setFont(Font.font("SanSerif", 15));
        savebtn.setOnAction(e ->{
                licencia.setIdLicencia(Integer.parseInt(idLicencia.getText()));
                licencia.setClaseLicencia((claseLicencia.getText()).charAt(0));
                licencia.setNumeroDeRenovacion(Integer.parseInt(numeroDeRenovacion.getText()));
                try {
                    Date fechaEmision = new SimpleDateFormat("dd/MM/yyyy").parse(date.getEditor().getText());
                    licencia.setFechaEmision(fechaEmision);
                } catch (ParseException ex) {
                    Logger.getLogger(testLicencia.class.getName()).log(Level.SEVERE, null, ex);
                }
;

                licencia.setNumeroDeCopia(Integer.parseInt(numeroDeCopia.getText()));

                Session session = sf.openSession();
                session.beginTransaction();
                session.save(licencia);
                session.getTransaction().commit();
                session.close();

                clearFields();

        });

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(idLicencia, claseLicencia, date, numeroDeRenovacion, numeroDeCopia, savebtn);
        vbox.setPadding(new Insets(10));		
        root.getChildren().add(vbox);
        
        stage.setScene(scene);
        stage.show();      
    }
    
    
	private void clearFields() {
		// TODO Auto-generated method stub
		idLicencia.clear();
		claseLicencia.clear();
		numeroDeRenovacion.clear();
		numeroDeCopia.clear();
		date.getEditor().setText(null);	
		date.setValue(null);
	}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
