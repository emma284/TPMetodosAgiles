/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.layouts;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 *
 * @author usuario
 */
public class TextFieldSoloNumeros extends TextField {
    private int longitudMaxima = 10;
    
    //TODO: Restringir la longitud de texto cuando se pega texto
    
    @Override
    public void replaceText(int start, int end, String text)
    {
        if (validar(text))
        {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String text)
    {
        if (validar(text))
        {
            super.replaceSelection(text);
        }
    }

    private boolean validar(String text)
    {
        //this.getText() retorna el último texto valido del TextField (sin el último valor ingresado, que está en la variable 'text')
        return (text.matches("[0-9]*") && this.getText().length() < this.longitudMaxima);
    }
    
    public void setLongitudMaxima(int longitud){
        this.longitudMaxima = longitud;
    }
}
