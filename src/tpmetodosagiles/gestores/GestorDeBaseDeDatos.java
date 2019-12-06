/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tpmetodosagiles.gestores;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import org.hibernate.SQLQuery;
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
            session.beginTransaction();
            session.save(unaLicencia);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("------------------------\n" + e.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean modificarLicencia(Licencia unaLicencia){
        try {
            session.beginTransaction();
            session.saveOrUpdate(unaLicencia);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("------------------------\n" + e.getMessage());
            return false;
        }
        return true;
    }
    
    
    public boolean guardarTitular(Titular unTitular){
        try {
           
            session.beginTransaction();
            session.saveOrUpdate(unTitular);
            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("------------------------\n" + e.getMessage());
            return false;
        }
        return true;
    }
    
    
    public List<Licencia> getLicenciasExpiradas(String nombre, String apellido, String clase, LocalDate fechaDesde, LocalDate fechaHasta){
        
        LocalDate hoy = LocalDate.now();
        
        String arreglo = "";
        if(nombre!=null){
           arreglo = arreglo + "AND t.nombre = " + nombre; 
        }
        if(apellido!=null){
           arreglo = arreglo + "AND t.apellido = " + apellido; 
        }
        if(clase!=null){
           arreglo = arreglo + "AND l.clase_licencia = " + clase; 
        }
        if((fechaDesde!=null)||(fechaHasta!=null)){
            if((fechaDesde!=null)&&(fechaHasta!=null)){
                arreglo = arreglo + "AND l.fecha_vencimiento BETWEEN " + fechaDesde + "AND" + fechaHasta; 
            }
            else if(fechaDesde!=null){
                arreglo = arreglo + "AND l.fecha_vencimiento > " + fechaDesde; 
            }
            else{
                arreglo = arreglo + "AND l.fecha_vencimiento < " + fechaHasta; 
            }
        }
        
        session.beginTransaction();

        SQLQuery query = session.createSQLQuery("SELECT * FROM licencia l JOIN titular t ON l.id_titular=t.id "
                + "WHERE fechaVencimiento < now()" + arreglo + "")
                .addEntity("l",Licencia.class)
		.addJoin("t","l.titular");
//                .setParameter("hoy", hoy);
        
        List<Object[]> rows = query.list();
        List<Licencia> retorno = null;
//        for (Object[] row : rows) {
//            Licencia l = (Licencia) row[0]; 
//            Titular t = (Titular) row[1];
//        }
        
//        session.close()
        
        return retorno;
    }
    
    public Titular getTitularPorDNI(String tipoDocumento, String numDocumento) {
        session.beginTransaction();
        List<Titular> result = session.createSQLQuery("SELECT * FROM titular t "
            + "WHERE t.numero_documento = " + numDocumento
            + " AND t.tipo_de_documento = '" + tipoDocumento + "'").addEntity(Titular.class).list();
        if (!result.isEmpty()) {
            return result.get(0);
        }
        else{
            System.out.println("No se encontró el titular");
        }
        return null;
    }
    
    /**
     *Verifica que el titular ya tenga una licencia registrada de la clase claseLicencia
     */
    public boolean titularRenovanteDeLicenciaClaseX(int idTitular, char claseLicencia) {
        session.beginTransaction();
        
        List<Licencia> result = session.createSQLQuery("SELECT * FROM licencia l "
            + "WHERE l.id_titular = " + idTitular
            + " AND l.clase_licencia = '" + claseLicencia + "'").addEntity(Licencia.class).list();
        if (!result.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }
    
    public List<Licencia> getLicenciasPorIDTitular(int idTitular) {
        session.beginTransaction();
        
        List<Licencia> result = session.createSQLQuery("SELECT * FROM licencia l "
            + "WHERE l.id_titular = " + idTitular).addEntity(Licencia.class).list();
        if (!result.isEmpty()){
            return result;
        }
        else{
            System.out.println("Lista vacia");
        }
        return null;
    }
    
    //TODO retornar null en caso de no encontrar el usuario en lugar de unUsuario que solo está inicializado?
    public Usuario getUsuarioPorId(int id) {
        session.beginTransaction();
        Usuario unUsuario = new Usuario();
        List<Usuario> result = session.createSQLQuery("SELECT * FROM usuario u "
            + "WHERE u.id = " + id).addEntity(Usuario.class).list();
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