/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import DAO.ConexaoMySQL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.ConsultorioModel;

/**
 *
 * @author Danilo
 */
public class ControleConsultorio {
    
    public void cadastrarConsultorio(ConsultorioModel consultorio){
        ConexaoMySQL conex = new ConexaoMySQL();
        conex.conecta();
        
        String sql = "insert into consultorio(cons_id)";
        PreparedStatement pst;
        try {
            pst = conex.con.prepareStatement(sql);
            pst.setInt(0, consultorio.getCons_numero());
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ControleConsultorio.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Falha ao inserir o Consultorio");
        }
        conex.desconecta();
    }
    
    public List buscaTodosOsConsultorios(){
        List<ConsultorioModel> listaConsultorio  = new ArrayList();
        ConexaoMySQL conex = new ConexaoMySQL();
        conex.conecta();
        
        conex.executaSql("select * from consultorio");
        ConsultorioModel consultorio = null;
        
        consultorio = new ConsultorioModel();
        
        try {
            while(conex.rs.next()){
                consultorio.setCons_id(conex.rs.getInt("cons_id"));
                consultorio.setCons_numero(conex.rs.getInt("cons_numero"));
                listaConsultorio.add(consultorio);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControleConsultorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        conex.desconecta();
        return listaConsultorio;    
    }
    
    public ConsultorioModel buscaConsltorio(ConsultorioModel cons_id){
        ConsultorioModel consultorio = new ConsultorioModel();
        ConexaoMySQL conex = new ConexaoMySQL();
        conex.executaSql("select * form consultorio where cons_id like "+cons_id);
        
        try {
            consultorio = new ConsultorioModel();
            conex.rs.first();
            consultorio.setCons_id(conex.rs.getInt("cons_id"));
            consultorio.setCons_numero(conex.rs.getInt("cons_numero"));
        } catch (SQLException ex) {
            Logger.getLogger(ControleConsultorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conex.desconecta();
        return consultorio;
    }
}

