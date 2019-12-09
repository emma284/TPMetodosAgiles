/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.gestores.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class MySessionDBCiudad {
        private static Configuration cfg = new Configuration().configure("hibernateDBCiudad.cfg.xml");
        private static SessionFactory sessionFactory = null;
            
    private static void inicializar(){
        if(sessionFactory==null)
            sessionFactory = cfg.buildSessionFactory();
    }

    public static Session get() throws Exception{
        inicializar();
        return sessionFactory.openSession();
    }
    
    public static void close(){
        if (sessionFactory != null)
            sessionFactory.close();
    }
}
