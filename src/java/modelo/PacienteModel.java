/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Danilo
 */
public class PacienteModel {
    
    private int pac_id;
    private String pac_nome;
    private String pac_cpf;
    
    /**
     * @return the pac_id
     */
    public int getPac_id() {
        return pac_id;
    }

    /**
     * @param pac_id the pac_id to set
     */
    public void setPac_id(int pac_id) {
        this.pac_id = pac_id;
    }

    /**
     * @return the pac_nome
     */
    public String getPac_nome() {
        return pac_nome;
    }

    /**
     * @param pac_nome the pac_nome to set
     */
    public void setPac_nome(String pac_nome) {
        this.pac_nome = pac_nome;
    }

    /**
     * @return the pac_cpf
     */
    public String getPac_cpf() {
        return pac_cpf;
    }

    /**
     * @param pac_cpf the pac_cpf to set
     */
    public void setPac_cpf(String pac_cpf) {
        this.pac_cpf = pac_cpf;
    }
    
    
}
