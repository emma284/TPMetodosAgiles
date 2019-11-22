/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.gestores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.stage.Stage;
import tpmetodosagiles.entidades.Usuario;

/**
 * 
 * Esta clase contendrá los valores comunes y necesarios en distintas interfaces de usuario.
 * @author usuario
 */
public class GestorDeConfiguracion {
    private static Usuario usuarioActual;
    private static Stage ventanaActual;

    public GestorDeConfiguracion(){
    }
    
    public GestorDeConfiguracion(Usuario usr, Stage ventana){
        this();
        usuarioActual = usr;
        ventanaActual = ventana;
    }
    
    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static Stage getVentanaActual() {
        return ventanaActual;
    }

    public static void setUsuarioActual(Usuario usuario) {
        usuarioActual = usuario;
    }

    public static void setVentanaActual(Stage ventana) {
        ventanaActual = ventana;
    }
    
    
}
