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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import tpmetodosagiles.gestores.GestorDeLicencias;
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

    private List<List<Double>> listaDeCostos;
    private Double costoAdministrativo;
    
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
        
        List<Double> valoresLicenciaA = new ArrayList<Double>();
        List<Double> valoresLicenciaB = new ArrayList<Double>();
        List<Double> valoresLicenciaC = new ArrayList<Double>();
        List<Double> valoresLicenciaD = new ArrayList<Double>();
        List<Double> valoresLicenciaE = new ArrayList<Double>();
        List<Double> valoresLicenciaF = new ArrayList<Double>();
        List<Double> valoresLicenciaG = new ArrayList<Double>();
        
        valoresLicenciaA.add(Double.parseDouble(tfA5Anios.getText()));
        valoresLicenciaA.add(Double.parseDouble(tfA4Anios.getText()));
        valoresLicenciaA.add(Double.parseDouble(tfA3Anios.getText()));
        valoresLicenciaA.add(Double.parseDouble(tfA1Anios.getText()));
        
        valoresLicenciaB.add(Double.parseDouble(tfB5Anios.getText()));
        valoresLicenciaB.add(Double.parseDouble(tfB4Anios.getText()));
        valoresLicenciaB.add(Double.parseDouble(tfB3Anios.getText()));
        valoresLicenciaB.add(Double.parseDouble(tfB1Anios.getText()));
        
        valoresLicenciaC.add(Double.parseDouble(tfC5Anios.getText()));
        valoresLicenciaC.add(Double.parseDouble(tfC4Anios.getText()));
        valoresLicenciaC.add(Double.parseDouble(tfC3Anios.getText()));
        valoresLicenciaC.add(Double.parseDouble(tfC1Anios.getText()));
        
        valoresLicenciaD.add(Double.parseDouble(tfD5Anios.getText()));
        valoresLicenciaD.add(Double.parseDouble(tfD4Anios.getText()));
        valoresLicenciaD.add(Double.parseDouble(tfD3Anios.getText()));
        valoresLicenciaD.add(Double.parseDouble(tfD1Anios.getText()));
        
        valoresLicenciaE.add(Double.parseDouble(tfE5Anios.getText()));
        valoresLicenciaE.add(Double.parseDouble(tfE4Anios.getText()));
        valoresLicenciaE.add(Double.parseDouble(tfE3Anios.getText()));
        valoresLicenciaE.add(Double.parseDouble(tfE1Anios.getText()));
        
        valoresLicenciaF.add(Double.parseDouble(tfF5Anios.getText()));
        valoresLicenciaF.add(Double.parseDouble(tfF4Anios.getText()));
        valoresLicenciaF.add(Double.parseDouble(tfF3Anios.getText()));
        valoresLicenciaF.add(Double.parseDouble(tfF1Anios.getText()));
        
        valoresLicenciaG.add(Double.parseDouble(tfG5Anios.getText()));
        valoresLicenciaG.add(Double.parseDouble(tfG4Anios.getText()));
        valoresLicenciaG.add(Double.parseDouble(tfG3Anios.getText()));
        valoresLicenciaG.add(Double.parseDouble(tfG1Anios.getText()));
        
        ArrayList<List<Double>> valoresLicencia = new ArrayList<List<Double>>();
        valoresLicencia.add(valoresLicenciaA);
        valoresLicencia.add(valoresLicenciaB);
        valoresLicencia.add(valoresLicenciaC);
        valoresLicencia.add(valoresLicenciaD);
        valoresLicencia.add(valoresLicenciaE);
        valoresLicencia.add(valoresLicenciaF);
        valoresLicencia.add(valoresLicenciaG);
        
        gestorLicencias.guardarValoresLicencia(valoresLicencia);
        gestorLicencias.guardarCostoAdministrativo(Double.parseDouble(tfCostosAdministrativos.getText()));
        
    }
    
    @FXML
    public void cargarTabla(){
        
        listaDeCostos = gestorLicencias.obtenerCostos();
        costoAdministrativo = gestorLicencias.obtenerCostoAdministrativo();
        tfCostosAdministrativos.setText(costoAdministrativo.toString());
        
        tfA5Anios.setText(listaDeCostos.get(0).get(0).toString());
        tfA4Anios.setText(listaDeCostos.get(0).get(1).toString());
        tfA3Anios.setText(listaDeCostos.get(0).get(2).toString());
        tfA1Anios.setText(listaDeCostos.get(0).get(3).toString());
        
        tfB5Anios.setText(listaDeCostos.get(1).get(0).toString());
        tfB4Anios.setText(listaDeCostos.get(1).get(1).toString());
        tfB3Anios.setText(listaDeCostos.get(1).get(2).toString());
        tfB1Anios.setText(listaDeCostos.get(1).get(3).toString());
        
        tfC5Anios.setText(listaDeCostos.get(2).get(0).toString());
        tfC4Anios.setText(listaDeCostos.get(2).get(1).toString());
        tfC3Anios.setText(listaDeCostos.get(2).get(2).toString());
        tfC1Anios.setText(listaDeCostos.get(2).get(3).toString());
        
        tfD5Anios.setText(listaDeCostos.get(3).get(0).toString());
        tfD4Anios.setText(listaDeCostos.get(3).get(1).toString());
        tfD3Anios.setText(listaDeCostos.get(3).get(2).toString());
        tfD1Anios.setText(listaDeCostos.get(3).get(3).toString());
        
        tfE5Anios.setText(listaDeCostos.get(4).get(0).toString());
        tfE4Anios.setText(listaDeCostos.get(4).get(1).toString());
        tfE3Anios.setText(listaDeCostos.get(4).get(2).toString());
        tfE1Anios.setText(listaDeCostos.get(4).get(3).toString());
        
        tfF5Anios.setText(listaDeCostos.get(5).get(0).toString());
        tfF4Anios.setText(listaDeCostos.get(5).get(1).toString());
        tfF3Anios.setText(listaDeCostos.get(5).get(2).toString());
        tfF1Anios.setText(listaDeCostos.get(5).get(3).toString());
        
        tfG5Anios.setText(listaDeCostos.get(6).get(0).toString());
        tfG4Anios.setText(listaDeCostos.get(6).get(1).toString());
        tfG3Anios.setText(listaDeCostos.get(6).get(2).toString());
        tfG1Anios.setText(listaDeCostos.get(6).get(3).toString());
 
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
            Double.parseDouble(tfA5Anios.getText());
            Double.parseDouble(tfB5Anios.getText());
            Double.parseDouble(tfC5Anios.getText());
            Double.parseDouble(tfD5Anios.getText());
            Double.parseDouble(tfE5Anios.getText());
            Double.parseDouble(tfF5Anios.getText());
            Double.parseDouble(tfG5Anios.getText());
            
            Double.parseDouble(tfA4Anios.getText());
            Double.parseDouble(tfB4Anios.getText());
            Double.parseDouble(tfC4Anios.getText());
            Double.parseDouble(tfD4Anios.getText());
            Double.parseDouble(tfE4Anios.getText());
            Double.parseDouble(tfF4Anios.getText());
            Double.parseDouble(tfG4Anios.getText());
            
            Double.parseDouble(tfA3Anios.getText());
            Double.parseDouble(tfB3Anios.getText());
            Double.parseDouble(tfC3Anios.getText());
            Double.parseDouble(tfD3Anios.getText());
            Double.parseDouble(tfE3Anios.getText());
            Double.parseDouble(tfF3Anios.getText());
            Double.parseDouble(tfG3Anios.getText());
           
            Double.parseDouble(tfA1Anios.getText());
            Double.parseDouble(tfB1Anios.getText());
            Double.parseDouble(tfC1Anios.getText());
            Double.parseDouble(tfD1Anios.getText());
            Double.parseDouble(tfE1Anios.getText());
            Double.parseDouble(tfF1Anios.getText());
            Double.parseDouble(tfG1Anios.getText());
            
        }
        catch(NumberFormatException fne){
            datosCorrectos = false;
        }
        if (!datosCorrectos){
            Alert mensajeErrores = new Alert(Alert.AlertType.INFORMATION);
            mensajeErrores.setTitle("Datos faltantes/incorrectos");
            mensajeErrores.setHeaderText("No puede dejar campos vacios o ingresar datos no numéricos");
            mensajeErrores.initModality(Modality.APPLICATION_MODAL);
            mensajeErrores.show();
        }
        
        return datosCorrectos;
    }   
}
