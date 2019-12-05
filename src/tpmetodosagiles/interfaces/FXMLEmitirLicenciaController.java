/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.interfaces;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import tpmetodosagiles.entidades.Licencia;
import tpmetodosagiles.entidades.Titular;
import tpmetodosagiles.entidades.Usuario;
import tpmetodosagiles.gestores.GestorDeBaseDeDatos;
import tpmetodosagiles.gestores.GestorDeConfiguracion;
import tpmetodosagiles.gestores.GestorDeDatosDeInterface;
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
    private ComboBox cbTipoDocumento;
    @FXML
    private TextField tfNumeroDocumento;
    @FXML
    private TextField tfNombres;
    @FXML
    private TextField tfApellidos;
    @FXML
    private DatePicker dpFechaNacimiento;
    @FXML
    private ComboBox cbSexo;
    @FXML
    private ComboBox cbCalleTitular;
    @FXML
    private TextField tfNroAltura;
    @FXML
    private TextField tfNroInterno;
    @FXML
    private TextField tfPiso;
    @FXML
    private ComboBox cbEsDonante;
    @FXML
    private ComboBox cbGrupoSanguineo;
    @FXML
    private ComboBox cbObservaciones;
    @FXML
    private ComboBox cbClaseLicencia;
    
    @FXML
    private ListView<Licencia> lvLicencias;

    private ObservableList<Licencia> licenciaObservableList;
    
    private Titular unTitular;
    private Licencia unaLicencia;
    
    public FXMLEmitirLicenciaController(){
        gestorTitular = new GestorDeTitulares();
        licenciaObservableList = FXCollections.observableArrayList();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList <String> tiposDocumentos = FXCollections.observableArrayList( GestorDeDatosDeInterface.getTipoDeDocumento() );
        cbTipoDocumento.setItems(tiposDocumentos);
        //cbTipoDocumento.getSelectionModel().select(unTitular.getTipoDeDocumento());
        
        
    }   
    
    public void setDatosTitular(){
        tfNombres.setText(unTitular.getNombre());
        tfApellidos.setText(unTitular.getApellido());

        dpFechaNacimiento.setValue(unTitular.getFechaNacimiento());

        String direccion = unTitular.getDomicilio();
        String[] campos = direccion.split("%");
        cbCalleTitular.getSelectionModel().select(campos[0]);
        tfNroAltura.setText(campos[1]);
        tfNroInterno.setText(campos[2]);
        tfPiso.setText(campos[3]);
        
        cbObservaciones.getSelectionModel().select(unTitular.getObservaciones());
        //Listas de los desplegables
       
        
        ObservableList <String> grupoSanguineo = FXCollections.observableArrayList( GestorDeDatosDeInterface.getGrupoSanguinio() );
        cbGrupoSanguineo.setItems(grupoSanguineo);
        cbGrupoSanguineo.getSelectionModel().select(unTitular.getGrupoSanguinio());
        
        ObservableList <String> esDonante = FXCollections.observableArrayList( GestorDeDatosDeInterface.getEsDonante() );
        cbEsDonante.setItems(esDonante);
        cbEsDonante.getSelectionModel().select(unTitular.getEsDonante() ? "Sí" : "No");
        
        ObservableList <String> sexos = FXCollections.observableArrayList( GestorDeDatosDeInterface.getSexos() );
        cbSexo.setItems(sexos);
        switch(unTitular.getSexo()){
            case 'm':
            case 'M':
                cbSexo.getSelectionModel().select("Masculino");
                break;
            case 'f':
            case 'F':
                cbSexo.getSelectionModel().select("Femenino");
                break;
        }
        
        ObservableList <String> calles = FXCollections.observableArrayList( GestorDeDatosDeInterface.getCalles() );
        cbCalleTitular.setItems(calles);
        
        ObservableList <String> observaciones = FXCollections.observableArrayList( GestorDeDatosDeInterface.getObservaciones() );
        cbObservaciones.setItems(observaciones);
        licenciaObservableList.clear();
        for(Licencia licencia: unTitular.getLicencias()){
            if(licencia.getFechaVencimiento().isAfter(LocalDate.now()) || licencia.getFechaVencimiento().isEqual(LocalDate.now())){
                licenciaObservableList.add(licencia);
            }
        }
        
        ObservableList <String> claseLicencia = FXCollections.observableArrayList( GestorDeDatosDeInterface.getLicencias());
        for(Licencia licencia : licenciaObservableList){
            claseLicencia.remove(String.valueOf(licencia.getClaseLicencia()));
        }
        cbClaseLicencia.setItems(claseLicencia);
        
        lvLicencias.setItems(licenciaObservableList);
        lvLicencias.setCellFactory(studentListView -> new FXMLCeldaListaLicenciasController());
    }
    
    
    @FXML
    public void onEnter(ActionEvent ae){
        unTitular = gestorTitular.getTitularPorDNI(cbTipoDocumento.getSelectionModel().getSelectedItem().toString(), tfNumeroDocumento.getText());
        if(unTitular == null){
            System.out.println("Usuario no encontrado");
        }else{
            setDatosTitular();
        }
        //borrar desde acá
        /*unTitular = new Titular();
        unTitular.setNombre("emmanuel");
        unTitular.setApellido("ábrego");
        unTitular.setFechaNacimiento(new Date());
        unTitular.setEsDonante(true);
        unTitular.setSexo('m');
        unTitular.setTipoDeDocumento("DNI");
        unTitular.setGrupoSanguinio("A+");
       
        System.out.println(unTitular.getNombre());*///hasta acá      
    }
    
    
    @FXML
    public void emitirLicenciaOnClick(){
        //TODO Settear fecha de emisión de primera licencia de clase B
        gestorTitular.emitirLicencia(unTitular,cbClaseLicencia.getSelectionModel().getSelectedItem().toString().charAt(0));
//        unaLicencia.setClaseLicencia(cbClaseLicencia.getSelectionModel().getSelectedItem().toString().charAt(0));
//        unaLicencia.setFechaEmision(LocalDate.MIN);
    }
    
    @FXML
    public void renovarLicenciaOnClick(){
        gestorTitular.renovarLicencia(lvLicencias.getSelectionModel().getSelectedItem());
    }
}
