/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.enums;

/**
 *
 * @author usuario
 */
public enum SexoEnum {
    MASCULINO,FEMENINO;
    
    public static SexoEnum getSexoPorIdentificador(char identificador){
        if( (identificador == 'M') || (identificador == 'm') )
            return SexoEnum.MASCULINO;
        else if( (identificador == 'F') || (identificador == 'f') )
            return SexoEnum.FEMENINO;
        
        return null;
    }
    
    public char getIdentificador(){
        if(this == SexoEnum.MASCULINO)
            return 'M';
        else
            return 'F';
    }
    
    @Override
    public String toString(){
        if(this == SexoEnum.MASCULINO)
            return "Masculino";
        else
            return "Femenino";
    }
}
