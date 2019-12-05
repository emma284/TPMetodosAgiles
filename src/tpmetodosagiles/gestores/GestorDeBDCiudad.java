/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.gestores;

import java.util.List;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import org.hibernate.Session;
import tpmetodosagiles.entidades.ContribuyenteDTO;
import tpmetodosagiles.enums.TipoDocumentoEnum;
import tpmetodosagiles.gestores.util.MySessionDBCiudad;

/**
 *
 * @author usuario
 */
public class GestorDeBDCiudad {
    private Session session = null;
    
    public GestorDeBDCiudad() {
        try {
            session = MySessionDBCiudad.get();
        } catch (Exception ex) {
            Alert mensajeErrores = new Alert(Alert.AlertType.ERROR);
            mensajeErrores.setTitle("Sin conexión al servidor de Santa Fe Ciudad");
            mensajeErrores.setHeaderText("No se pudo establecer conexión con el servidor de Santa Fe Ciudad. Intente nuevamente más tarde.");
            mensajeErrores.setContentText(ex.getMessage());
            
            mensajeErrores.initModality(Modality.APPLICATION_MODAL);
            mensajeErrores.show();
        }
    }
    
    public ContribuyenteDTO getContribuyentePorDocumento(TipoDocumentoEnum tipoDocumento, int numDocumento) {
        session.beginTransaction();
        List<ContribuyenteDTO> result = session.createSQLQuery("SELECT * FROM contribuyente c "
            + "WHERE c.nro_documento = " + numDocumento
            + " AND c.tipo_documento = '" + tipoDocumento.getIdentificador() + "'").addEntity(ContribuyenteDTO.class).list();
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }
}
