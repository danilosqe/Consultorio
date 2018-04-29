/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import DAO.ConexaoMySQL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.ConsultasModel;

/**
 *
 * @author Danilo
 */
public class ControleConsulta {
    public String alterarConsulta(ConsultasModel consulta)
    {
        ConexaoMySQL conex = new ConexaoMySQL();
        conex.conecta();
        String sql = "update consultas set consulta_espec = ?, med_nome = ?, med_crm = ?, consulta_dataHora = ? cons_numero = ? where consulta_id = ?";
        String retorno = "";
        PreparedStatement pst;
        try {
            pst = conex.con.prepareStatement(sql);
            pst.setString(1, consulta.getConsulta_espec());
            pst.setString(1, consulta.getMedico_nome());
            pst.setString(3, consulta.getMedico_crm());
            pst.setDate(4, (Date) consulta.getConsulta_dataHora());
            pst.setInt(5, consulta.getCons_numero());
            pst.setInt(6, consulta.getConsulta_id());
            pst.execute();
            retorno = "Consulta alterada com sucesso";
        } catch (SQLException ex) {
            Logger.getLogger(ControleConsulta.class.getName()).log(Level.SEVERE, null, ex);
            retorno = "falha ao alterar os dados da consulta \n"+ex.getMessage();
        }
        conex.desconecta();
        return retorno;
    }
}
