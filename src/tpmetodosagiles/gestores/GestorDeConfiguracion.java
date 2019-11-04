/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.gestores;

import javafx.stage.Stage;
import tpmetodosagiles.entidades.Usuario;

/**
 *
 * @author usuario
 */
public class GestorDeConfiguracion {
    private Usuario usuarioActual;
    private Stage ventanaActual;
    private GestorDeBaseDeDatos gestorBD;

    public GestorDeConfiguracion(){
        //TODO: Descomentar...     gestorBD = new GestorDeBaseDeDatos();
    }
    
    public GestorDeConfiguracion(Usuario usr, Stage ventana){
        this();
        usuarioActual = usr;
        ventanaActual = ventana;
    }
    
    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public Stage getVentanaActual() {
        return ventanaActual;
    }

    public GestorDeBaseDeDatos getGestorBD() {
        return gestorBD;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public void setVentanaActual(Stage ventanaActual) {
        this.ventanaActual = ventanaActual;
    }
    
    
}
