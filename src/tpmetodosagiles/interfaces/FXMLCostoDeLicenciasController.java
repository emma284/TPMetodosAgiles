/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.interfaces;

import java.net.URL;
import java.util.ArrayList;
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
    private TextField tfCostosAdministrativos;
    @FXML
    private TextField tfA5Anios;
    @FXML
    private TextField tfB5Anios;
    @FXML
    private TextField tfC5Anios;
    @FXML
    private TextField tfD5Anios;
    @FXML
    private TextField tfE5Anios;
    @FXML
    private TextField tfF5Anios;
    @FXML
    private TextField tfG5Anios;
    @FXML
    private TextField tfA4Anios;
    @FXML
    private TextField tfB4Anios;
    @FXML
    private TextField tfC4Anios;
    @FXML
    private TextField tfD4Anios;
    @FXML
    private TextField tfE4Anios;
    @FXML
    private TextField tfF4Anios;
    @FXML
    private TextField tfG4Anios;
    @FXML
    private TextField tfA3Anios;
    @FXML
    private TextField tfB3Anios;
    @FXML
    private TextField tfC3Anios;
    @FXML
    private TextField tfD3Anios;
    @FXML
    private TextField tfE3Anios;
    @FXML
    private TextField tfF3Anios;
    @FXML
    private TextField tfG3Anios;
    @FXML
    private TextField tfA1Anios;
    @FXML
    private TextField tfB1Anios;
    @FXML
    private TextField tfC1Anios;
    @FXML
    private TextField tfD1Anios;
    @FXML
    private TextField tfE1Anios;
    @FXML
    private TextField tfF1Anios;
    @FXML
    private TextField tfG1Anios;

    private List<List<Integer>> listaDeCostos;
    private Integer costoAdministrativo;
    
    public FXMLCostoDeLicenciasController(){
        gestorLicencias = new GestorDeLicencias();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cargarTabla();
                
        
    }
    
    @FXML
    public void modificarTabla(){
        
        if (!this.validarDatos())
            return;
        
        List<Integer> valoresLicenciaA = new ArrayList<Integer>();
        List<Integer> valoresLicenciaB = new ArrayList<Integer>();
        List<Integer> valoresLicenciaC = new ArrayList<Integer>();
        List<Integer> valoresLicenciaD = new ArrayList<Integer>();
        List<Integer> valoresLicenciaE = new ArrayList<Integer>();
        List<Integer> valoresLicenciaF = new ArrayList<Integer>();
        List<Integer> valoresLicenciaG = new ArrayList<Integer>();
        
        valoresLicenciaA.add(Integer.parseInt(tfA5Anios.getText()));
        valoresLicenciaA.add(Integer.parseInt(tfA4Anios.getText()));
        valoresLicenciaA.add(Integer.parseInt(tfA3Anios.getText()));
        valoresLicenciaA.add(Integer.parseInt(tfA1Anios.getText()));
        
        valoresLicenciaB.add(Integer.parseInt(tfB5Anios.getText()));
        valoresLicenciaB.add(Integer.parseInt(tfB4Anios.getText()));
        valoresLicenciaB.add(Integer.parseInt(tfB3Anios.getText()));
        valoresLicenciaB.add(Integer.parseInt(tfB1Anios.getText()));
        
        valoresLicenciaC.add(Integer.parseInt(tfC5Anios.getText()));
        valoresLicenciaC.add(Integer.parseInt(tfC4Anios.getText()));
        valoresLicenciaC.add(Integer.parseInt(tfC3Anios.getText()));
        valoresLicenciaC.add(Integer.parseInt(tfC1Anios.getText()));
        
        valoresLicenciaD.add(Integer.parseInt(tfD5Anios.getText()));
        valoresLicenciaD.add(Integer.parseInt(tfD4Anios.getText()));
        valoresLicenciaD.add(Integer.parseInt(tfD3Anios.getText()));
        valoresLicenciaD.add(Integer.parseInt(tfD1Anios.getText()));
        
        valoresLicenciaE.add(Integer.parseInt(tfE5Anios.getText()));
        valoresLicenciaE.add(Integer.parseInt(tfE4Anios.getText()));
        valoresLicenciaE.add(Integer.parseInt(tfE3Anios.getText()));
        valoresLicenciaE.add(Integer.parseInt(tfE1Anios.getText()));
        
        valoresLicenciaF.add(Integer.parseInt(tfF5Anios.getText()));
        valoresLicenciaF.add(Integer.parseInt(tfF4Anios.getText()));
        valoresLicenciaF.add(Integer.parseInt(tfF3Anios.getText()));
        valoresLicenciaF.add(Integer.parseInt(tfF1Anios.getText()));
        
        valoresLicenciaG.add(Integer.parseInt(tfG5Anios.getText()));
        valoresLicenciaG.add(Integer.parseInt(tfG4Anios.getText()));
        valoresLicenciaG.add(Integer.parseInt(tfG3Anios.getText()));
        valoresLicenciaG.add(Integer.parseInt(tfG1Anios.getText()));
        
        ArrayList<List<Integer>> valoresLicencia = new ArrayList<List<Integer>>();
        gestorLicencias.guardarValoresLicencia(valoresLicencia);
        gestorLicencias.guardarCostoAdministrativo(Integer.parseInt(tfG1Anios.getText()));
        
    }
    
    @FXML
    public void cargarTabla(){
        
        listaDeCostos = gestorLicencias.obtenerCostos();
        costoAdministrativo = gestorLicencias.obtenerCostoAdministrativo();
        tfCostosAdministrativos.setText(costoAdministrativo.toString());
        
        tfA5Anios.setText(listaDeCostos.get(0).get(0).toString());
        tfB5Anios.setText(listaDeCostos.get(0).get(1).toString());
        tfC5Anios.setText(listaDeCostos.get(0).get(2).toString());
        tfD5Anios.setText(listaDeCostos.get(0).get(3).toString());
        tfE5Anios.setText(listaDeCostos.get(0).get(4).toString());
        tfF5Anios.setText(listaDeCostos.get(0).get(5).toString());
        tfG5Anios.setText(listaDeCostos.get(0).get(6).toString());
        
        tfA4Anios.setText(listaDeCostos.get(1).get(0).toString());
        tfB4Anios.setText(listaDeCostos.get(1).get(1).toString());
        tfC4Anios.setText(listaDeCostos.get(1).get(2).toString());
        tfD4Anios.setText(listaDeCostos.get(1).get(3).toString());
        tfE4Anios.setText(listaDeCostos.get(1).get(4).toString());
        tfF4Anios.setText(listaDeCostos.get(1).get(5).toString());
        tfG4Anios.setText(listaDeCostos.get(1).get(6).toString());
        
        tfA3Anios.setText(listaDeCostos.get(2).get(0).toString());
        tfB3Anios.setText(listaDeCostos.get(2).get(1).toString());
        tfC3Anios.setText(listaDeCostos.get(2).get(2).toString());
        tfD3Anios.setText(listaDeCostos.get(2).get(3).toString());
        tfE3Anios.setText(listaDeCostos.get(2).get(4).toString());
        tfF3Anios.setText(listaDeCostos.get(2).get(5).toString());
        tfG3Anios.setText(listaDeCostos.get(2).get(6).toString());
        
        tfA1Anios.setText(listaDeCostos.get(3).get(0).toString());
        tfB1Anios.setText(listaDeCostos.get(3).get(1).toString());
        tfC1Anios.setText(listaDeCostos.get(3).get(2).toString());
        tfD1Anios.setText(listaDeCostos.get(3).get(3).toString());
        tfE1Anios.setText(listaDeCostos.get(3).get(4).toString());
        tfF1Anios.setText(listaDeCostos.get(3).get(5).toString());
        tfG1Anios.setText(listaDeCostos.get(3).get(6).toString());

    }
    
    private Boolean validarDatos(){
        
        boolean datosCorrectos = true;
        StringBuffer errores = new StringBuffer("");
        
        if (tfCostosAdministrativos.getText().isEmpty()) datosCorrectos = false;
        
        if (tfA5Anios.getText().isEmpty()) datosCorrectos = false;
        if (tfB5Anios.getText().isEmpty()) datosCorrectos = false;
        if (tfC5Anios.getText().isEmpty()) datosCorrectos = false;
        if (tfD5Anios.getText().isEmpty()) datosCorrectos = false;
        if (tfE5Anios.getText().isEmpty()) datosCorrectos = false;
        if (tfF5Anios.getText().isEmpty()) datosCorrectos = false;
        if (tfG5Anios.getText().isEmpty()) datosCorrectos = false;

        if (tfA4Anios.getText().isEmpty()) datosCorrectos = false;
        if (tfB4Anios.getText().isEmpty()) datosCorrectos = false;
        if (tfC4Anios.getText().isEmpty()) datosCorrectos = false;
        if (tfD4Anios.getText().isEmpty()) datosCorrectos = false;
        if (tfE4Anios.getText().isEmpty()) datosCorrectos = false;
        if (tfF4Anios.getText().isEmpty()) datosCorrectos = false;
        if (tfG4Anios.getText().isEmpty()) datosCorrectos = false;

        if (tfA3Anios.getText().isEmpty()) datosCorrectos = false;
        if (tfB3Anios.getText().isEmpty()) datosCorrectos = false;
        if (tfC3Anios.getText().isEmpty()) datosCorrectos = false;
        if (tfD3Anios.getText().isEmpty()) datosCorrectos = false;
        if (tfE3Anios.getText().isEmpty()) datosCorrectos = false;
        if (tfF3Anios.getText().isEmpty()) datosCorrectos = false;
        if (tfG3Anios.getText().isEmpty()) datosCorrectos = false;

        if (tfA1Anios.getText().isEmpty()) datosCorrectos = false;
        if (tfB1Anios.getText().isEmpty()) datosCorrectos = false;
        if (tfC1Anios.getText().isEmpty()) datosCorrectos = false;
        if (tfD1Anios.getText().isEmpty()) datosCorrectos = false;
        if (tfE1Anios.getText().isEmpty()) datosCorrectos = false;
        if (tfF1Anios.getText().isEmpty()) datosCorrectos = false;
        if (tfG1Anios.getText().isEmpty()) datosCorrectos = false;

        try{
            Integer.parseInt(tfA5Anios.getText());
            Integer.parseInt(tfB5Anios.getText());
            Integer.parseInt(tfC5Anios.getText());
            Integer.parseInt(tfD5Anios.getText());
            Integer.parseInt(tfE5Anios.getText());
            Integer.parseInt(tfF5Anios.getText());
            Integer.parseInt(tfG5Anios.getText());
            
            Integer.parseInt(tfA4Anios.getText());
            Integer.parseInt(tfB4Anios.getText());
            Integer.parseInt(tfC4Anios.getText());
            Integer.parseInt(tfD4Anios.getText());
            Integer.parseInt(tfE4Anios.getText());
            Integer.parseInt(tfF4Anios.getText());
            Integer.parseInt(tfG4Anios.getText());
            
            Integer.parseInt(tfA3Anios.getText());
            Integer.parseInt(tfB3Anios.getText());
            Integer.parseInt(tfC3Anios.getText());
            Integer.parseInt(tfD3Anios.getText());
            Integer.parseInt(tfE3Anios.getText());
            Integer.parseInt(tfF3Anios.getText());
            Integer.parseInt(tfG3Anios.getText());
           
            Integer.parseInt(tfA1Anios.getText());
            Integer.parseInt(tfB1Anios.getText());
            Integer.parseInt(tfC1Anios.getText());
            Integer.parseInt(tfD1Anios.getText());
            Integer.parseInt(tfE1Anios.getText());
            Integer.parseInt(tfF1Anios.getText());
            Integer.parseInt(tfG1Anios.getText());
            
        }
        catch(NumberFormatException fne){
            datosCorrectos = false;
        }
        if (!datosCorrectos){
            Alert mensajeErrores = new Alert(Alert.AlertType.INFORMATION);
            mensajeErrores.setTitle("Datos faltantes/incorrectos");
            mensajeErrores.initModality(Modality.APPLICATION_MODAL);
            mensajeErrores.show();
        }
        
        return datosCorrectos;
    }   
}
