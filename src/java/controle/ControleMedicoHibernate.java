/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import com.google.gson.Gson;
import dao.Medicos;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Danilo
 */
public class ControleMedicoHibernate {
    public static void main(String[] args) {
          Medicos m = new Medicos();      
        try {
            
            SessionFactory factory = new Configuration().configure("/dao/hibernate.cfg.xml").buildSessionFactory();
            
            Session sessao = factory.openSession();
            
            m = (Medicos) sessao.createQuery("from Medicos where med_id like 1");
//            m.setMed_crm("");
//            m.setMed_idade(18);
//            m.setMed_nome("");
//            m.setMed_id(1);
//            m.setMed_espec("");
            
            Gson g = new Gson();
        
            
            System.out.println(g.toJson(m));
            
        } catch (HibernateException hibernateException) {
            Gson g = new Gson();
            System.out.println(g.toJson(hibernateException.getMessage()));
        }
    }
}
