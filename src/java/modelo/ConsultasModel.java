/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author Danilo
 */
public class ConsultasModel {

    /**
     * @return the cons_numero
     */
    public int getCons_numero() {
        return cons_numero;
    }

    /**
     * @param cons_numero the cons_numero to set
     */
    public void setCons_numero(int cons_numero) {
        this.cons_numero = cons_numero;
    }

    /**
     * @return the consulta_id
     */
    public int getConsulta_id() {
        return consulta_id;
    }

    /**
     * @param consulta_id the consulta_id to set
     */
    public void setConsulta_id(int consulta_id) {
        this.consulta_id = consulta_id;
    }

    /**
     * @return the consulta_espec
     */
    public String getConsulta_espec() {
        return consulta_espec;
    }

    /**
     * @param consulta_espec the consulta_espec to set
     */
    public void setConsulta_espec(String consulta_espec) {
        this.consulta_espec = consulta_espec;
    }

    /**
     * @return the medico_nome
     */
    public String getMedico_nome() {
        return medico_nome;
    }

    /**
     * @param medico_nome the medico_nome to set
     */
    public void setMedico_nome(String medico_nome) {
        this.medico_nome = medico_nome;
    }

    /**
     * @return the medico_crm
     */
    public String getMedico_crm() {
        return medico_crm;
    }

    /**
     * @param medico_crm the medico_crm to set
     */
    public void setMedico_crm(String medico_crm) {
        this.medico_crm = medico_crm;
    }

    /**
     * @return the consulta_dataHora
     */
    public Date getConsulta_dataHora() {
        return consulta_dataHora;
    }

    /**
     * @param consulta_dataHora the consulta_dataHora to set
     */
    public void setConsulta_dataHora(Date consulta_dataHora) {
        this.consulta_dataHora = consulta_dataHora;
    }
    private int consulta_id;
    private String consulta_espec;
    private String medico_nome;
    private String medico_crm;
    private Date consulta_dataHora;
    private int cons_numero;
}
