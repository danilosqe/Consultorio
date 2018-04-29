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
public class MedicoModel {

    /**
     * @return the med_id
     */
    public int getMed_id() {
        return med_id;
    }

    /**
     * @param med_id the med_id to set
     */
    public void setMed_id(int med_id) {
        this.med_id = med_id;
    }

    /**
     * @return the med_nome
     */
    public String getMed_nome() {
        return med_nome;
    }

    /**
     * @param med_nome the med_nome to set
     */
    public void setMed_nome(String med_nome) {
        this.med_nome = med_nome;
    }

    /**
     * @return the med_crm
     */
    public String getMed_crm() {
        return med_crm;
    }

    /**
     * @param med_crm the med_crm to set
     */
    public void setMed_crm(String med_crm) {
        this.med_crm = med_crm;
    }

    /**
     * @return the med_idade
     */
    public int getMed_idade() {
        return med_idade;
    }

    /**
     * @param med_idade the med_idade to set
     */
    public void setMed_idade(int med_idade) {
        this.med_idade = med_idade;
    }
    
    /**
     * @return the med_espec
     */
    public String getMed_espec() {
        return med_espec;
    }

    /**
     * @param med_espec the med_crm to set
     */
    public void setMed_espec(String med_espec) {
        this.med_espec = med_espec;
    }
    private int med_id;
    private String med_nome;
    private String med_crm;
    private int med_idade;
    private String med_espec;
}
