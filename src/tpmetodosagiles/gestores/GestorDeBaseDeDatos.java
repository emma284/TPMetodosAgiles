/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
