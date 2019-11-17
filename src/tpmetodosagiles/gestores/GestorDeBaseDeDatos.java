/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tpmetodosagiles.gestores;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import org.hibernate.Session;
import tpmetodosagiles.entidades.Licencia;
import tpmetodosagiles.entidades.Titular;
import tpmetodosagiles.entidades.Usuario;
import tpmetodosagiles.gestores.util.MySession;


public class GestorDeBaseDeDatos {
    private Session session = null;
    
    public GestorDeBaseDeDatos() {
        try {
            session = MySession.get();
        } catch (Exception ex) {
            Alert mensajeErrores = new Alert(Alert.AlertType.ERROR);
            mensajeErrores.setTitle("Sin conexión al servidor");
            mensajeErrores.setHeaderText("No se pudo establecer conexión con el servidor. Intente nuevamente más tarde.");
            mensajeErrores.setContentText(ex.getMessage());
            
            mensajeErrores.initModality(Modality.APPLICATION_MODAL);
            mensajeErrores.show();
        }
    }
    
    
    public boolean guardarLicencia(Licencia unaLicencia){
        try {
        //session = MySession.get();
        session.beginTransaction();
        session.save(unaLicencia);
        session.getTransaction().commit();
        session.close();    
        } catch (Exception e) {
            System.out.println("------------------------\n" + e.getMessage());
            return false;
        }
        return true;
    }
    
    
    public boolean guardarTitular(Titular unTitular){
        try {
            //session = MySession.get();
            session.beginTransaction();
            session.save(unTitular);
            session.getTransaction().commit();
//          session.close();
        } catch (Exception e) {
            System.out.println("------------------------\n" + e.getMessage());
            return false;
        }
        return true;
    }
    
    public Titular getTitularPorDNI(String tipoDocumento, String numDocumento) {
        session.beginTransaction();
        List<Titular> result = session.createSQLQuery("SELECT * FROM titular t "
            + "WHERE t.numero_documento = " + numDocumento
            + " AND t.tipo_de_documento = '" + tipoDocumento + "'").addEntity(Titular.class).list();
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }
    
    public Licencia getLicenciaPorIDTitularYClase(int idTitular, char claseLicencia) {
        session.beginTransaction();
        Licencia unaLicencia = new Licencia();
        
        List<Licencia> result = session.createSQLQuery("SELECT * FROM licencia l "
            + "WHERE l.id_titular = " + idTitular
            + " AND l.clase_licencia = '" + claseLicencia + "'").addEntity(Licencia.class).list();
        if (!result.isEmpty()){
            unaLicencia = result.get(0);
        }
        else{
            System.out.println("Lista vacia");
        }
        return unaLicencia;
        
        /*Licencia unTitular = new Titular();
        List<Titular> result = session.createSQLQuery("SELECT * FROM titular t "
            + "WHERE t.numero_documento = " + numDocumento
            + " AND t.tipo_de_documento = '" + tipoDocumento + "'").addEntity(Titular.class).list();
        if (!result.isEmpty()) {
            unTitular = result.get(0);
        }else{
            System.out.println("Lista vacia");
        }
        return unTitular;*/
    }
    
    
    public Usuario getUsuarioPorId(int id) {
        session.beginTransaction();
        Usuario unUsuario = new Usuario();
        List<Usuario> result = session.createSQLQuery("SELECT * FROM usuario t "
            + "WHERE t.id = " + id).addEntity(Usuario.class).list();
        if (!result.isEmpty()) {
            unUsuario = result.get(0);
        }
        return unUsuario;
    }
}


/*
package tpmetodosagiles.gestores;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import tpmetodosagiles.entidades.Licencia;
import tpmetodosagiles.entidades.Titular;


public class GestorDeBaseDeDatos {
    private EntityManagerFactory emf;			
    private EntityManager em;
    
    public GestorDeBaseDeDatos() {
	emf = Persistence.createEntityManagerFactory("persistencia");
	em = emf.createEntityManager();
    }
    
    public boolean guardarLicencia(Licencia unaLicencia){
        try{
            em.getTransaction().begin();
            em.persist(unaLicencia);
            em.getTransaction().commit();
        }
        catch(Exception e){
            System.out.println("------------------------\n" + e.getMessage());
            return false;
        }
        
        return true;
    }
    
    public boolean guardarTitular(Titular unTitular){
        try{
            em.getTransaction().begin();
            em.persist(unTitular);
            em.getTransaction().commit();
        }
        catch(Exception e){
            System.out.println("------------------------\n" + e.getMessage());
            return false;
        }
        
        return true;
    }

    public Titular getTitularPorDNI(String numDocumento) {
       Titular unTitular = new Titular();
       List<Titular> result = em.createQuery("SELECT t FROM titular t WHERE t.numero_documento = " + numDocumento + ";").getResultList();
       if(!result.isEmpty()){
           unTitular = result.get(0);
       }
       return unTitular;
    }
}
*/