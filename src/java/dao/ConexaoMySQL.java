/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Danilo
 */
public class ConexaoMySQL {
   
    // VARIÁVEIS RESPONSÁVEIS POR PEGAR AS INFORMAÇÕES DE ACESSO AO BANCO DE DAODS
        public Statement stm; // VÁRIAVEL RESPONSÁVEL PELAS PESQUISAS NO BANCO DE DADOS.
        public ResultSet rs; // GUARDA AS INFORMAÇÕES QUE FORAM PESQUISADAS NO BANCO DE DADOS.
        private String driver = "com.mysql.jdbc.Driver"; // DRIVER ORACLE PARA CONEXÃO DO BANCO.(não necessário no oracle).
        private String caminho = "jdbc:mysql://localhost/consultorio"; //CAMINHO DA CONEXÃO.
        private String usuario = "root"; // USUÁRIO DE CONEXÃO BANCO.
        private String senha = ""; // SENHA DE CONEXÃO BANCO.
        public Connection con = null;  // VARIÁVEL RESPONSÁVEL PELA CONEXÃO COM O BANCO DE DADOS.
        

        public ConexaoMySQL(){
            
        }
    
    // METODO QUE EFETUA A CONEXÃO COM O BANCO DE DADOS
   public void conecta(){  
       
            try {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ConexaoMySQL.class.getName()).log(Level.SEVERE, null, ex);
                }
                con = DriverManager.getConnection(caminho, usuario, senha); //PEGA A CONEXÃO COM O USUÁRIO E SENHA
                //JOptionPane.showMessageDialog(null,"CONEXÃO EFETUADA COM SUCESSO!!"); // SE EFETUAR A CONEXÃO EXIBE ESTA MENSAGEM
                
            } catch (SQLException ex) {
                System.out.println(ex.getMessage()); // SE NÃO SE CONECTAR EXIBE ESTA MENSAGEM.
                
                
            }        
   }
   // METODO QUE FECHA A CONEXÃO COM O BANCO DE DADOS
   public void desconecta(){
            try {
                con.close();
                //JOptionPane.showMessageDialog(null,"BANCO DE DADOS DESCONECTADO COM SUCESSO!!!");
                System.out.println("desconectado com sucesso");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Falha ao desconectar o banco de dados!\n" +ex.getMessage());
            }
            
   }
   
            //metodo para pesquisa no banco de dados.
   public void executaSql(String sql){
       
            try {
                stm = con.createStatement(rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
                rs= stm.executeQuery(sql);
                
            } catch (SQLException ex) {
                Logger.getLogger(ConexaoMySQL.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"Erro ExecutaSql\n" +ex.getMessage());
            }
   }
   
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
