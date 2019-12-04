/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.interfaces;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import tpmetodosagiles.entidades.Licencia;

/**
 *
 * @author Gino
 */
public class FXMLCeldaListaLicenciasController extends ListCell<Licencia>{
    
    @FXML
    private Label labelClaseLicencia;
    @FXML
    private Label labelFechaEmision;
    @FXML
    private Label labelFechaVencimiento;
    @FXML
    private Label labelTitular;
//    @FXML
//    private Button bRenovar;
    @FXML
    private GridPane gridPane;

    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(Licencia unaLicencia, boolean empty) {
        super.updateItem(unaLicencia, empty);

        if(empty || unaLicencia == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("FXMLCeldaListaLicencias.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            labelTitular.setText(unaLicencia.getTitular().getApellido()+" "+unaLicencia.getTitular().getNombre());
            labelClaseLicencia.setText(String.valueOf(unaLicencia.getClaseLicencia()));
            labelFechaEmision.setText(unaLicencia.getFechaEmision().toString());
            labelFechaVencimiento.setText(unaLicencia.getFechaVencimiento().toString());


            setText(null);
            setGraphic(gridPane);
        }

    }
//    @FXML
//    public void renovarLicencia(){
//        System.out.println("Renovar licencia "+labelClaseLicencia);
//        
//    }
}
