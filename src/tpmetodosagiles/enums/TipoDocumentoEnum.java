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
public enum TipoDocumentoEnum {
    DNI, LE, LC, DE;
    
    /**
     * Retorna una instancia de TipoDocumentoEnum a partir de un String.
     * @param descriptor - Texto largo que describe el tipo de documento
     * @return la instancia de TipoDocumentoEnum o null si el descriptor no coincide con los pre- establecidos
     */
    public static TipoDocumentoEnum getTipoDocumentoDesdeString(String descriptor){
        if(descriptor.equals(DNI.toString())){
            return TipoDocumentoEnum.DNI;
        }
        else if(descriptor.equals(LE.toString())){
            return TipoDocumentoEnum.LE;
        }
        else if(descriptor.equals(LC.toString())){
            return TipoDocumentoEnum.LC;
        }
        else if(descriptor.equals(DE.toString())){
            return TipoDocumentoEnum.DE;
        }
        else return null;
    }
    
    public String getIdentificadorDesdeDescriptor(String descriptor){
        if(descriptor.equals(DNI.toString())){
            return DNI.getIdentificador();
        }
        else if(descriptor.equals(LE.toString())){
            return LE.getIdentificador();
        }
        else if(descriptor.equals(LC.toString())){
            return LC.getIdentificador();
        }
        else if(descriptor.equals(DE.toString())){
            return DE.getIdentificador();
        }
        else return null;
        
    }
    
    @Override
    public String toString(){
        if(this == TipoDocumentoEnum.DNI ){
            return "DNI";
        }
        else if(this == TipoDocumentoEnum.LE ){
            return "Libreta de Enrrolamiento";
        }
        else if(this == TipoDocumentoEnum.LC ){
            return "Libreta Cívica";
        }
        else if(this == TipoDocumentoEnum.DE ){
            return "DNI Extranjero";
        }
        else return "";
    }
    
    /**
     * Obtiene el identificador de la instancia, que es la misma sigla que se usa para referenciarla.
     * @return un String del identificador de la instancia
     */
    public String getIdentificador(){
        if(this == TipoDocumentoEnum.DNI ){
            return "DNI";
        }
        else if(this == TipoDocumentoEnum.LE ){
            return "LE";
        }
        else if(this == TipoDocumentoEnum.LC ){
            return "LC";
        }
        else if(this == TipoDocumentoEnum.DE ){
            return "DE";
        }
        else return "";
    }
}
