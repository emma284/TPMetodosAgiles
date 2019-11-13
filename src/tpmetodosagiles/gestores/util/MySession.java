/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.gestores.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Gino
 */
public class MySession {
        private static Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        private static SessionFactory sessionFactory = null;
            
    private static void inicializar(){
        if(sessionFactory==null)
        sessionFactory = cfg.buildSessionFactory();
    }

    public static Session get(){
        inicializar();
        return sessionFactory.getCurrentSession();
    }
    
}
