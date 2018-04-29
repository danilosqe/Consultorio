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
import modelo.PacienteModel;

/**
 *
 * @author Danilo
 */
public class ControlePaciente {
    
    public void CadastroPaciente(PacienteModel paciente){
        ConexaoMySQL conex = new ConexaoMySQL();
        conex.conecta();
        
        String sql = "insert into paciente(pac_nome, pac_cpf) values(?,?)";
        PreparedStatement pst;
        
        try {
            pst = conex.con.prepareStatement(sql);
            pst.setString(0, paciente.getPac_nome());
            pst.setString(1, paciente.getPac_cpf());
            pst.execute();
        } catch (SQLException sQLException) {
            System.out.println("Falha ao inserir o paciente");
        }
        conex.desconecta();
    }
    
    public List buscarTodosOsPacientes(){
        List<PacienteModel> listaPaciente = new ArrayList();
        ConexaoMySQL conex = new ConexaoMySQL();
        
        conex.conecta();
        conex.executaSql("selec * from paciente");
        PacienteModel paciente = null;
        paciente = new PacienteModel();
        try {
            while(conex.rs.next()){
                paciente.setPac_id(conex.rs.getInt("pac_id"));
                paciente.setPac_nome(conex.rs.getString("pac_nome"));
                paciente.setPac_cpf(conex.rs.getString("pac_cpf"));
                listaPaciente.add(paciente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControlePaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conex.desconecta();
        return listaPaciente;
        
    }
    
    public PacienteModel buscarPaciente(PacienteModel pac_id){
        PacienteModel paciente = new PacienteModel();
        ConexaoMySQL conex = new ConexaoMySQL();
        conex.conecta();
        conex.executaSql("select * from paciente where pac_id like "+pac_id);
        
        try {
            paciente = new PacienteModel();
            conex.rs.first();
            paciente.setPac_id(conex.rs.getInt("pac_id"));
            paciente.setPac_nome(conex.rs.getString("pac_nome"));
            paciente.setPac_cpf(conex.rs.getString("pac_cpf"));
        } catch (SQLException ex) {
            Logger.getLogger(ControlePaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        conex.desconecta();
        return paciente;
    }
    
    
}
