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
    M,F;
    
    public static SexoEnum getSexoPorIdentificador(char identificador){
        if( (identificador == 'M') || (identificador == 'm') )
            return SexoEnum.M;
        else if( (identificador == 'F') || (identificador == 'f') )
            return SexoEnum.F;
        
        return null;
    }
    
    public char getIdentificador(){
        if(this == SexoEnum.M)
            return 'M';
        else
            return 'F';
    }
    
    public String getDescriptor(){
        if(this == SexoEnum.M)
            return "Masculino";
        else
            return "Femenino";
    }
    
    @Override
    public String toString(){
        if(this == SexoEnum.M)
            return "M";
        else
            return "F";
    }
}
