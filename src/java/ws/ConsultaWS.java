/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import controle.ControleConsulta;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import modelo.ConsultasModel;

/**
 * REST Web Service
 *
 * @author Danilo
 */
@Path("Consulta")
public class ConsultaWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ConsultaWS
     */
    public ConsultaWS() {
    }

    /**
     * Retrieves representation of an instance of ws.ConsultaWS
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Consulta/get/{cons_id}")
    public String getConsulta(@PathParam("cons_id") int cons_id) {
        ConsultasModel cons = new ConsultasModel();
        cons.setConsulta_id(cons_id);
        ControleConsulta c = new ControleConsulta();
        cons = c.buscaConsulta(cons);
        Gson g = new Gson();
        
        
        return g.toJson(cons);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Consulta/get/listaConsulta")
    public String getListaConsulta(){
        ControleConsulta c = new ControleConsulta();
        List<ConsultasModel> listaConsultas = new ArrayList();
        
        listaConsultas = c.buscarTodasAsConsultas();
        
        Gson g = new Gson();
        return g.toJson(listaConsultas);
    }

    /**
     * PUT method for updating or creating an instance of ConsultaWS
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void AlteraCosnulta(String content) {
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("Consulta/cadastrar")
    public boolean cadastrarConsulta(String content)
    {
        boolean retorno;
        ControleConsulta c = new ControleConsulta();
        
        Gson g = new Gson();
        ConsultasModel consulta = (ConsultasModel) g.fromJson(content, ConsultasModel.class);
        //PEGA AS DATAS PARA O CAULCULO DE TEMPO
        
        SimpleDateFormat diaUltimaConsulta = new SimpleDateFormat("dd/mm/yyyy HH:mm");
        SimpleDateFormat proximaConsulta = new SimpleDateFormat("dd/mm/yyyy HH:mm");
        Date ultimaConsulta = c.ultimaConsultaConsultorio(consulta); 
        Date diaProximaConsulta = consulta.getConsulta_dataHora();
        diaUltimaConsulta.format(ultimaConsulta);
        proximaConsulta.format(proximaConsulta);
        
        //CALCULA A SE PODE OU NÃO AGENDAR A CONSULTA
        long calculaConsultas, consulta1, consulta2, consulta3;
        consulta1 = diaUltimaConsulta.getCalendar().getTimeInMillis();
        consulta2 = proximaConsulta.getCalendar().getTimeInMillis();
        consulta3 = consulta1 - consulta2;
        calculaConsultas = (consulta3 / 1000) / 60;
        
        //SE O RESULTADO DO CALCULO FOR MENOR QUE 15 MIN EM MILISEGUNDOS
        if (calculaConsultas < 1578225){
            c.marcaConsulta(consulta);
            retorno = true;
        }else{
            retorno = false;
        }
        
        return retorno;
        
    }
}
