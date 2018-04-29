/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import DAO.ConexaoMySQL;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.MedicoModel;

/**
 *
 * @author Danilo
 */
public class ControleMedicos {

    public ControleMedicos() {

    }
    // METODO PARA CADASTRAR O MEDICO
    public String CadastroMedico(MedicoModel medico){
        ConexaoMySQL conex = new ConexaoMySQL();
        conex.conecta();
        
        String sql = "insert into medicos(med_nome, med_crm, med_idade, med_espec) values(?, ?, ?, ?)";
        PreparedStatement pst;
        try {
            pst = conex.con.prepareStatement(sql);
            pst.setString(1, medico.getMed_nome());
            pst.setString(2, medico.getMed_crm());
            pst.setInt(3, medico.getMed_idade());
            pst.setString(4, medico.getMed_espec());
            pst.execute();
            conex.desconecta();
            return "Medico cadastrado com sucesso";
        } catch (SQLException sQLException) {
            System.out.println("Falha ao inserir os dados");
            return "Falha ao inserir os dados"+sQLException.getMessage();
        }
    }
    
    //METODO PARA BUSCAR A LISTA DE MEDICOS
    public List buscarTodosOsMedicos() {
        List<MedicoModel> listaMedico = new ArrayList();
        ConexaoMySQL conex = new ConexaoMySQL();
        
        conex.conecta();
        conex.executaSql("select * from medicos ");
        MedicoModel medico = null;
        try {
            medico = new MedicoModel();
            while(conex.rs.next()){
            
                medico.setMed_id(conex.rs.getInt("med_id"));
                medico.setMed_nome(conex.rs.getString("med_nome"));
                medico.setMed_idade(conex.rs.getInt("med_idade"));
                medico.setMed_crm(conex.rs.getString("med_crm"));
                medico.setMed_espec(conex.rs.getString("med_espec"));
                listaMedico.add(medico);

            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicoModel.class.getName()).log(Level.SEVERE, null, ex);

        }
        conex.desconecta();
        return listaMedico;
    }
    //METODO PARA BUSCAR MEDICO

    public MedicoModel buscaMedico(MedicoModel medicomod) {
        ConexaoMySQL conex = new ConexaoMySQL();
        
        conex.conecta();
        conex.executaSql("select * from medicos where med_id like " + medicomod.getMed_id());
        MedicoModel medico = null;
        try {
            medico = new MedicoModel();
            conex.rs.first();
            medico.setMed_id(conex.rs.getInt("med_id"));
            medico.setMed_nome(conex.rs.getString("med_nome"));
            medico.setMed_idade(conex.rs.getInt("med_idade"));
            medico.setMed_crm(conex.rs.getString("med_crm"));
            medico.setMed_espec(conex.rs.getString("med_espec"));
        } catch (SQLException ex) {
            Logger.getLogger(MedicoModel.class.getName()).log(Level.SEVERE, null, ex);

        }
        conex.desconecta();
        return medico;
    }
    public String alterarMedico(MedicoModel medico){
        ConexaoMySQL conex = new ConexaoMySQL();
        conex.conecta();
        String sql = ("update medicos set med_nome = ?, med_crm =?, med_idade = ?, med_espec =? where id medico = ?");
        PreparedStatement pst;
        String retorno = "";
        try {
            pst = conex.con.prepareStatement(sql);
            pst.setString(1, medico.getMed_nome());
            pst.setString(2, medico.getMed_crm());
            pst.setInt(3, medico.getMed_idade());
            pst.setString(4, medico.getMed_espec());
            pst.setInt(5, medico.getMed_id());
            pst.execute();
            conex.desconecta();
            retorno = "Medico alterado com sucesso nome do medico: "+medico.getMed_nome();
        } catch (SQLException ex) {
            Logger.getLogger(ControleMedicos.class.getName()).log(Level.SEVERE, null, ex);
            retorno =  "erro ao alterar dados do medico \n"+ex.getMessage();
        }
        
        return retorno;
        
    }

}
