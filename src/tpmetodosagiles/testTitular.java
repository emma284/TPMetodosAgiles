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
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import tpmetodosagiles.entidades.Licencia;
import tpmetodosagiles.entidades.Titular;
import tpmetodosagiles.gestores.GestorDeDatosDeInterface;
import tpmetodosagiles.gestores.GestorDeTitulares;
import tpmetodosagiles.gestores.util.MySession;


public class testTitular extends Application {
    
    private TextField idTitular, numeroDocumento, apellido, 
            nombre, domicilio;
    private DatePicker fechaNacimiento, fechaEntradaSistema, fechaEmisionLicenciaTipoB;
    private ComboBox tipoDocumento, grupoSanguineo, sexo;
    private CheckBox esDonante;
    private Button savebtn, consulbtn;
    
    @Override
    public void start(Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Group root = new Group();
        Scene scene = new Scene(root, 400, 700);
        
        
        idTitular = new TextField();
        idTitular.setTooltip(new Tooltip("Ingrese ID de Titular"));
        idTitular.setFont(Font.font("SanSerif", 15));
        idTitular.setPromptText("Titular ID");
        idTitular.setMaxWidth(200);

        tipoDocumento = new ComboBox<String>();
        tipoDocumento.setTooltip(new Tooltip("Ingrese Tipo de documento"));
        tipoDocumento.setTooltip(new Tooltip("Ingrese Tipo de documento"));
        tipoDocumento.setPromptText("Tipo de Documento");
        tipoDocumento.setItems(FXCollections.observableArrayList( GestorDeDatosDeInterface.getTipoDeDocumento() ));
        tipoDocumento.setMaxWidth(200);
        
        grupoSanguineo = new ComboBox<String>();
        grupoSanguineo.setTooltip(new Tooltip("Ingrese Tipo de documento"));
        grupoSanguineo.setPromptText("Grupo Sanguineo");
        grupoSanguineo.setItems(FXCollections.observableArrayList( GestorDeDatosDeInterface.getGrupoSanguinio()));
        grupoSanguineo.setMaxWidth(200);
        
        sexo = new ComboBox<String>();
        sexo.setTooltip(new Tooltip("Ingrese Tipo de documento"));
        sexo.setPromptText("Sexo");
        sexo.setItems(FXCollections.observableArrayList( GestorDeDatosDeInterface.getSexos()));
        sexo.setMaxWidth(200);
        
        esDonante = new CheckBox();

        numeroDocumento = new TextField();
        numeroDocumento.setTooltip(new Tooltip("Ingrese Numero de Documento"));
        numeroDocumento.setFont(Font.font("SanSerif", 15));
        numeroDocumento.setPromptText("Numero de Documento");
        numeroDocumento.setMaxWidth(200);

        apellido = new TextField();
        apellido.setTooltip(new Tooltip("Ingrese Apellido"));
        apellido.setFont(Font.font("SanSerif", 15));
        apellido.setPromptText("Apellido");
        apellido.setMaxWidth(200);
        
        nombre = new TextField();
        nombre.setTooltip(new Tooltip("Ingrese Nombre"));
        nombre.setFont(Font.font("SanSerif", 15));
        nombre.setPromptText("Nombre");
        nombre.setMaxWidth(200);

        domicilio = new TextField();
        domicilio.setTooltip(new Tooltip("Ingrese Domicilio"));
        domicilio.setFont(Font.font("SanSerif", 15));
        domicilio.setPromptText("Domicilio");
        domicilio.setMaxWidth(200);
        
        fechaNacimiento = new DatePicker();
        fechaNacimiento.setTooltip(new Tooltip("Ingrese fecha de emision"));		
        fechaNacimiento.setPromptText("Fecha de nacimiento");
        fechaNacimiento.setMaxWidth(200);
        fechaNacimiento.setStyle("-fx-font-size:15");
        
        fechaEntradaSistema = new DatePicker();
        fechaEntradaSistema.setTooltip(new Tooltip("Ingrese fecha de emision"));		
        fechaEntradaSistema.setPromptText("Fecha de entda al sistema");
        fechaEntradaSistema.setMaxWidth(200);
        fechaEntradaSistema.setStyle("-fx-font-size:15");
        
        fechaEmisionLicenciaTipoB = new DatePicker();
        fechaEmisionLicenciaTipoB.setTooltip(new Tooltip("Ingrese fecha de emision"));		
        fechaEmisionLicenciaTipoB.setPromptText("Fecha de emision licencia tipo b");
        fechaEmisionLicenciaTipoB.setMaxWidth(200);
        fechaEmisionLicenciaTipoB.setStyle("-fx-font-size:15");

//        Hibernate Configuration
//        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
//        SessionFactory sf = cfg.buildSessionFactory();

        Titular unTitular = new Titular();

        savebtn = new Button("Save");
        savebtn.setTooltip(new Tooltip("Guardar Datos de Licencia"));
        savebtn.setFont(Font.font("SanSerif", 15));
        savebtn.setOnAction(e ->{
                unTitular.setIdTitular(Integer.parseInt(idTitular.getText()));
                unTitular.setApellido(apellido.getText());
                unTitular.setNombre(nombre.getText());
                unTitular.setDomicilio(domicilio.getText());
                unTitular.setSexo(sexo.getValue().toString().charAt(0));
                unTitular.setTipoDeDocumento(tipoDocumento.getValue().toString());
                unTitular.setGrupoSanguinio(grupoSanguineo.getValue().toString());
                unTitular.setNumeroDocumento(Integer.parseInt(numeroDocumento.getText()));
                try {
                    Date fechaN = new SimpleDateFormat("dd/MM/yyyy").parse(fechaNacimiento.getEditor().getText());
                    unTitular.setFechaNacimiento(fechaN);
                } catch (ParseException ex) {
                    Logger.getLogger(testTitular.class.getName()).log(Level.SEVERE, null, ex);
                };


                Session session = MySession.get();
                session.beginTransaction();
                session.save(unTitular);
                session.getTransaction().commit();

                clearFields();

        });
        
        consulbtn = new Button("Consulta");
        consulbtn.setTooltip(new Tooltip("Guardar Datos de Licencia"));
        consulbtn.setFont(Font.font("SanSerif", 15));
        consulbtn.setOnAction(e ->{
            Session session = MySession.get();
            session.beginTransaction();
            GestorDeTitulares gestorTitular = new GestorDeTitulares();
            Titular otroTitular = gestorTitular.getTitularPorDNI("DNI","12345678");
            System.out.println(otroTitular.toString());
        });

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(idTitular, tipoDocumento, numeroDocumento,
                apellido, nombre, fechaNacimiento, domicilio, sexo,
                grupoSanguineo, fechaEntradaSistema, fechaEmisionLicenciaTipoB,
                esDonante, savebtn, consulbtn);
        vbox.setPadding(new Insets(10));		
        root.getChildren().add(vbox);
        
        stage.setScene(scene);
        stage.show();      
    }
    
    
	private void clearFields() {
             
            idTitular.clear();
            tipoDocumento.setValue(null);
            numeroDocumento.clear();
            apellido.clear();
            nombre.clear();
            fechaNacimiento.getEditor().setText(null);	
            domicilio.clear();
            sexo.setValue(null);
            grupoSanguineo.setValue(null);
            fechaEntradaSistema.getEditor().setText(null);
            fechaEmisionLicenciaTipoB.getEditor().setText(null);
            esDonante.setSelected(false);
	}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
