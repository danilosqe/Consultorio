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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.ConsultasModel;

/**
 *
 * @author Danilo
 */
public class ControleConsulta {

    public String marcaConsulta(ConsultasModel consulta) {
        ConexaoMySQL conex = new ConexaoMySQL();
        conex.conecta();
        String sql = "insert into consultas (consulta_espec, med_nome, med_crm, consulta_dataHora, cons_numero) values(?, ?, ?, ?, ?)";
        PreparedStatement pst;
        String retorno = "";

        try {
            pst = conex.con.prepareStatement(sql);
            pst.setString(1, consulta.getConsulta_espec());
            pst.setString(2, consulta.getMedico_nome());
            pst.setString(3, consulta.getMedico_crm());
            pst.setDate(4, (Date) consulta.getConsulta_dataHora());
            pst.setInt(5, consulta.getCons_numero());
            pst.execute();
            retorno = "Consulta agendada";

        } catch (SQLException ex) {
            Logger.getLogger(ControleConsulta.class.getName()).log(Level.SEVERE, null, ex);
            retorno = "não foi possivel agendar erro.: " + ex.getMessage();
        }
        conex.desconecta();
        return retorno;
    }

    public ConsultasModel buscaConsulta(ConsultasModel consultaMod) {
        ConexaoMySQL conex = new ConexaoMySQL();

        conex.conecta();
        conex.executaSql("select + from consultas where consulta_id like " + consultaMod.getConsulta_id());
        ConsultasModel consulta = null;

        try {
            consulta = new ConsultasModel();
            conex.rs.first();
            consulta.setConsulta_id(conex.rs.getInt("consulta_id"));
            consulta.setCons_numero(conex.rs.getInt("cons_numero"));
            consulta.setConsulta_dataHora(conex.rs.getDate("consulta_dataHora"));
            consulta.setConsulta_espec(conex.rs.getString("med_espec"));
            consulta.setMedico_crm(conex.rs.getString("med_crm"));
            consulta.setMedico_nome(conex.rs.getString("med_nome"));
            consulta.setPac_nome(conex.rs.getString("pac_nome"));

        } catch (SQLException ex) {
            Logger.getLogger(ControleConsulta.class.getName()).log(Level.SEVERE, null, ex);

        }

        conex.desconecta();
        return consulta;
    }

    public List buscarTodasAsConsultas() {
        List<ConsultasModel> listaConsultas = new ArrayList();
        ConexaoMySQL conex = new ConexaoMySQL();
        conex.conecta();
        conex.executaSql("select * from consultas order by consulta_dataHora desc");

        ConsultasModel consulta = new ConsultasModel();
        try {
            while (conex.rs.next()) {
                consulta.setConsulta_id(conex.rs.getInt("consulta_id"));
                consulta.setConsulta_espec(conex.rs.getString("consulta_espec"));
                consulta.setMedico_nome(conex.rs.getString("med_nome"));
                consulta.setMedico_crm(conex.rs.getString("med_crm"));
                consulta.setConsulta_dataHora(conex.rs.getDate("consulta_dataHora"));
                consulta.setPac_nome(conex.rs.getString("pac_nome"));
                listaConsultas.add(consulta);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ControleConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        conex.desconecta();
        return listaConsultas;
    }

    public Date ultimaConsultaConsultorio(ConsultasModel consulta) {
        
        ConexaoMySQL conex = new ConexaoMySQL();
        Date retorno = null;
        conex.conecta();
        conex.executaSql("select consulta_dataHora from consultas where consulta_id like " + consulta.getConsulta_id());

        try {
            conex.rs.first();
            retorno = conex.rs.getDate("consulta_dataHora");

        } catch (SQLException ex) {
            Logger.getLogger(ControleConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }

        conex.desconecta();

        return retorno;

    }

    public String alterarConsulta(ConsultasModel consulta) {
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
            retorno = "falha ao alterar os dados da consulta \n" + ex.getMessage();
        }
        conex.desconecta();
        return retorno;
    }
}
