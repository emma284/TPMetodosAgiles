/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.gestores;

import tpmetodosagiles.entidades.ContribuyenteDTO;
import tpmetodosagiles.enums.TipoDocumentoEnum;

/**
 *
 * @author usuario
 */
public class GestorDeContribuyente {
    private GestorDeBDCiudad gbdc = new GestorDeBDCiudad();
    
    /**
     * Busca un contribuyente en la base de datos de la provincia utilizando como parámetros el tipo y número de documento de un contribuyente. 
     */
    public ContribuyenteDTO getContribuyente(String tipoDeDocumento, String numeroDeDocumento){
        int numDocument;
        
        try{
            numDocument = Integer.parseInt(numeroDeDocumento);
        }
        catch(NumberFormatException nfe){
            return null;
        }
        
        TipoDocumentoEnum tipDocument = TipoDocumentoEnum.getTipoDocumentoDesdeString(tipoDeDocumento);
        if (tipDocument == null)
            return null;
        
        return gbdc.getContribuyentePorDocumento(tipDocument, numDocument);
    }
}
